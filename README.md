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

# Run a Docker container from image named product-catalog.
# Define database server, username and password as environment variables.
docker run  -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://<DATABASE_SERVER>:5432/<DATABASE_NAME>
            -e KUMULUZEE_DATASOURCES0_USERNAME=<DATABASE_USERNAME>
            -e KUMULUZEE_DATASOURCES0_PASSWORD=<DATABASE_PASSWORD>
            -p 8080:8080
            product-catalog
```

We can use the [ElephantSQL](https://www.elephantsql.com/) PostgreSQL database as a Service.

## TODO

* [ ] Add search
* [ ] Add Kubernetes configuration
* [ ] Create an organization on Docker Hub