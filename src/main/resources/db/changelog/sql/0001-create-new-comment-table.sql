--liquibase formatted sql
--changeset vulinh:20251209-0000
CREATE TABLE new_comment
(
    id              UUID NOT NULL PRIMARY KEY,
    post_id         UUID,
    title           VARCHAR(255),
    content         TEXT,
    action_user_id  UUID,
    action_username VARCHAR(255),
    timestamp       TIMESTAMP,
    created_date    TIMESTAMP
);