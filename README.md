# Quickstart the Application

## Table of Contents

<!-- TOC -->
* [Quickstart the Application](#quickstart-the-application)
  * [Table of Contents](#table-of-contents)
  * [Running the Container Stack for Local Development](#running-the-container-stack-for-local-development)
    * [Prerequisites](#prerequisites)
    * [Required External Dependency](#required-external-dependency)
    * [Dependency Version](#dependency-version)
    * [Running the Required Containers](#running-the-required-containers)
  * [Running the Compose Stack](#running-the-compose-stack)
<!-- TOC -->

## Running the Container Stack for Local Development

### Prerequisites

* JDK 25+ (for coding and debugging, but not necessary if running the service in containers)
* Docker Desktop

### Required External Dependency

The project uses the external dependency [spring-base-commons](https://github.com/vulinh64/spring-base-commons).

* For Windows, run [this script](./create-data-classes.cmd)

* For Linux, run [this script](./create-data-classes.sh)

    * Run `chmod +x ./create-data-classes.sh` if you don't have permission to execute the shell file.

Check if your `pom.xml` contains these lines in the `<dependencies>` section:

```xml
<dependency>
  <groupId>com.vulinh</groupId>
  <artifactId>spring-base-commons</artifactId>
  <version>${spring-base-commons.version}</version>
</dependency>
```

### Dependency Version

This project uses `spring-base-commons` as an external dependency (see above). If you want to change the version, check the following locations:

* [`pom.xml` file](./pom.xml) - find the property `spring-base-commons.version`.

* Environment variable `SPRING_BASE_COMMONS_VERSION` in:

    * [Dockerfile](./Dockerfile)

    * [create-data-classes.cmd](./create-data-classes.cmd) (Windows)

    * [create-data-classes.sh](./create-data-classes.sh) (Linux)

    * [GitHub Actions workflows](./.github/workflows/unit-test.yml) (the variable is `${{ env.SPRING_BASE_COMMONS_VERSION }}`)

### Running the Required Containers

You can run [this script (Windows only)](./initialize-postgres-keycloak-rabbitmq.cmd) or [this script (Linux only)](./initialize-postgres-keycloak-rabbitmq.sh), and it will start the required containers for local development: PostgreSQL and Keycloak.

> Both scripts have already handled the external dependency for you. See the [Required External Dependency](#required-external-dependency) section for more information.

## Running the Compose Stack

You can run [this script (Windows only)](./run-docker-compose-stack.cmd), or [this script (Linux only)](./run-docker-compose-stack.sh) and it will build the service image and start the containers for you.

> Again, both scripts have already handled the external dependency for you.

If you want to make use of host OS to build the images (to speed up the build process), run [this script (Windows only)](./run-docker-compose-stack-jar.cmd) or [this script (Linux only)](./run-docker-compose-stack-jar.sh) instead.