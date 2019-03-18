# Reactive Rest APIs with Spring WebFlux and DynamoDB Async

# Getting Started

## Requirements

Run the app `mvn spring-boot:run`

The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application defines following REST APIs

```
1. POST /planetas/ - Adicionar planeta

2. GET /planetas/ - Listar planetas

3. GET /planetas/api/ - Listar planetas pela API Star Wars

4. GET /planetas/{nome} - buscar planeta por nome

5. GET /planetas/{id} - buscar planeta por id

```

## Running integration tests

The project also contains integration tests for all the Rest APIs. For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.
