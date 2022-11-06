insert into categoria (nome) values ('Bermuda'),('Camisa'),('Long'),('Camisa de time'),
                                    ('Calçado'),('Óculos'),('Bolsa'),('Jóia'),('Jeans'),('Calça'),('Nike');
insert into nominal (P,M,G,GG) values (1,4,6,2),(4,2,7,3),(8,1,3,0),(2,2,3,4);
insert into numerico (`36`,`38`,`40`,`42`,`44`,`46`,`48`,`50`) values (1,2,3,4,5,6,7,8),(0,2,3,5,6,2,0,0),(1,5,2,6,2,3,3,0),(3,5,2,6,2,1,7,3);
insert into avulso (quantidade) values (3),(4),(2),(6);

insert into produto (nome, imagem, valor, tipo, avulso_id, nominal_id, numerico_id) values
('Camisa tamo junto vermelha',null,30,'NOMINAL',null,1,null),('Camisa lacoste branca',null,60,'NOMINAL',null,2,null),
('Long nike preta',null,45,'NOMINAL',null,3,null),('Camisa seleção 2022',null,100,'NOMINAL',null,4,null),

('Sandália Kenner preta',null,40,'NUMERICO',null,null,1),('Bermuda Jeans',null,75,'NUMERICO',null,null,2),
('Bermuda preta',null,60,'NUMERICO',null,null,3),('Calça Jeans',null,120,'NUMERICO',null,null,4),

('Óculos Oakley',null,70,'AVULSO',1,null,null),('Necessaire preta',null,30,'AVULSO',2,null,null),
('Necessaire Branca',null,30,'AVULSO',3,null,null),('Corrente prata',null,130,'AVULSO',4,null,null);

insert into produto_categorias (produto_id,categoria_nome) values
(1,'Camisa'),(1,'Long')
,(2,'Camisa'),(3,'Nike'),(3,'Camisa'),(3,'Long'),(4,'Camisa de Time'),(4,'Camisa'),(5,'Calçado'),
 (6,'Bermuda'),(6,'Jeans'),(7,'Bermuda'),(8,'Calça'),(8,'Jeans'),(9,'Óculos'),(10,'Bolsa'),(11,'Bolsa'),(12,'Jóia');