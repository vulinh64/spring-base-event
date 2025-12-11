--liquibase formatted sql
--changeset vulinh:20251209-0003
CREATE TABLE new_subscriber
(
    id                  UUID        NOT NULL PRIMARY KEY,
    subscriber_user_id  UUID,
    subscriber_username VARCHAR(255),
    status              VARCHAR(15) NOT NULL DEFAULT 'RECEIVED',
    retry_count         TINYINT UNSIGNED NOT NULL DEFAULT 0,
    action_user_id      UUID,
    action_username     VARCHAR(255),
    timestamp           DATETIME,
    created_date        DATETIME,
    last_processed_date DATETIME,
    failure_reason      TEXT
);