--liquibase formatted sql
--changeset vulinh:20251209-0001
CREATE TABLE new_post
(
    id              UUID NOT NULL PRIMARY KEY,
    post_id         UUID,
    title           VARCHAR(255),
    excerpt         TEXT,
    action_user_id  UUID,
    action_username VARCHAR(255),
    timestamp       TIMESTAMP,
    created_date    TIMESTAMP
);