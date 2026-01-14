-- enable postgres database feature in ./compose.yaml first

CREATE SCHEMA "keycloak";
CREATE USER keycloak_admin PASSWORD 'password';

GRANT CONNECT ON DATABASE rest_keycloack_service_db TO keycloak_admin;
GRANT USAGE, CREATE ON SCHEMA keycloak TO keycloak_admin;