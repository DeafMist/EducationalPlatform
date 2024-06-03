--liquibase formatted sql

-- changeset DeafMist:1
CREATE TABLE profile
(
    profile_id      BIGSERIAL PRIMARY KEY,
    login           VARCHAR(200)                           NOT NULL,
    date_registered TIMESTAMP WITH TIME ZONE               NOT NULL
);

CREATE TABLE post
(
    post_id         BIGSERIAL PRIMARY KEY,
    title           VARCHAR(200)                           NOT NULL,
    content         TEXT                                   NOT NULL,
    profile_id      BIGINT REFERENCES profile (profile_id) NOT NULL,
    date_added      TIMESTAMP WITH TIME ZONE               NOT NULL
);

CREATE TABLE comment
(
    comment_id      BIGSERIAL PRIMARY KEY,
    post_id         BIGINT REFERENCES post (post_id)       NOT NULL,
    profile_id      BIGINT REFERENCES profile (profile_id) NOT NULL,
    date_commented  TIMESTAMP WITH TIME ZONE               NOT NULL
);

CREATE TABLE course
(
    id              BIGSERIAL PRIMARY KEY,
    author          VARCHAR(255)                           NOT NULL,
    title           VARCHAR(255)                           NOT NULL
);

CREATE TABLE lesson
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);
