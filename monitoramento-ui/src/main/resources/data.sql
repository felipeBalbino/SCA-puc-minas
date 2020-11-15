insert into funcao (nome,data_inclusao) values('ADMINISTRADOR','2020-11-12');
insert into funcao (nome,data_inclusao) values('ENGENHEIRO','2020-11-12');
insert into funcao (nome,data_inclusao) values('CONSULTOR','2020-11-12');

insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2','administrador','2020-11-12');
insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2C','engenheiro','2020-11-12');
insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2','consultor','2020-11-12');

insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,1);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,2);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,3);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(2,2);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(2,3);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(3,3);