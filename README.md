#RestValidator

###How to start:
Запустить spring application через gradle  <br/>

По умолчанию сервис стартует на порту 8082 (задается в application.properties)  <br/>

###Методы:<br/>
* /delete DELETE - удаляет схему, которая указана в гет-параметре name, например http://localhost:8082/delete?name=example<br/>
* /add POST - добавляет схему, если такая уже существует изменяет ее, например http://localhost:8082/add?name=111222<br/>
* /validate POST - валидирует json переданный в body по схеме указанной в гет параметре name http://localhost:8082/validate?name=111
* /getScheme GET -  json отдает схему, которая казана в гет параметре name  http://localhost:8082/getscheme?name=example
* /getSchemes GET -  json отдает списком названия всех имеющихся в валидаторе схем http://localhost:8082/getschemes



###Example:<br/>
`request` with curl:

    curl -d '{"property1": 1, "property2": "str", "property3": true}' -X POST "http://localhost:8082/validate?name=testscheme1" -H "Content-Type: application/json"

response:

    {
      "id":7,
      "valid":true,
      "details":"No log found."
    }
