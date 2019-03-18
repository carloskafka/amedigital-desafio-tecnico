# Duas API's Rest, uma utlizando o framework Spring WebFlux e a outra API utlizando o framework Spring Web e o banco de dados MySQL

# Getting Started

Como a empresa Ame Digital possui uma alta demanda de requisições por segundo (rps), o uso da tecnologia assíncrona junto com a quebra do retorno (abordagem reativa) obtido pela API Star Wars otimizou o tempo de resposta da chamada da API Principal para a API Star Wars (https://swapi.co/api/planets/) de **5 segundos** para **10 milissegundos** ficando, basicamente, instantâneo.

A solução possui três módulos: 
   
   - **PlanetStarWars-commons**: Responsável por compartilhar os **DTO's** ou **objetos de transferência** e o **ContratoRest** responsável por centralizar todos os **Endpoints REST** entre o projeto **server** e o **reativo**
   - **PlanetStarWars-server**: API principal que utiliza o framework **Spring Web** e é estruturada no conceito arquitetural de "separação em camadas" para se comunicar com um client onde nela possui os **objetos de domínio** com suas **regras de negócio** (como validação de campos, por exemplo), a fachada principal que simplifica todas as chamadas.
   - **PlanetStarWars-reativo**: API que utiliza o framework **Spring WebFlux** com paradigma reativo que comunica com a API Star Wars (https://swapi.co/api/planets/) e retorna o resultado para a API principal

## Importante 

- Através do link http://localhost:9090/planetas/ é capaz de ver a aplicação **planetstarwars-reativo** obtendo os planetas aos poucos da API Star Wars.
- E é possível ver pressionando F5 para atualizar a página no link http://localhost:8080/planetas/api/, a aplicação **planetstarwars-server** obtendo a resposta da aplicação **planetstarwars-reativo** e após receber tudo, o tempo de resposta fica praticamente instantâneo.

## Requisitos

1. Executar `mvn install`no projeto **PlanetStarWars-commons**
2. Executar as aplicações **planetstarwars-server** **planetstarwars-reativo** com o comando `mvn spring-boot:run`

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
