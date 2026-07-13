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

POSTGRES_DB=hospital_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

SPRING_PORT=8081
```

> **Note:** Do not commit your `.env` file to Git. Only commit `.env.example`.

---

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

Example output:

```text
CONTAINER ID   IMAGE          PORTS
xxxxxxx        postgres:16    0.0.0.0:5432->5432/tcp
xxxxxxx        your-app       0.0.0.0:8081->8081/tcp
```

---

## Access the Application

Spring Boot API:

```
http://localhost:8081
```

PostgreSQL:

| Property | Value |
|----------|-------|
| Host | localhost |
| Port | 5432 |
| Database | Defined by `POSTGRES_DB` |
| Username | Defined by `POSTGRES_USER` |
| Password | Defined by `POSTGRES_PASSWORD` |

---

## Useful Docker Commands

### View running containers

```bash
docker ps
```

### View logs

```bash
docker compose logs
```

### Follow logs

```bash
docker compose logs -f
```

### Stop containers

```bash
docker compose down
```

### Stop containers and remove volumes

> **Warning:** This removes the PostgreSQL database data stored in Docker volumes.

```bash
docker compose down -v
```

### Rebuild after code changes

```bash
./gradlew clean build
docker compose up --build
```

---

## Technologies Used

- Java 17
- Spring Boot
- Gradle
- PostgreSQL 16
- Docker
- Docker Compose

---

## Notes

- Docker Compose automatically reads environment variables from the `.env` file.
- The Spring Boot application connects to PostgreSQL using the Docker service name (`postgres`).
- Ensure Docker is running before starting the application.
- If you change the application port, update both `server.port` in `application.properties` and the port mapping in `compose.yaml`.
````
