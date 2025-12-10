--liquibase formatted sql
--changeset vulinh:20251209-0000
CREATE TABLE new_comment
(
    id                  UUID        NOT NULL PRIMARY KEY,
    post_id             UUID,
    title               VARCHAR(255),
    content             TEXT,
    status              VARCHAR(15) NOT NULL DEFAULT 'RECEIVED',
    retry_count         TINYINT UNSIGNED NOT NULL DEFAULT 0,
    action_user_id      UUID,
    action_username     VARCHAR(255),
    timestamp           TIMESTAMP,
    created_date        TIMESTAMP,
    last_processed_date TIMESTAMP
);