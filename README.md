# Projeto de API REST – Baozi Store com Spring Boot

Este projeto foi desenvolvido como parte da atividade prática da disciplina de Desenvolvimento Web Back End do curso de Análise e Desenvolvimento de Sistemas da Uninter, e também como forma de praticar, demonstrar meus conhecimentos em desenvolvimento back-end e gerar material para meu portfólio. O objetivo é desenvolver uma API REST para o controle básico de uma pequena loja fictícia (Baozi Store), aplicando a arquitetura MVC do Spring e mapeamento relacional.

## Objetivo:

Desenvolver e automatizar o back-end da loja Baozi Store para gerenciar:
- Cadastro de clientes;
- Cadastro de produtos;
- Registro de pedidos simples (vinculando o cliente e o produto comprado).

## Tecnologias utilizadas:

- Java 17;
- Spring Boot;
- Spring Data JPA;
- MySQL;
- Postman.

## Estrutura do Projeto:

O projeto segue a arquitetura padrão MVC do Spring, garantindo separação de responsabilidades e fácil manutenção do código:
- model/ → contém as entidades de dados (Cliente, Produto, Pedido);
- repository/ → contém as interfaces do JPA para comunicação direta com o banco de dados;
- controller/ → contém os endpoints REST que recebem as requisições HTTP.

## Benefícios do padrão MVC:

- Código mais organizado;
- Separação clara entre acesso a dados e rotas da API;
- Facilidade de manutenção e escalabilidade.

## Como executar o projeto:

- Clonar o repositório:

``git clone https://github.com/barbaramsilveira/desenvolvimentoBackEndUninter.git``

## Configurar o banco de dados:

- Crie um banco de dados no MySQL com o nome bd_rest.

- No arquivo application.properties, ajuste as credenciais do seu banco local (username e password).

## Executar a aplicação:

- Inicie o projeto pela sua IDE (como IntelliJ ou Eclipse) executando a classe principal, ou via terminal na raiz do projeto:

``./mvnw spring-boot:run``

- A aplicação estará rodando em http://localhost:8080.

## Como testar os endpoints:

- Você pode utilizar o Postman ou o Insomnia para realizar requisições (POST, GET, PUT, DELETE) nas seguintes rotas principais:
    - http://localhost:8080/clientes
    - http://localhost:8080/produtos
    - http://localhost:8080/pedidos

## Funcionalidades Implementadas

- Clientes → Criar, listar todos, listar por ID, atualizar e excluir;
- Produtos → Criar, listar todos, listar por ID, atualizar e excluir;
- Pedidos → Criar (relacionando ID de cliente e produto), listar todos, listar por ID, atualizar e excluir.

