# Reactive Rest APIs with Spring WebFlux and DynamoDB Async

# Getting Started

A solução possui três módulos: 
   
   - **PlanetStarWars-commons**: Responsável por compartilhar os **DTO's** entre o projeto **server** e o **reativo**
   - **PlanetStarWars-server**: API principal para se comunicar com um client.
   - **PlanetStarWars-reativo**: API reativa que comunica com a API Star Wars (https://swapi.co/api/planets/) e retorna o resultado para a API principal

## Requisitos

Executar a aplicação `mvn spring-boot:run`

O servidor irá iniciar em <http://localhost:8080>.

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
