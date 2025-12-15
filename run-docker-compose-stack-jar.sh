#!/bin/bash

# Check if Docker daemon is running
docker info >/dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Error: Docker daemon is not running."
    echo "Please start Docker Desktop or Docker service and run this script again."
    exit 1
fi

docker compose down
docker rmi --force spring-base-event:1.0.0

chmod +x ./create-data-classes.sh

./create-data-classes.sh

./mvnw clean verify -DskipTests

docker compose -f docker-compose-1.yml up --detach