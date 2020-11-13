insert into funcao (nome,data_inclusao) values('ADMINISTRATOR','2020-11-12');
insert into funcao (nome,data_inclusao) values('ENGINEER','2020-11-12');
insert into funcao (nome,data_inclusao) values('CONSULTANT','2020-11-12');

insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2','testAdmin','2020-11-12');
insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2C','testEnginner','2020-11-12');
insert into usuario (senha,nome,data_inclusao) values('$2a$10$LqgUaHCb8iJ8mRS7or6Dk.kZF0nqM133R6rDZundeRT.Q6xZIHzD2','testConsultant','2020-11-12');

insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,1);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,2);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(1,3);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(2,2);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(2,3);
insert into usuario_funcoes (usuarios_codigo_usuario,funcoes_codigo_funcao) values(3,3);