insert into fabricante (codigo_fabricante, nome, data_inclusao) values(1,'Barragem','2020-01-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(2,'Nextcon','2020-02-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(3,'LEMAQS','2020-03-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(4,'Acer','2020-04-24');
insert into fabricante (codigo_fabricante,  nome, data_inclusao) values(5,'Skiller','2020-05-24');

insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(1,'Barragem','2020-01-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(2,'Sensor','2020-02-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(3,'Estrutura de Contenção','2020-02-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(4,'Reforço de Talude','2020-03-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(5,'Hidráulico','2020-04-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(6,'Contenção à Gravidade','2020-05-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(7,'Controle de Queda de Rochas','2020-05-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(8,'Britadores','2020-05-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(9,'Transportador de Correia','2020-05-24');
insert into tipo_ativo (codigo_tipoativo,  nome, data_inclusao) values(10,'Pneira Vibratória','2020-05-24');

insert into ativo (  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Barragem de Rejeitos 1',1,1,'2020-01-24');
insert into ativo (  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Barragem de Rejeitos 2',1,1,'2020-02-24');
insert into ativo (  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Barragem de Rejeitos 3',1,1,'2020-03-24');
insert into ativo (  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Barragem de Rejeitos 4',1,1,'2020-04-24');
insert into ativo (  nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Barragem de Rejeitos 5',1,1,'2020-05-24');

insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Sensor 1',2,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Sensor 2',2,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Sensor 3',2,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Sensor 4',2,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Sensor 5',2,2,'2020-11-24');

insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Planta de Mineração',3,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Calha Vibratória',3,2,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Britador Girosférios',3,8,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Peneira Vibratória Britador',3,8,'2020-11-24');
insert into ativo (nome,codigo_fabricante, codigo_tipoAtivo,data_inclusao) values('Transportador de Correia',3,2,'2020-11-24');

INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (1, '2020-05-24', '2020-05-24', '2020-05-24', 'Descrição da manutencao 1', 0, 0, 1);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (2, '2020-05-24', '2020-05-24', '2020-05-24', 'Descrição da manutencao 2', 1, 1, 2);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (3, '2020-05-24', '2020-05-24', '2020-05-24', 'Descrição da manutencao 3', 0, 2, 3);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (4, '2020-05-24', '2020-05-24', '2020-05-24', 'Descrição da manutencao 4', 1, 0, 4);
INSERT INTO manutencao (codigo_manutencao, data_inclusao, data_manutencao, data_prox_manutencao, descricao, status_manutencao, tipo_manutencao, codigo_ativo) VALUES (5, '2020-05-24', '2020-05-24', '2020-05-24', 'Descrição da manutencao 5', 0, 1, 5);
