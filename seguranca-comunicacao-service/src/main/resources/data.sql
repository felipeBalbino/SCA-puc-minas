insert into pessoa (CODIGO_PESSOA,NOME_COMPLETO,DATA_NASCIMENTO,EMAIL, TELEFONE,CEP,NUMERO,COMPLEMENTO, DATA_INCLUSAO) values(1,'Vicente Benjamin Bryan da Rosa'	,'1988-11-01','carloscalebmonteiro_@gvmail.br'			,'(61) 2744-6935','73753-074',30,'Quadra 5 MC','2020-11-01');
insert into pessoa (CODIGO_PESSOA,NOME_COMPLETO,DATA_NASCIMENTO,EMAIL, TELEFONE,CEP,NUMERO,COMPLEMENTO, DATA_INCLUSAO) values(2,'Raul Bruno da Luz'				,'1988-11-01','rrebecaauroranunes@vanguarda.tv'			,'(61) 2744-6935','73753-074',30,'Quadra 5 MC','2020-11-01');
insert into pessoa (CODIGO_PESSOA,NOME_COMPLETO,DATA_NASCIMENTO,EMAIL, TELEFONE,CEP,NUMERO,COMPLEMENTO, DATA_INCLUSAO) values(3,'Vicente Luiz Bernardes'			,'1988-11-01','sseverinoyuriteixeira@rodomen.com.br'	,'(61) 2744-6935','73753-074',30,'Quadra 5 MC','2020-11-01');
insert into pessoa (CODIGO_PESSOA,NOME_COMPLETO,DATA_NASCIMENTO,EMAIL, TELEFONE,CEP,NUMERO,COMPLEMENTO, DATA_INCLUSAO) values(4,'Luiza Sebastiana da Costa'		,'1988-11-01','llaiscarolinapinto@salvagninigroup.com'	,'(61) 2744-6935','73753-074',30,'Quadra 5 MC','2020-11-01');
insert into pessoa (CODIGO_PESSOA,NOME_COMPLETO,DATA_NASCIMENTO,EMAIL, TELEFONE,CEP,NUMERO,COMPLEMENTO, DATA_INCLUSAO) values(5,'Carlos Caleb Monteiro'			,'1988-11-01','claudiopaulomoreira@alphacandies.com.br'	,'(61) 2744-6935','73753-074',30,'Quadra 5 MC','2020-11-01');

insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(1,'Alerta de evacuação total 1',0,'Todos que moram nas proximidades da Barragem, devem procurar pontos altos, indicados pela Defesa Civil.','2020-11-01',1);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(2,'Alerta de evacuação total 2',1,'Todos que moram nas proximidades da Barragem, devem procurar pontos altos, indicados pela Defesa Civil.','2020-11-01',2);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(3,'Alerta de evacuação total 3',2,'Todos que moram nas proximidades da Barragem, devem procurar pontos altos, indicados pela Defesa Civil.','2020-11-01',3);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(4,'Alerta de evacuação total 4',0,'Todos que moram nas proximidades da Barragem, devem procurar pontos altos, indicados pela Defesa Civil.','2020-11-01',4);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(5,'Alerta de evacuação total 5',1,'Todos que moram nas proximidades da Barragem, devem procurar pontos altos, indicados pela Defesa Civil.','2020-11-01',5);

insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(6,'Alerta de Sensor 6',0,'Emissão de alerta de sensor 6','2020-11-01',6);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(7,'Alerta de Sensor  7',1,'Emissão de alerta de sensor 7','2020-11-01',7);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(8,'Alerta de Sensor  8',2,'Emissão de alerta de sensor 8','2020-11-01',8);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(9,'Alerta de Sensor  9',0,'Emissão de alerta de sensor 9','2020-11-01',9);
insert into plano_acao(CODIGO_PLANO_ACAO, DESCRICAO, GRAU_RISCO, MENSAGEM_ALERTA, DATA_INCLUSAO,CODIGO_ATIVO) values(10,'Alerta de Sensor  10',1,'Emissão de alerta de sensor 10','2020-11-01',10);

insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(1,1);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(1,2);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(1,3);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(1,4);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(1,5);

insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(6,1);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(7,2);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(8,3);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(9,4);
insert into plano_acao_pessoas(plano_acao_codigo_plano_acao, pessoas_codigo_pessoa) values(10,5);

insert into comunicacao(CODIGO_COMUNICACAO,CODIGO_PLANO_ACAO,DATA_INCLUSAO) values(1,1,'2020-11-01');
insert into comunicacao(CODIGO_COMUNICACAO,CODIGO_PLANO_ACAO,DATA_INCLUSAO) values(2,2,'2020-11-01');
insert into comunicacao(CODIGO_COMUNICACAO,CODIGO_PLANO_ACAO,DATA_INCLUSAO) values(3,3,'2020-11-01');
insert into comunicacao(CODIGO_COMUNICACAO,CODIGO_PLANO_ACAO,DATA_INCLUSAO) values(4,4,'2020-11-01');
insert into comunicacao(CODIGO_COMUNICACAO,CODIGO_PLANO_ACAO,DATA_INCLUSAO) values(5,5,'2020-11-01');
