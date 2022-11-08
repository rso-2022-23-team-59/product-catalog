# Products microservice

## Prerequisites

```bash
docker run -d --name pg-products -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=products -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
java -jar .\api\target\product-catalog-api-1.0.0-SNAPSHOT.jar
```
Available at: [localhost:8080/v1/products](http://localhost:8080/v1/products)
