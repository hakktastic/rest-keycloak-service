# 🚀 Run the Rest Keycloak Service
This guide explains how to start and run the Rest Keycloak Service locally using Maven, Docker Compose, or IntelliJ HTTP Requests. Everything is pre-configured, so you can start and go without manual Keycloak setup.

## 🚦 Prerequisites

Make sure you have the following installed:
- Java 25 or higher
- Maven 3.9+
- Docker & Docker Compose
- IDE (e.g., IntelliJ IDEA, VS Code – optional but helpful)

## 💾 Clone the Repository

```shell
   git https://github.com/hakktastic/rest-keycloak-service.git
   cd rest-keycloak-service
   git checkout master
```

## 🛠️ Build the Project

```shell
   mvn clean install
```

## ⚡Start Application

```shell      
  # start the application
  mvn spring-boot:run
```
## 🧪 Test the service
All HTTP requests are located in the ./http-requests directory. These are ready to use and already configured for the preloaded Keycloak realm (rest-keycloack-service).

### Using IntelliJ
1. Open IntelliJ and navigate to the [/http-requests](./http-requests) directory.
2. Open the .http files:
   - [http-requests/api-v1-requests-admin1.http](http-requests/api-v1-requests-admin1.http)
   - [http-requests/api-v1-requests-user1.http](http-requests/api-v1-requests-user1.http)
   - [http-requests/api-v1-requests-orphan1.http](http-requests/api-v1-requests-orphan1.http)
3. Send requests directly from the editor 
   - Access tokens are already configured for user1, admin1, and orphan1
   - No manual token generation is needed — just run the requests

## 🛑 Stop Application

```shell  
  # stop the application
  mvn spring-boot:stop
```

> This file was generated with the assistance of an AI tool