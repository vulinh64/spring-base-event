# spring-base-event

## Description

The supporting service for [spring-base](https://github.com/vulinh64/spring-base).

## Requirements for local debugging

| Component            | Notes                                                                                                                |
|----------------------|----------------------------------------------------------------------------------------------------------------------|
| JDK 25               | Required to build and run.                                                                                           |
| Maven                | The wrapper (`mvnw` / `mvnw.cmd`) is included — no global install needed.                                            |
| PostgreSQL           | Application database.                                                                                                |
| RabbitMQ             | Message broker (key-rotation events, outbound notifications).                                                        |
| Authorization server | [vulinh64/spring-base-auth](https://github.com/vulinh64/spring-base-auth) — for initializing database schemas.       |
| Commons library      | [vulinh64/spring-base-commons](https://github.com/vulinh64/spring-base-commons) — shared data classes and utilities. |

## Setup

### Step 1 — Install the commons library

The commons artifact is published as a GitHub release and must be installed into your local Maven repository before the project can resolve its dependencies.

- **Windows:** run [`create-data-classes.cmd`](./create-data-classes.cmd)

- **Linux / macOS:** run [`create-data-classes.sh`](./create-data-classes.sh)

### Step 2 — Initialize PostgreSQL and RabbitMQ containers

Only needed when running this service **standalone**. In normal operation the auth server is responsible for provisioning the shared schema (for itself, for this project, and for other downstream services), so this step is usually already done by the time you start `spring-base`.

- **Windows:** run [`initialize-postgres-rabbitmq.cmd`](./initialize-postgres-rabbitmq.cmd)

- **Linux / macOS:** run [`initialize-postgres-rabbitmq.sh`](./initialize-postgres-rabbitmq.sh)

## Configuration

Sensible defaults are already wired up in [`application.yaml`](./src/main/resources/application.yaml), so the app boots out of the box against the containers from Step 2. Override any of the following environment variables when the defaults do not match your setup:

| Environment variable      | Default                                   | Remark                             |
|---------------------------|-------------------------------------------|------------------------------------|
| `SERVER_PORT`             | `8088`                                    | HTTP port this service listens on. |
| `POSTGRES_HOST`           | `localhost`                               | PostgreSQL host.                   |
| `POSTGRES_PORT`           | `5432`                                    | PostgreSQL port.                   |
| `POSTGRES_DB`             | `spring-base`                             | Database name.                     |
| `POSTGRES_USER`           | `postgres`                                | Database user.                     |
| `POSTGRES_PASSWORD`       | `123456`                                  | Database password.                 |
| `RABBITMQ_HOST`           | `localhost`                               | RabbitMQ host.                     |
| `RABBITMQ_PORT`           | `5672`                                    | RabbitMQ AMQP port.                |
| `RABBITMQ_USERNAME`       | `rabbitmq`                                | RabbitMQ user.                     |
| `RABBITMQ_PASSWORD`       | `123456`                                  | RabbitMQ password.                 |
| `VIRTUAL_THREADS_ENABLED` | `true`                                    | Enable virtual thread support.     |