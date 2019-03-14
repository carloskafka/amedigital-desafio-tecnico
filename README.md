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
