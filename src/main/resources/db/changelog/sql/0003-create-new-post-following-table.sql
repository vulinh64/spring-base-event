--liquibase formatted sql
--changeset vulinh:20251209-0002
CREATE TABLE new_post_following
(
    post_id           UUID        NOT NULL,
    title             VARCHAR(200),
    excerpt           TEXT,
    action_user_id    UUID        NOT NULL,
    action_username   VARCHAR(100),
    status            VARCHAR(20) NOT NULL DEFAULT 'RECEIVED',
    retry_count       INT         NOT NULL DEFAULT 0,
    failure_reason    TEXT,
    `timestamp`       DATETIME(6),
    created_date_time DATETIME(6),
    updated_date_time DATETIME(6),
    PRIMARY KEY (post_id, action_user_id)
);