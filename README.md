# Products microservice

## Local development

Start a PostgreSQL database in a Docker container. The database username, password and database name must mach the
configuration in `api/src/main/resources/config.yaml`.

```bash
docker run  -d --name pg-products
            -e POSTGRES_USER=dbuser
            -e POSTGRES_PASSWORD=postgres
            -e POSTGRES_DB=products
            -p 5432:5432
            postgres:13
```

Then compile the project using Maven and run the generated JAR file using the following commands.

```bash
mvn clean package
java -jar .\api\target\product-catalog-api-1.0.0-SNAPSHOT.jar
```

Available at: [localhost:8080/v1/products](http://localhost:8080/v1/products)

## Docker

```bash
# Compile the project to jar compressed file and build a Docker image named product-catalog.
docker build -t product-catalog .

# Create a new network
docker network create --driver bridge price-comparison-network

# Run a postgres database in container named pg-products in defined network.
# Since we have defined both the container name and network, we can connect to this instance
# using the following connection string "jdbc:postgresql://pg-products:5432/products"
docker run  -d --name pg-products
            -e POSTGRES_USER=dbuser
            -e POSTGRES_PASSWORD=postgres
            -e POSTGRES_DB=products
            -p 5432:5432
            --network price-comparison-network
            postgres:13

# Run a Docker container from image named product-catalog.
# Define database server, username and password as environment variables.
docker run  -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-products:5432/products
            -e KUMULUZEE_DATASOURCES0_USERNAME=dbuser
            -e KUMULUZEE_DATASOURCES0_PASSWORD=postgres
            --name product-catalog
            -p 8080:8080
            --network price-comparison-network
            product-catalog
```

We can use the [ElephantSQL](https://www.elephantsql.com/) PostgreSQL database as a Service.

## Configuration

## Consul configuration

For currency exchange to work, the following values must be set in Consul configuration server.

```
environments/dev/services/product-catalog-microservice/1.0.0/config/currency-exchange/currency-exchange-host
environments/dev/services/product-catalog-microservice/1.0.0/config/currency-exchange/currency-exchange-api-key
environments/dev/services/product-catalog-microservice/1.0.0/config/currency-exchange/default-currency
```