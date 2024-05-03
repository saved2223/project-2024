CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE training_status
(
    id              INTEGER      NOT NULL,
    training_status VARCHAR(255) NOT NULL,
    CONSTRAINT pk_training_status PRIMARY KEY (id)
);

ALTER TABLE training_status
    ADD CONSTRAINT uc_training_status_training_status UNIQUE (training_status);

INSERT INTO training_status values (1, 'NOT_STARTED'),
                                    (2, 'STARTED'),
                                    (3, 'FINISHED');