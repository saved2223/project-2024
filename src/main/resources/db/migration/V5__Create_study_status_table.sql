CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE study_status
(
    id           INTEGER      NOT NULL,
    study_status VARCHAR(255) NOT NULL,
    CONSTRAINT pk_study_status PRIMARY KEY (id)
);

ALTER TABLE study_status
    ADD CONSTRAINT uc_study_status_study_status UNIQUE (study_status);

INSERT INTO study_status values (1, 'STUDYING'), (2, 'FINISHED')

