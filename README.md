# Spring Boot Proxy & SSL Demo

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Memija_spring-boot-demo&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Memija_spring-boot-demo)

This is a Spring Boot application designed to demonstrate and test proxy connections and SSL client certificate configurations. It provides endpoints to verify application health and proxy connectivity.

## Features

- **Health Check**: Simple endpoint to verify the application is running.
- **Proxy Connectivity**: Specific endpoint to test outgoing connections via a configured proxy.
- **Global Proxy Configuration**: Sets JVM-wide proxy settings with authentication.
- **SSL Configuration**: Sets JVM-wide SSL TrustStore and KeyStore settings.

## Prerequisites

- Java 8 or higher
- Maven

## Configuration

The application is configured via `src/main/resources/application.properties`. You should update these values to match your environment.

### Proxy Settings

```properties
proxy.hostname=your-proxy-hostname
proxy.port=8080
proxy.username=your-username
proxy.password=your-password
proxy.url-string=http://target-url-to-test.com
```

### SSL Settings

```properties
ssl.trust-store=path/to/trustStore
ssl.trust-store-password=trustStorePassword
ssl.key-store=path/to/clientCertificate
ssl.key-store-password=clientCertificatePassword
```

## Running the Application

To run the application using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Or build and run the JAR:

```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## API Endpoints

### 1. Health Check
Checks if the application is up and running.

- **URL**: `/healthcheck`
- **Method**: `GET`
- **Response**: `true`

### 2. Proxy Check
Tests the connection to `proxy.url-string` using the configured proxy settings.

- **URL**: `/proxycheck`
- **Method**: `GET`
- **Response**: The content of the target URL if successful.

## Project Structure

- `src/main/java/com/memija/demo/configurations`: Contains classes for setting up global Proxy (`ProxyConnectionWithAuthorization`) and SSL (`ClientCertificate`) system properties.
- `src/main/java/com/memija/demo/controllers`: REST controllers for health and proxy checks.
- `src/main/java/com/memija/demo/models`: Data models.
