# Projeto Web1

Este projeto consiste em três atividades avaliativas (AA1, AA2, AA3) focadas em desenvolvimento web utilizando diferentes tecnologias. O objetivo principal é demonstrar a aplicação de conceitos de Java EE, Spring Framework e ferramentas de desenvolvimento web como Tomcat e Maven.

## Responsáveis

- Antonio Maldonado Guerra da Cunha
- Enzo Youji Murayama
- Lucas Sciarra Gonçalves

## Estrutura do Projeto

O projeto está dividido em três atividades:

- **AA1**: Um aplicativo web simples utilizando Java EE e Tomcat.
- **AA2**: Um aplicativo web utilizando Spring Framework com suporte ao Spring Boot.
- **AA3**: Baseado em AA2, implementa os requisitos com as funcionalidades providas em uma REST API.

Cada atividade é projetada para demonstrar uma parte específica do desenvolvimento de aplicações web em Java.

### AA1 - Java EE com Tomcat e Maven

A primeira atividade envolve a criação de um aplicativo web que roda no servidor Tomcat e é construído com Maven. O foco aqui está no desenvolvimento de uma aplicação simples que utiliza Servlets, JSP e configuração via `web.xml`.

### AA2 - Aplicação com Spring Framework

Na segunda atividade, o projeto evolui para uma arquitetura mais robusta utilizando o Spring Framework. O objetivo é configurar um aplicativo Spring Boot, que inclui controladores, mapeamentos de rotas e integração com Thymeleaf para renderização de páginas dinâmicas.

### AA3 - REST API

Na terceira atividade o projeto evolui para uma REST API utilizando Spring Boot, permitindo operações CRUD sobre as entidades via requisições HTTP. A API utiliza JSON para troca de dados, e o Postman é usado para testar os endpoints e verificar o comportamento da API.

---

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Maven**: Gerenciador de dependências e construção do projeto.
- **Tomcat**: Servidor de aplicações utilizado na AA1.
- **Spring Framework**: Framework utilizado na AA2 para facilitar o desenvolvimento e a configuração da aplicação.
- **Thymeleaf**: Template engine para renderização de páginas HTML dinâmicas no Spring.
- **JSP**: Usado na AA1 para renderização de páginas.
- **REST API**: Interface de comunicação entre sistemas utilizando protocolos HTTP.
- **Postman**: Ferramenta para testar APIs REST e verificar as respostas.
  
---

## Configuração e Execução do Projeto

### Requisitos

Certifique-se de ter os seguintes softwares instalados:

- Java 8 ou superior
- Maven
- Tomcat 9.x (para AA1)
- IDE com suporte a Java (Eclipse, IntelliJ, VSCode)
- Docker (opcional, caso queira executar o projeto em containers)