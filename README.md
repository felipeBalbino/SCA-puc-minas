# SISTEMA DE GESTÃO AMBIENTAL - SGA

# Descrição
Este repositório mantém os códigos fontes da prova de conceito elaborada para o trabalho de conclusão de curso da pós graduação em Arquitetura de Sistemas Distribuídos.
Implementação dos serviços que compõe o tema do TCC do curso de Arquitetura de Software distribuído da PUC-Minas.

Orientador(a): XXX

Alunos: Felipe Balbino/ Gabriel Ferreira

[Apresentação da POC no Youtube.](https://youtu.be/XXX)

## Protótipo Arquitetural

XXXX

## Passos para subir o sistema localmente

1. Como pré-requisito, possuir Interface de Linha de Comando(CLI) - [docker][maven][git]
2. Baixar o arquivo docker-compose.yml deste repositório e executar o comando: docker-compose up
3. A aplicação estará disponível na URL: http://localhost:8080/
4. Para o primeiro acesso ao sistema, o usuário de Administrador do sistema já é criado:
  * Usuário: admin
  * Senha: 12345678
 
## Técnologias Utilizadas na POC

* [Java](https://java.com/en/download/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Mysql](https://www.mysql.com/)
* [Maven](https://maven.apache.org/)
* [Bootstrap](https://getbootstrap.com/)
* [RabbitMQ](https://www.rabbitmq.com/)
* [Docker](https://www.docker.com/)
* [Netflix Zuul](https://github.com/Netflix/zuul)
* [Netflix Eureka](https://github.com/Netflix/eureka)


## Projetos do repositório
* eureka-service: É o servidor de registros (toda api se registra nele).
* zuul-gateway: É o servidor gateway (é a porta de entrada para acessar os serviços).
* monitoramento-service: Api de monitoramento.
* seguranca-comunicacao-service: Api de segurança e comunicação.
* monitoramento-ui: Aplicaçao Web/mobile que consome os serviços feitos.
* notificacoes-service: Responsavel por centralizar as notificacoes geradas por todos os modulos.

