CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE person
(
    id             UUID           NOT NULL,
    username       VARCHAR(20)    NOT NULL,
    name           VARCHAR(50)    NOT NULL,
    email          VARCHAR(30)    NOT NULL,
    phone          VARCHAR(12)    NOT NULL,
    telegram       VARCHAR(30)    NOT NULL,
    add_info       VARCHAR(255),
    date_of_birth  date           NOT NULL,
    city           VARCHAR(20)    NOT NULL,
    person_role_id int DEFAULT 1 NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

ALTER TABLE person
    ADD CONSTRAINT uc_person_email UNIQUE (email);

ALTER TABLE person
    ADD CONSTRAINT uc_person_phone UNIQUE (phone);

ALTER TABLE person
    ADD CONSTRAINT uc_person_telegram UNIQUE (telegram);

ALTER TABLE person
    ADD CONSTRAINT uc_person_username UNIQUE (username);

ALTER TABLE person
    ADD CONSTRAINT FK_PERSON_ON_PERSON_ROLE FOREIGN KEY (person_role_id) REFERENCES person_role (id);