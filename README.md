````markdown
# Spring Boot REST API

A Spring Boot REST API built with **Java 17**, **Gradle**, **PostgreSQL 16**, and **Docker Compose**.

## Prerequisites

Before running the project, ensure you have the following installed:

- Java 17
- Docker
- Docker Compose
- Git

Verify your installation:

```bash
java -version
docker --version
docker compose version
```

---

## Project Structure

```text
.
├── src/
├── Dockerfile
├── compose.yaml
├── .env.example
├── .gitignore
├── README.md
├── build.gradle
├── settings.gradle
├── gradlew
└── gradlew.bat
```

---

## Configure Environment Variables

Copy the example environment file.

### Linux / macOS

```bash
cp .env.example .env
```

### Windows (PowerShell)

```powershell
Copy-Item .env.example .env
```

Update the values inside the `.env` file.

Example:

```properties
DB_HOST=postgres
DB_PORT=5432

POSTGRES_DB=example_db
POSTGRES_USER=postgres_username
POSTGRES_PASSWORD=postgres_password

SPRING_PORT=8081
```

## Build the Application

### Linux / macOS

```bash
./gradlew clean build
```

### Windows

```bash
gradlew.bat clean build
```

---

## Run with Docker Compose

Build and start the Spring Boot application and PostgreSQL 16:

```bash
docker compose up --build
```

Run in detached mode:

```bash
docker compose up -d --build
```

---

## Verify Running Containers

```bash
docker ps
```

## Access the Application

Spring Boot API:

```
http://localhost:8081

Spring Boot API with swagger:

http://localhost:8081/swagger-ui/index.html#/
```


---

## Technologies Used

- Java 17
- Spring Boot
- Gradle
- PostgreSQL 16
- Docker
- Docker Compose


##Working Flow of API
1. Register Device
2. Register group and members
3. Create Order
4. Assign Device with order and device
5. create alert which comes from device
6. acknowledge 
7. return device
