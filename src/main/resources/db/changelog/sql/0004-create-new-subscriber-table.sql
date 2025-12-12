--liquibase formatted sql
--changeset vulinh:20251209-0003
CREATE TABLE new_subscriber
(
    subscribed_user_id  UUID,
    action_user_id      UUID        NOT NULL,
    action_username     VARCHAR(100),
    subscribed_username VARCHAR(100),
    status              VARCHAR(20) NOT NULL DEFAULT 'RECEIVED',
    created_date_time   DATETIME(6),
    updated_date_time   DATETIME(6),
    event_id            UUID,
    `timestamp`         DATETIME(6),
    PRIMARY KEY (subscribed_user_id, action_user_id)
);