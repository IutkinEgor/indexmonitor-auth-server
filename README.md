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

![Architecture](https://user-images.githubusercontent.com/60474448/227837258-55ad671a-eef7-4d6a-b1ce-d71a564572e4.svg)
