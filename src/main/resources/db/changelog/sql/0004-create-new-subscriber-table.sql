--liquibase formatted sql
--changeset vulinh:20251209-0003
CREATE TABLE new_subscriber
(
    id                  UUID NOT NULL PRIMARY KEY,
    subscriber_user_id  UUID,
    subscriber_username VARCHAR(255),
    action_user_id      UUID,
    action_username     VARCHAR(255),
    timestamp           TIMESTAMP,
    created_date        TIMESTAMP
);