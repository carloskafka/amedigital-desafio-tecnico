# Two Rest API's, one with Spring WebFlux and another with Spring Web and MySQL 

# Getting Started

A solução possui três módulos: 
   
   - **PlanetStarWars-commons**: Responsável por compartilhar os **DTO's** entre o projeto **server** e o **reativo**
   - **PlanetStarWars-server**: API principal para se comunicar com um client.
   - **PlanetStarWars-reativo**: API reativa que comunica com a API Star Wars (https://swapi.co/api/planets/) e retorna o resultado para a API principal

## Requisitos

Executar as aplicações **planetstarwars-server** **planetstarwars-reativo** com o comando `mvn spring-boot:run`

O servidor da aplicação **planetstarwars-server** irá iniciar em <http://localhost:8080>.
O servidor da aplicação **planetstarwars-reativo** irá iniciar em <http://localhost:9090>.

## Rest Endpoints

```
1. POST /planetas/ - Adicionar planeta

2. GET /planetas/ - Listar planetas

3. GET /planetas/api/ - Listar planetas pela API Star Wars

4. GET /planetas/{nome} - buscar planeta por nome

5. GET /planetas/{id} - buscar planeta por id

```

## Execução de testes de integração

O projeto também contém testes de integração para todos os endpoints da API. Para executar os testes de integração, vá até o diretório raíz do projeto e escreva `mvn test` no terminal (windows ou linux).
