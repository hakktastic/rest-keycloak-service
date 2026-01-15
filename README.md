# 🛡️ Rest Keycloak Service
A lightweight Java REST service demonstrating integration with Keycloak for authentication and authorization. It provides protected REST endpoints that delegate to a Keycloak server for access token validation and user identity information.

## 🔑 Keycloak
This project includes a pre-configured Keycloak realm (rest-keycloack-service) so you don’t need to manually set up users or clients. Everything is ready to use out of the box — just start the REST service.

Following users & roles are pre-configured:

| Username  | Email                                                   | Roles | Password |
| --------- | ------------------------------------------------------- | ----- | -------- |
| `user1`   | [user1@hakktastic.nl](mailto:user1@hakktastic.nl)       | USER  | password |
| `admin1`  | [admin1@hakktastic.nl](mailto:admin1@hakktastic.nl)     | ADMIN | password |
| `orphan1` | [orphan11@hakktastic.nl](mailto:orphan11@hakktastic.nl) | none  | password |



## 🚀 Run

Read the [Run.md](RUN.md) file to configure and run this application.



## 💻 Local links
- Health Checks - [Actuator](http://localhost:9098/actuator/health)
- Keycloak Admin Console - [Keycloak](http://localhost:8081/admin/master/console/)
- 
> This file was generated with the assistance of an AI tool