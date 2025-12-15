@echo off
SETLOCAL EnableDelayedExpansion

SET RABBITMQ_CONTAINER_NAME=rabbitmq
SET RABBITMQ_NAME=rabbitmq
SET RABBITMQ_TAG=4.2.1-management-alpine
SET RABBITMQ_IMAGE=!RABBITMQ_NAME!:!RABBITMQ_TAG!
SET RABBITMQ_COMMAND=docker run --detach --name !RABBITMQ_CONTAINER_NAME! --hostname rabbitmq -e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=123456 -p 5672:5672 -p 15672:15672 !RABBITMQ_IMAGE!

echo Checking RabbitMQ container [%RABBITMQ_CONTAINER_NAME%]...
docker ps -a | findstr /C:"!RABBITMQ_CONTAINER_NAME!" >nul
if errorlevel 1 (
    echo Container [%RABBITMQ_CONTAINER_NAME%] not existed, creating...
    !RABBITMQ_COMMAND!
) else (
    docker ps | findstr /C:"!RABBITMQ_CONTAINER_NAME!" >nul
    if errorlevel 1 (
        echo Container [%RABBITMQ_CONTAINER_NAME%] stopped, restarting...
        docker start !RABBITMQ_CONTAINER_NAME!
    ) else (
        echo Container [%RABBITMQ_CONTAINER_NAME%] is already running...
    )
)

SET MARIADB_CONTAINER_NAME=mariadb
SET MARIADB_NAME=mariadb
SET MARIADB_TAG=12.1.2-noble
SET MARIADB_IMAGE=!MARIADB_NAME!:!MARIADB_TAG!
SET MARIADB_COMMAND=docker run --detach --name !MARIADB_CONTAINER_NAME! -e MARIADB_ROOT_PASSWORD=123456 -e MARIADB_DATABASE=myspringeventdatabase -p 3306:3306 -v mariadb-volume:/var/lib/mysql !MARIADB_IMAGE! --lower_case_table_names=1

echo Checking MariaDB container [%MARIADB_CONTAINER_NAME%]...
docker ps -a | findstr /C:"!MARIADB_CONTAINER_NAME!" >nul
if errorlevel 1 (
    echo Container [%MARIADB_CONTAINER_NAME%] not existed, creating...
    !MARIADB_COMMAND!
) else (
    docker ps | findstr /C:"!MARIADB_CONTAINER_NAME!" >nul
    if errorlevel 1 (
        echo Container [%MARIADB_CONTAINER_NAME%] stopped, restarting...
        docker start !MARIADB_CONTAINER_NAME!
    ) else (
        echo Container [%MARIADB_CONTAINER_NAME%] is already running...
    )
)

:: Initialize data dependency
call ./create-data-classes.cmd

ENDLOCAL
