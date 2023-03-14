package com.scarlet.backscarlet.model.beans;

import com.scarlet.backscarlet.controller.exceptions.ErroCoringaException;
import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.TamanhoIncompativelException;
import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import com.scarlet.backscarlet.model.enums.Tipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @NotBlank
    private String nome;

    private String imagem;

    @PositiveOrZero
    private double valor;

    private String marca;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produto_categorias", joinColumns = @JoinColumn(name = "produto_id")
            , inverseJoinColumns = @JoinColumn(name = "categoria_nome"))
    private List<Categoria> categorias;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Nominal nominal;

    @OneToOne(cascade = CascadeType.ALL)
    private Numerico numerico;

    @OneToOne(cascade = CascadeType.ALL)
    private Avulso avulso;

    public void adicionarUmaCategoria(Categoria c) {
        categorias.add(c);
    }

    public void removerUmaCategoria(Categoria c) {
        categorias.remove(c);
    }

    public boolean verificarDisponibilidade(TamanhoEnum tamanhoEnum, int unidades) throws ObjectNotFoundException, TamanhoIncompativelException {

        if (tamanhoEnum == null) {
            if (this.getAvulso() == null)
                throw new TamanhoIncompativelException("Não é possível adicionar o tamanho " + Optional.ofNullable(tamanhoEnum.getTamanho()).orElse("")
                        + "para um produto que esta cadastrado como um avulso.");
            return this.getAvulso().getQuantidade() >= unidades;
        } else if (tamanhoEnum.equals(TamanhoEnum.P) || tamanhoEnum.equals(TamanhoEnum.M) | tamanhoEnum.equals(TamanhoEnum.G) || tamanhoEnum.equals(TamanhoEnum.GG)) {
            if (this.getNominal() == null)
                throw new TamanhoIncompativelException("Não é possível adicionar o tamanho " + tamanhoEnum.getTamanho()
                        + " para um produto que esta cadastrado com tamanhos (36/38/40/42...)");

            if (TamanhoEnum.P == tamanhoEnum) return this.getNominal().getP() >= unidades;
            else if (TamanhoEnum.M == tamanhoEnum) return this.getNominal().getM() >= unidades;
            else if (TamanhoEnum.G == tamanhoEnum) return this.getNominal().getG() >= unidades;
            return this.getNominal().getGG() >= unidades;
        } else if (tamanhoEnum.equals(TamanhoEnum.T36) || tamanhoEnum.equals(TamanhoEnum.T38) | tamanhoEnum.equals(TamanhoEnum.T40) || tamanhoEnum.equals(TamanhoEnum.T42) ||
                tamanhoEnum.equals(TamanhoEnum.T44) || tamanhoEnum.equals(TamanhoEnum.T46) | tamanhoEnum.equals(TamanhoEnum.T48) || tamanhoEnum.equals(TamanhoEnum.T50)) {
            if (this.getNumerico() == null)
                throw new TamanhoIncompativelException("Não é possível adicionar o tamanho " + tamanhoEnum.getTamanho()
                        + " para um produto que esta cadastrado com tamanhos (P/M/G/GG)");

            if (TamanhoEnum.T36 == tamanhoEnum) return this.getNumerico().getT36() >= unidades;
            else if (TamanhoEnum.T38 == tamanhoEnum) return this.getNumerico().getT38() >= unidades;
            else if (TamanhoEnum.T40 == tamanhoEnum) return this.getNumerico().getT40() >= unidades;
            else if (TamanhoEnum.T42 == tamanhoEnum) return this.getNumerico().getT42() >= unidades;
            else if (TamanhoEnum.T44 == tamanhoEnum) return this.getNumerico().getT44() >= unidades;
            else if (TamanhoEnum.T46 == tamanhoEnum) return this.getNumerico().getT46() >= unidades;
            else if (TamanhoEnum.T48 == tamanhoEnum) return this.getNumerico().getT48() >= unidades;
            else return this.getNumerico().getT50() >= unidades;
        }

        throw new RuntimeException("Erro estranho"); // TODO ajeitar os textos das exceptions
    }

    public void retirarEstoque(Tamanho tamanho, int unidades) throws ErroCoringaException {
        try {
            if (getAvulso() != null)
                this.avulso.setQuantidade(getAvulso().getQuantidade() - unidades);
            else if (getNominal() != null) {
                switch (tamanho.getTamanho()) {
                    case "P" -> this.getNominal().setP(getNominal().getP() - unidades);
                    case "M" -> this.getNominal().setM(getNominal().getM() - unidades);
                    case "G" -> this.getNominal().setG(getNominal().getG() - unidades);
                    case "GG" -> this.getNominal().setGG(getNominal().getGG() - unidades);
                }
            } else if (getNumerico() != null) {
                switch (tamanho.getTamanho()) {
                    case "36" -> getNumerico().setT36(getNumerico().getT36() - unidades);
                    case "38" -> getNumerico().setT38(getNumerico().getT38() - unidades);
                    case "40" -> getNumerico().setT40(getNumerico().getT40() - unidades);
                    case "42" -> getNumerico().setT42(getNumerico().getT42() - unidades);
                    case "44" -> getNumerico().setT44(getNumerico().getT44() - unidades);
                    case "46" -> getNumerico().setT46(getNumerico().getT46() - unidades);
                    case "48" -> getNumerico().setT48(getNumerico().getT48() - unidades);
                    case "50" -> getNumerico().setT50(getNumerico().getT50() - unidades);
                }
            }
        } catch (NullPointerException x) {
            throw new ErroCoringaException("Todos os tamanhos de um dos produtos estão nulos." + this + "\n" + "tamanho" + tamanho.getTamanho() + "  -  unidades" + unidades, x.getCause());
        }
    }

    public int getUnidades() {

        if (getAvulso() != null) {
            return this.avulso.getQuantidade();

        } else if (getNominal() != null) {
            return this.nominal.getQuantidade();

        } else if (getNumerico() != null) {
            return this.numerico.getQuantidade();
        }
        throw new ErroCoringaException("Todos os tamanhos de um dos produtos estão nulos." + this);

    }

}
