## Environment
- Java version: 17
- Maven version: 3.*
- Spring Boot version: 3.2.2

## Data
Example of a Covid data JSON object:
```json
{
    "id":1,
    "country":"MyCountry",
    "active":574,
    "death":45,
    "recovered": 7000
}
```

## Requirements
In this project, data related to covid are provided for many countries. Note that all the data are virtual.

You have to implement `/covid` REST endpoint for following 3 operations.


`GET` request to `/covid/{id}`:
* return the covid entry with given id and status code 200
* if the requested covid entry doesn't exist, then status code 404 should be returned

`GET` request to `/covid/top5?by={by}`:
* return the top 5 covid entries sorted by given field and status code 200.
* for example: `/covid/top5?by=death` gives total deaths
* if give `by` is invalid attribute, return status code 400

`GET` request to `/covid/total?by={by}`:
* return the total value summed by given field and status code 200
* for example: `/covid/total?by=active` gives total active cases
* if give `by` is invalid attribute, return status code 400
 
`Test writing`
In addition to implementing the REST endpoints, you are supposed to write several(at least 3) unit tests to test your implementation.


## Commands
- run: 
```bash
mvn clean spring-boot:run
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```