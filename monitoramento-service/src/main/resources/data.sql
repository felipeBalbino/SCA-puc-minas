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

insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(1,'Medição de Nível','2020-11-01' );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(2,'Medição de Vazão','2020-11-01' );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(3,'Medição Analítica','2020-11-01' );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(4,'Medição de Pressão','2020-11-01');
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(5,'Medição de Temperatura','2020-11-01' );
insert into tipo_sensor (codigo_tipo_sensor, nome,data_inclusao) values(6,'Detecção de Pó','2020-11-01' );

insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(1, 1, 1,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(2, 2, 2,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(3, 3, 3,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(4, 4, 4,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(5, 5, 5,'2020-11-01' );
insert into sensor (codigo_sensor, codigo_ativo, codigo_tipo_sensor, data_inclusao) values(6, 6, 6,'2020-11-01' );

insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(1,1,'15.456123','-15.123456',1,'2020-11-01' );
insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(2,1,'15.456123','-15.123456',2,'2020-11-01' );
insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(3,1,'15.456123','-15.123456',3,'2020-11-01' );
insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(4,1,'15.456123','-15.123456',4,'2020-11-01' );
insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(5,1,'15.456123','-15.123456',5,'2020-11-01' );
insert into barragem (codigo_barragem, codigo_ativo, latitude, longitude, codigo_tipo_metodo,data_inclusao) values(6,1,'15.456123','-15.123456',1,'2020-11-01' );


insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(1,'22.5',1,'apto',1,'2012-07-12','3000',1);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(2,'10.5',2,'apto',2,'2007-02-22','3000',2);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(3,'3.5' ,3,'apto',3,'2020-02-18','3000',3);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(4,'45.5',1,'apto',1,'2009-02-11','3000',4);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(5,'2.0' ,2,'apto',2,'2019-12-26','3000',5);
insert into inspecao (codigo_inspecao, altura, codigo_categoria_risco, descricao, codigo_dano_potencial, data_inclusao, volume, barragem) values(6,'12.0',3,'apto',3,'2004-03-24','3000',6);