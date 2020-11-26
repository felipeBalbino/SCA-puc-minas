insert into tipo_metodo (codigo_tipo_metodo, nome,data_inclusao) values(1,'Etapa única','2020-11-01' );
insert into tipo_metodo (codigo_tipo_metodo, nome,data_inclusao) values(2,'Alteamento a jusante','2020-11-01' );
insert into tipo_metodo (codigo_tipo_metodo, nome,data_inclusao) values(3,'Alteamento a montante','2020-11-01' );
insert into tipo_metodo (codigo_tipo_metodo, nome,data_inclusao) values(4,'Alteamento por linha de centro','2020-11-01');
insert into tipo_metodo (codigo_tipo_metodo, nome,data_inclusao) values(5,'Desconhecido','2020-11-01' );

insert into dano_potencial (codigo_dano_potencial, nome,data_inclusao) values(1,'Baixo','2020-11-01' );
insert into dano_potencial (codigo_dano_potencial, nome,data_inclusao) values(2,'Médio','2020-11-01' );
insert into dano_potencial (codigo_dano_potencial, nome,data_inclusao) values(3,'Alto','2020-11-01' );

insert into categoria_risco (codigo_categoria_risco, nome,data_inclusao) values(1,'Baixo','2020-11-01' );
insert into categoria_risco (codigo_categoria_risco, nome,data_inclusao) values(2,'Médio','2020-11-01' );
insert into categoria_risco (codigo_categoria_risco, nome,data_inclusao) values(3,'Alto','2020-11-01' );

insert into barragem (nome, codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values('Brumadinho - Córrego do Feijão', 1, 1,'-20.1247746','-44.1243703',1,'2020-11-01' );
insert into barragem (nome, codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values('Mariana - Fundão', 2, 2,'-20.2054932','-43.4621964',2,'2020-11-01' );
insert into barragem (nome, codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values('Mariana - Germano', 3, 3,'-20.2054932','-43.4621964',3,'2020-11-01' );
insert into barragem (nome, codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values('Mariana - Santarém', 4, 4,'-20.2251652','-43.460372',4,'2020-11-01' );
insert into barragem (nome, codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values('Brumadinho - Capim Branco', 5, 5,'-20.1206066','-44.1076755',5,'2020-11-01' );


insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(1,'Medição de Nível','2020-11-01',10,90 );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(2,'Medição de Vazão','2020-11-01',10,90 );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(3,'Medição Analítica','2020-11-01',10,90 );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(4,'Medição de Pressão','2020-11-01',10,90);
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(5,'Medição de Temperatura','2020-11-01',10,90 );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao,min_leitura,max_leitura) values(6,'Detecção de Pó','2020-11-01',10,90 );

insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(1, 6, 1	, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(2, 7, 2	, 2,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(3, 8, 3	, 3,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(4, 9, 4	, 4,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(5, 10, 5	, 5,'2020-11-01' );

insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(6, 6, 1	, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(7, 7, 2	, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(8, 8, 3	, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(9, 9, 4	, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor,CODIGO_BARRAGEM, data_inclusao) values(10, 10, 5, 1,'2020-11-01' );


insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, CODIGO_BARRAGEM) values(1,'22.5',1,'apto',1,'2020-07-12','3000',1);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, CODIGO_BARRAGEM) values(2,'10.5',2,'apto',2,'2020-02-22','3000',2);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, CODIGO_BARRAGEM) values(3,'3.5' ,3,'apto',3,'2020-02-18','3000',3);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, CODIGO_BARRAGEM) values(4,'45.5',1,'apto',1,'2020-02-11','3000',4);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, CODIGO_BARRAGEM) values(5,'2.0' ,2,'apto',2,'2020-12-26','3000',5);
