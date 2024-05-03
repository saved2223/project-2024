CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE person_role
(
    id   INTEGER      NOT NULL,
    role VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT pk_person_role PRIMARY KEY (id)
);

INSERT INTO person_role values (1, 'STUDENT') , (2, 'ADMIN');