## 1.How to compile/run.

    mvnw clean install - remove old build and rebuild project.
    mvnw spring-boot:run - run spring boot application.

## 2.How to run tests.

    mvnw test - run all unit tests.

## 3.User credentials.
There are 2 predefined users in the system.

    Root - Full access.
    username - root.
    password - root.

    Customer - able to search and order products.
    username - customer.
    password - customer.

## 4.Test REST API.
For convenience manual testing of api can be performed using swagger. Swagger UI is available at path "localhost:8080/swagger-ui.html".

## 5.In memory database.
The h2 database client is available at path "localhost:8080/h2-console".

    username - root.
    password - root.
