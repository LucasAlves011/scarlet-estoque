package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.model.dto.produto.*;
import com.scarlet.backscarlet.model.enums.Tipo;
import com.scarlet.backscarlet.model.repository.CategoriaRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {

    @Value("${app.pasta-fotos}")
    private String imagens;

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Object> findAllProdutos() {
        List<Produto> retorno = produtoRepository.findAllAvulso();
        retorno.addAll(produtoRepository.findAllNominal());
        retorno.addAll(produtoRepository.findAllNumerico());
        retorno.sort(Comparator.comparing(Produto::getNome));
        return retorno.stream().map(this::transformarDTO).collect(Collectors.toList());
    }

    public ProdutoDTO cadastrarProduto(ProdutoInputDTO p, MultipartFile image) {
        var cats = p.getCategorias().stream().map(pr -> Optional.ofNullable(categoriaRepository.findByNomeOrderByNome(pr))
                        .orElseThrow(() -> new ObjectNotFoundException("Categoria de nome " + pr + " não é uma categoria cadastrada.")))
                .collect(Collectors.toList());
        var pr = transformarProduto(p, cats);

        try {
            byte[] bytes = image.getBytes();
            var uuidImagem = UUID.randomUUID().toString();
            Path caminhoImagem = Paths.get(imagens + uuidImagem + ".png");
            Files.write(caminhoImagem, bytes);

            pr.setImagem(uuidImagem + ".png");
        } catch (IOException e) {
            System.err.println("Erro em cadastrar a imagem do produto.");
        }
        produtoRepository.save(pr);

        return transformarDTO(pr);
    }

    public Object produtoPorId(int id) throws ObjectNotFoundException {
        var x = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
        return transformarDTO(x);
    }

    public void deletarProduto(int id) {
        var nomeImagem = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado")).getImagem();
        File file = new File(imagens + nomeImagem);
        boolean deleted = file.delete();
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO alterarProduto(int id, ProdutoInputDTO novoProduto) throws ObjectNotFoundException {
        var antigoProduto = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));

        antigoProduto.setNome(novoProduto.getNome());
        modificarCategoria(novoProduto.getCategorias(), antigoProduto);
        antigoProduto.setValor(novoProduto.getValor());

        if (antigoProduto.getAvulso() != null) {
            antigoProduto.getAvulso().setQuantidade(novoProduto.getAvulso().getQuantidade());
        } else if (antigoProduto.getNominal() != null) {
            antigoProduto.getNominal().setP(novoProduto.getNominal().getP());
            antigoProduto.getNominal().setM(novoProduto.getNominal().getM());
            antigoProduto.getNominal().setG(novoProduto.getNominal().getG());
            antigoProduto.getNominal().setGG(novoProduto.getNominal().getGG());
        } else {
            antigoProduto.getNumerico().setT36(novoProduto.getNumerico().getT36());
            antigoProduto.getNumerico().setT38(novoProduto.getNumerico().getT38());
            antigoProduto.getNumerico().setT40(novoProduto.getNumerico().getT40());
            antigoProduto.getNumerico().setT42(novoProduto.getNumerico().getT42());
            antigoProduto.getNumerico().setT44(novoProduto.getNumerico().getT44());
            antigoProduto.getNumerico().setT46(novoProduto.getNumerico().getT46());
            antigoProduto.getNumerico().setT48(novoProduto.getNumerico().getT48());
            antigoProduto.getNumerico().setT50(novoProduto.getNumerico().getT50());
        }

        return transformarDTO(produtoRepository.save(antigoProduto));
    }

    public List<ProdutoDTO> findByCategoria(String c) {
        var categoria = new Categoria();
        categoria.setNome(c);
        return produtoRepository.findByCategorias(categoria).stream().map(this::transformarDTO).collect(Collectors.toList());
    }

    public ProdutoDTO alterarCategorias(int id, List<String> novasCategorias) throws ObjectNotFoundException {
        var x = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto de id " + id + "não encontrado."));
        var todasCategorias = categoriaRepository.findAll();

        novasCategorias.forEach(c -> {
            if (!todasCategorias.stream().map(Categoria::toString).toList().contains(c.toUpperCase()))
                throw new ObjectNotFoundException("Categoria de nome " + c + " não é uma categoria cadastrada.");
        });

        modificarCategoria(novasCategorias, x);

        return transformarDTO(x);
    }

    public List<ProdutoDTO> findByNome(String nome) {
        var x = produtoRepository.findByNomeLike(nome);
        return x.stream().map(this::transformarDTO).collect(Collectors.toList());
    }


    private List<Categoria> transformarStringsEmCategoria(List<String> novasCategorias) {
        var todasCategorias = categoriaRepository.findAll();
        return novasCategorias.stream().map(String::toUpperCase)
                .map(c -> todasCategorias.stream().filter(a
                        -> a.verificarPorNome(c)
                ).findFirst().get()).toList();
    }

    private void modificarCategoria(List<String> novasCategorias, Produto x) {
        var novasCats = transformarStringsEmCategoria(novasCategorias);

        var precisamSair = x.getCategorias().stream().filter(Predicate.not(novasCats::contains)).toList();

        var precisamEntrar = novasCats.stream().filter(Predicate.not(x.getCategorias()::contains)).toList();

        precisamSair.forEach(x::removerUmaCategoria);
        precisamEntrar.forEach(x::adicionarUmaCategoria);
    }

    private ProdutoDTO transformarDTO(Produto produto) {
        if (produto.getNominal() != null)
            return new ProdutoNominalDTO(produto);
        else if (produto.getAvulso() != null)
            return new ProdutoAvulsoDTO(produto);
        else if (produto.getNumerico() != null)
            return new ProdutoNumericoDTO(produto);
        else
            throw new RuntimeException("Qualquer coisa"); //TODO: melhorar essa exceptions
    }

    private Produto transformarProduto(ProdutoInputDTO p, List<Categoria> categorias) {
        var t = new Produto();
        t.setNome(p.getNome());
        t.setAvulso(p.getAvulso());
        t.setNominal(p.getNominal());
        t.setNumerico(p.getNumerico());
        t.setMarca(p.getMarca() == null ? null : p.getMarca().toUpperCase());
        t.setValor(p.getValor());

        switch (p.getTipo().toUpperCase()) {
            case "AVULSO" -> t.setTipo(Tipo.AVULSO);
            case "NOMINAL" -> t.setTipo(Tipo.NOMINAL);
            case "NUMERICO" -> t.setTipo(Tipo.NUMERICO);
        }

        if (categorias == null)
            t.setCategorias(transformarStringsEmCategoria(p.getCategorias()));
        else
            t.setCategorias(categorias);

        return t;
    }

    public List<String> findMarcas() {
        return produtoRepository.findAllMarcas();
    }

    public List<RetornoQuantidadesPorMarca> marcasAndQuantidade() {
        var c = new ArrayList<RetornoQuantidadesPorMarca>();
        var data = produtoRepository.findAll();
        var marcas = produtoRepository.findAllMarcas();

        marcas.forEach(e -> {
            var qtdProdutos = data.stream().filter(f -> f.getMarca() != null && f.getMarca().equals(e)).count();
            var unidades = data.stream().filter(f -> f.getMarca() != null && f.getMarca().equals(e)).mapToInt(Produto::getUnidades).sum();
            c.add(new RetornoQuantidadesPorMarca(e, qtdProdutos, unidades));
        });

        var nulos = data.stream().filter(e -> e.getMarca() == null).toList();
        c.add(new RetornoQuantidadesPorMarca("null", nulos.size(), nulos.stream().mapToInt(Produto::getUnidades).sum()));

        return c;
    }

    public List<ProdutoDTO> findByMarca(String marca) {
        return produtoRepository.findByMarca(marca).stream().map(this::transformarDTO).toList();
    }

    public byte[] findImage(String nome) throws IOException {
        return Files.readAllBytes(Path.of(imagens + nome));
    }

    public byte[] getLogo() throws IOException {
        return Files.readAllBytes(Path.of(imagens + "logo32x.png"));
    }

    public String nomeProdutoPorId(int id) {
        String nome;
        try {
            nome = produtoRepository.findById(id).get().getNome();
        } catch (Exception e) {
            nome = "Nome não encontrado";
        }
        return nome;
    }

    public List<String> getTamanhos(List<Integer> ids) {
        return categoriaRepository.findAllProdutoWithCategorias(ids);
    }
}
