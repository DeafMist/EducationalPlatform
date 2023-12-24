--liquibase formatted sql

-- changeset DeafMist:1
CREATE TABLE IF NOT EXISTS profile
(
    profile_id      BIGINT PRIMARY KEY,
    login           VARCHAR(200)             NOT NULL,
    date_registered TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS post
(
    post_id    BIGINT PRIMARY KEY,
    title      VARCHAR(200)                           NOT NULL,
    content    TEXT                                   NOT NULL,
    profile_id BIGINT REFERENCES profile (profile_id) NOT NULL,
    date_added TIMESTAMP WITH TIME ZONE               NOT NULL
);

CREATE TABLE IF NOT EXISTS comment
(
    comment_id     BIGINT PRIMARY KEY,
    post_id        BIGINT REFERENCES post (post_id)       NOT NULL,
    profile_id     BIGINT REFERENCES profile (profile_id) NOT NULL,
    date_commented TIMESTAMP WITH TIME ZONE               NOT NULL
);