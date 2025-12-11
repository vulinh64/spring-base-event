--liquibase formatted sql
--changeset vulinh:20251209-0001
CREATE TABLE new_post
(
    id                UUID NOT NULL PRIMARY KEY,
    title             VARCHAR(200),
    excerpt           TEXT,
    `timestamp`       DATETIME(6),
    created_date_time DATETIME(6),
    updated_date_time DATETIME(6),
    action_user_id    UUID,
    action_username   VARCHAR(100)
);