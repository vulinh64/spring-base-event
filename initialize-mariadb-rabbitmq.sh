#!/bin/bash

RABBITMQ_CONTAINER_NAME="rabbitmq"
RABBITMQ_NAME="rabbitmq"
RABBITMQ_TAG="4.2.1-management-alpine"
RABBITMQ_IMAGE="$RABBITMQ_NAME:$RABBITMQ_TAG"
RABBITMQ_COMMAND="docker run --detach --name $RABBITMQ_CONTAINER_NAME --hostname rabbitmq -e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=123456 -p 5672:5672 -p 15672:15672 $RABBITMQ_IMAGE"

echo "Checking RabbitMQ container [$RABBITMQ_CONTAINER_NAME]..."
if ! docker ps -a --format '{{.Names}}' | grep -w "$RABBITMQ_CONTAINER_NAME" > /dev/null; then
    echo "Container [$RABBITMQ_CONTAINER_NAME] not existed, creating..."
    eval "$RABBITMQ_COMMAND"
else
    if ! docker ps --format '{{.Names}}' | grep -w "$RABBITMQ_CONTAINER_NAME" > /dev/null; then
        echo "Container [$RABBITMQ_CONTAINER_NAME] stopped, restarting..."
        docker start "$RABBITMQ_CONTAINER_NAME"
    else
        echo "Container [$RABBITMQ_CONTAINER_NAME] is already running..."
    fi
fi

MARIADB_CONTAINER_NAME="mariadb"
MARIADB_NAME="mariadb"
MARIADB_TAG="12.1.2-noble"
MARIADB_IMAGE="$MARIADB_NAME:$MARIADB_TAG"
MARIADB_COMMAND="docker run --detach --name $MARIADB_CONTAINER_NAME -e MARIADB_ROOT_PASSWORD=123456 -e MARIADB_DATABASE=myspringeventdatabase -p 3306:3306 -v mariadb-volume:/var/lib/mysql $MARIADB_IMAGE --lower_case_table_names=1"

echo "Checking MariaDB container [$MARIADB_CONTAINER_NAME]..."
if ! docker ps -a --format '{{.Names}}' | grep -w "$MARIADB_CONTAINER_NAME" > /dev/null; then
    echo "Container [$MARIADB_CONTAINER_NAME] not existed, creating..."
    eval "$MARIADB_COMMAND"
else
    if ! docker ps --format '{{.Names}}' | grep -w "$MARIADB_CONTAINER_NAME" > /dev/null; then
        echo "Container [$MARIADB_CONTAINER_NAME] stopped, restarting..."
        docker start "$MARIADB_CONTAINER_NAME"
    else
        echo "Container [$MARIADB_CONTAINER_NAME] is already running..."
    fi
fi