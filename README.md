# Indexmonitor Authorization Server

The Indexmonitor Authorization Server is powered by Spring Boot and Spring Authorization Server projects, and is developed and contributed as a part of the Indexmonitor project. It provides OAuth 2.1 authorization flow, exposes REST API, and includes built-in MVC templates powered by the Thymeleaf Engine and Bootstrap 5.0 styling.

## Feature support

### API 

The server supports the following OAuth 2.1 features: 

* OAuth 2.1 Clients and Scopes management
* OAuth 2.1 supported grant types: AUTHORIZATION_CODE, CLIENT_CREDENTIALS, REFRESH_TOKEN
* OAuth 2.1 supported authentication methods: AUTH_BASIC, AUTH_POST, NONE
* User implicit registration and management
* Authorities and roles management
* Determining user level of access to protected resource services by utilizing authorities and roles claims

### MVC

The server provides several built-in HTML pages built with Spring MVC: 

* User registration flow 
* User email confirmation flow
* User login flow
* User password reset flow

## Issues

The project is still in development mode and has a number of unsolved problems: 

* The user personal area page is not yet presented
* User logout request does not clean up the session on the server

## UI

The Indexmonitor team has developed a UI powered by Angular 15 for the authorization server, providing a clean user experience. You can find this under the Indexmonitor Authorization Client project.

## Architecture

The Authorization Server follows web development best practices by utilizing Clean Architecture, DDD pattern, and REST API. The entire project architecture is separated into four modules, each containing similarly structured sub-projects that are maintained by the Gradle Build System. 

### Standard subproject layout

- Root project
  - Sub-project
    - Adapter
      - In
        - API
        - MVC
        - ...
      - Out 
        - Persistence
        - Email
        - ...
    - Application
    - Domain

From the bottom to top, the sub-project dependency tree is growing, with the upper projects depending on the lower ones. You can find more information and understand the benefits of this solution in the book "Get your hands dirty on Clean Architecture" by Tom Hombergs.

### Application subproject structure

The Application sub-project is the main project and contains core logic implementation. It is designed with Clean Architecture and DDD pattern in mind and has a single-side dependency on Spring Boot to utilize IoC container and dependency injection principles. 

![Application](https://user-images.githubusercontent.com/60474448/227878104-85ffe0ce-65e6-42a3-9fdd-0c4a52a4605f.png)

# Building from Source

By following these steps, you will be ready to run the server:

1. Clone the repository.
2. Set up the 'Run/Debug Configuration' in your IDE.
   * The entire application runs as a single Spring context. The main method is located under the configuration:application subproject in the Application.java class. Configure it as the entry point.
3. Configure the 'application-env.properties' file.
   - Under the configuration:application subproject in the resource folder, you will find the application.properties file. It has some preset data and uses the spring.profiles.active=dev property that you will not find because it contains sensitive data. You need to provide your configuration in application.properties (not recommended) or create a separate file, application-dev.properties (recommended).
   - Recommended way: create the application-dev.properties file in the resource folder and insert this code. Provide the required fields.
  ```properties
  #JPA and DataBase connection settings
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  spring.datasource.url=jdbc:postgresql://localhost:8020/auth
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  spring.jpa.generate-ddl=true
  spring.jpa.hibernate.ddl-auto=update

  #CORS origin allowed list
  app.cors.origin.list=http://localhost:4200

  #Email service settings
  app.email.externalDomainAddress=http://localhost:8080
  #SMTP server connection settings
  spring.mail.username=
  spring.mail.password=
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.protocol=smtp
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true

  #Inital data seeding settings
  app.data.seed.user.enable = true
  app.data.seed.user.username = manager
  app.data.seed.user.password = manager1234
  app.data.seed.user.givenName = manager
  app.data.seed.user.familyName = manager
  app.data.seed.user.email = manager@manager.com

  # Authorization Server UI client
  app.data.seed.client.enable = true
  app.data.seed.client.domain = http://localhost:4200
  ```
  4. Make sure your database connection and SMTP server user are configured correctly, and then run the application.

