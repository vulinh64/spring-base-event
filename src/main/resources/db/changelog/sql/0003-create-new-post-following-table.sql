--liquibase formatted sql
--changeset vulinh:20251209-0002
CREATE TABLE new_post_following
(
    post_id           UUID NOT NULL,
    action_user_id    UUID NOT NULL,
    title             VARCHAR(200),
    excerpt           TEXT,
    action_username   VARCHAR(100),
    created_date_time DATETIME(6),
    updated_date_time DATETIME(6),
    event_id          UUID,
    `timestamp`       DATETIME(6),
    PRIMARY KEY (post_id, action_user_id)
);