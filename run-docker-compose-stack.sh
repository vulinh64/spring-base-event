#!/bin/bash

# Check if Docker daemon is running
if ! docker info > /dev/null 2>&1; then
    echo "Error: Docker daemon is not running."
    echo "Please start Docker service and run this script again."
    exit 1
fi

docker compose down
docker rmi --force spring-base:1.0.0
docker compose up --detach