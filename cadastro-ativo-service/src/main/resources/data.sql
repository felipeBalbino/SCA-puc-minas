insert into fabricante (codigo_fabricante, nome, data_inclusao) values(1,'Barragem','2004-01-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(2,'Ferrari','2004-02-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(3,'Volvo','2004-03-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(4,'Acer','2004-04-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(5,'Skiller','2004-05-24');

insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(1,'Barragem','2004-01-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(2,'Sensor','2004-02-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(3,'Caminhões de Mineração','2004-02-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(4,'Níquel','2004-03-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(5,'Cobre','2004-04-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(6,'Minério de Ferro','2004-05-24');

insert into ativo (codigo_ativo,  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values(1,'Barragem de Rejeitos Fundão',1,1,'2004-01-24');
insert into ativo (codigo_ativo,  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values(2,'Barragem de Rejeitos Fundão 2',2,2,'2004-02-24');
insert into ativo (codigo_ativo,  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values(3,'Barragem de Rejeitos Fundão 3',3,3,'2004-03-24');
insert into ativo (codigo_ativo,  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values(4,'Barragem de Rejeitos Fundão 4',4,4,'2004-04-24');
insert into ativo (codigo_ativo,  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values(5,'Barragem de Rejeitos Fundão 5',5,5,'2004-05-24');

INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (1, '2004-05-24', '2004-05-24', '2004-05-24', 'Descrição da manutencao 1', 0, 0, 1);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (2, '2004-05-24', '2004-05-24', '2004-05-24', 'Descrição da manutencao 2', 1, 1, 2);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (3, '2004-05-24', '2004-05-24', '2004-05-24', 'Descrição da manutencao 3', 0, 2, 3);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (4, '2004-05-24', '2004-05-24', '2004-05-24', 'Descrição da manutencao 4', 1, 0, 4);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (5, '2004-05-24', '2004-05-24', '2004-05-24', 'Descrição da manutencao 5', 0, 1, 5);
