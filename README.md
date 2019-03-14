# amedigital-desafio-tecnico

General instructions
Ready? Go! Aqui teremos um teste para você mostrar um pouco do que sabe!
Dando continuidade ao nosso processo, temos um desafio para te propor! \o/



Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.

Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas, que podem ser obtidas pela API pública do Star Wars (https://swapi.co/)

Requisitos:

A API deve ser REST
Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, que foram inseridos manualmente pela funcionalidade de adicionar planetas:
Nome
Clima
Terreno
Para cada planeta também devemos ter a quantidade de aparições em filmes que deve ser obtida pela api do Star Wars na inserção do planeta.


Funcionalidades desejadas:

Adicionar um planeta (com nome, clima e terreno)
Listar planetas do banco de dados
Listar planetas da API do Star Wars
Buscar por nome no banco de dados
Buscar por ID no banco de dados
Remover planeta


Linguagens que usamos: Java, Kotlin

Bancos que usamos: DynamoDB, Cassandra, MySQL, Oracle

Diferencial: Utilizar framework reativo (Ex.: Spring WebFlux, VertX)



Em média, as pessoas demoram 2 dias para resolver e enviar.

Antes de começar, estabeleça o seu prazo de entrega e informe a gente, blz?

Fique à vontade pra tirar qualquer dúvida que possa surgir!



E lembre-se! Um bom software é um software bem testado.

Suba o código no seu github e mande o link, clicando no botão abaixo.

# Getting Started

## Requirements

Run DynamoDB at local

```
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```

In the DynamoDB console at : `localhost:8000/shell/`

Execute:
```
AWS.config.update({
  region: "sa-east-1",
  endpoint: 'http://localhost:8000',
  accessKeyId: "jsa",
  secretAccessKey: "javasampleapproach"
});

var dynamodb = new AWS.DynamoDB();
    var params = {
        TableName : "Planet",
        KeySchema: [
            { AttributeName: "Id", KeyType: "HASH"}
        ],
        AttributeDefinitions: [
            { AttributeName: "Id", AttributeType: "S" },
            { AttributeName: "Name", AttributeType: "S" },
            { AttributeName: "Climate", AttributeType: "S" },
            { AttributeName: "Terrain", AttributeType: "S" },
            { AttributeName: "AmountOfViewsInFilms", AttributeType: "I" }
        ],
        ProvisionedThroughput: {
            ReadCapacityUnits: 5,
            WriteCapacityUnits: 5
        },
        GlobalSecondaryIndexes: [{
          IndexName: "TweetIndex",
          KeySchema: [
              {
                  AttributeName: "Text",
                  KeyType: "HASH"
                  },
              {
                  AttributeName: "CreatedAt",
                  KeyType: "RANGE"
              }
          ],
          Projection: {
              ProjectionType: "ALL"
              },
          ProvisionedThroughput: {
              ReadCapacityUnits: 5,
              WriteCapacityUnits: 5
              }
          }]
    };

    dynamodb.createTable(params, function(err, data) {
        if (err) {
            ppJson(err);
        } else {
            ppJson(data);
        }
    });
```


Run the app `mvn spring-boot:run`


The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application defines following REST APIs

```

1. POST /planets - Create a new Planet

2. GET /planets/db - Get All Planets from DB

3. GET /planets/api - Get All Planets from API

4. GET /planets/{name} - Retrieve a Planet by Name

5. GET /planets/{id} - Retrieve a Tweet by Id

6. DELETE /planets/{id} - Delete a Tweet

```

## Running integration tests

The project also contains integration tests for all the Rest APIs. For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.
