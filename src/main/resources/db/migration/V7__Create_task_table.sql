CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE task
(
    id                   UUID         NOT NULL,
    name                 VARCHAR(100) NOT NULL,
    reference_repository VARCHAR(100) NOT NULL,
    CONSTRAINT pk_task PRIMARY KEY (id)
);