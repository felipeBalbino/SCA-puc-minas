# SISTEMA DE GESTÃO AMBIENTAL - SGA

# Descrição
Este repositório mantém os códigos fontes da prova de conceito elaborada para o trabalho de conclusão de curso da pós graduação em Arquitetura de Sistemas Distribuídos.
Implementação dos serviços que compõe o tema do TCC do curso de Arquitetura de Software distribuído da PUC-Minas.

Orientador: Luiz Alberto Ferreira Gomes
Tutora: Marjorie Pimentel


Alunos: Felipe Balbino/ Gabriel Ferreira

![alt text](https://github.com/felipeBalbino/SCA-puc-minas/blob/main/Documentos/Diagramas/Instrutura%20do%20Sistema.png)
Instrutura geral do sistema

![alt text](https://github.com/felipeBalbino/SCA-puc-minas/blob/main/Documentos/Diagramas/Diagrama%20de%20Componente.png?raw=true)
Diagrama de Componente

![alt text](https://github.com/felipeBalbino/SCA-puc-minas/blob/main/Documentos/Diagramas/Diagrama%20de%20Implanta%C3%A7%C3%A3o.png?raw=true)
Diagrama de Implantação

## Passos para subir o sistema localmente

1. Como pré-requisito, possuir Interface de Linha de Comando(CLI) - [docker][maven][git]
2. Baixar o arquivo docker-compose.yml deste repositório e executar o comando: docker-compose up
3. A aplicação estará disponível na URL: http://localhost:8080/
4. Para o primeiro acesso ao sistema, o usuário de Administrador do sistema já é criado:
  * Usuário: administrador
  * Senha: qwe123
  
## Passos para subir o sistema no ecossistema da AWS(EC2)

1. Como pré-requisito, possuir Interface de Linha de Comando(CLI) - [docker][maven][git]
2. Subir todos os o arquivos de projeto para sua instância criada previamente. 
3. Instação e execução de bibliotecas:
```
	#Instalar o Docker: 
		sudo yum install docker
	
	#Instalar o Maven: 
		sudo yum install maven
	
	#Instalar o docker compose:
		sudo curl -L https://github.com/docker/compose/releases/download/1.21.0/docker-compose-`uname-s`-`uname -m` | sudo tee / usr / local / bin / docker-compose> / dev / null
		sudo chmod + x / usr / local / bin / docker-compose
		sudo ln -s / usr / local / bin / docker-compose / usr / bin / docker-compose
	
	#Start docker:
		sudo service docker start
		
	#Gerar imagens do Docker:
		sudo maven clean install
		
	#Implantar imagens:
		sudo docker-compose up
```
4. A aplicação estará disponível na URL:  http://[url_gerada_instancia_ec2]:8080/
5. Para o primeiro acesso ao sistema, o usuário de Administrador do sistema já é criado:
  * Usuário: administrador
  * Senha: qwe123
 
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

