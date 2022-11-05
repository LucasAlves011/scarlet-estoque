insert into categoria (nome) values ('Bermuda'),('Camisa'),('Long'),('Camisa de time'),('Calçado'),('Óculos'),('Bolsa'),('Jóia');
insert into nominal (P,M,G,GG) values (1,4,6,2),(4,2,7,3),(8,1,3,0),(2,2,3,4);
insert into numerico (`36`,`38`,`40`,`42`,`44`,`46`,`48`,`50`) values (1,2,3,4,5,6,7,8),(0,2,3,5,6,2,0,0),(1,5,2,6,2,3,3,0),(3,5,2,6,2,1,7,3);
insert into avulso (quantidade) values (3),(4),(2),(6);

insert into produto (nome, categoria_nome, imagem, valor, tipo, avulso_id, nominal_id, numerico_id) values
('Camisa tamo junto vermelha','Camisa',null,30,'NOMINAL',null,1,null),('Camisa lacoste branca','Camisa',null,60,'NOMINAL',null,2,null),
('Long nike preta','Long',null,45,'NOMINAL',null,3,null),('Camisa seleção 2022','Camisa de time',null,100,'NOMINAL',null,4,null),

('Sandália Kenner preta','Calçado',null,40,'NUMERICO',null,null,1),('Bermuda Jeans','Bermuda',null,75,'NUMERICO',null,null,2),
('Bermuda preta','Bermuda',null,60,'NUMERICO',null,null,3),('Calça Jeans','Calçado',null,120,'NUMERICO',null,null,4),

('Óculos Oakley','Óculos',null,70,'AVULSO',1,null,null),('Necessaire preta','Bolsa',null,30,'AVULSO',2,null,null),
('Necessaire Branca','Bolsa',null,30,'AVULSO',3,null,null),('Corrente prata','Jóia',null,130,'AVULSO',4,null,null);
