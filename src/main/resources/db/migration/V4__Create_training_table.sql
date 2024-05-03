CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE training
(
    id                    UUID         NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    description           OID          NOT NULL,
    start_date            date         NOT NULL,
    end_date              date         NOT NULL,
    registration_deadline date         NOT NULL,
    training_status_id    INTEGER      NOT NULL,
    CONSTRAINT pk_training PRIMARY KEY (id)
);

ALTER TABLE training
    ADD CONSTRAINT FK_TRAINING_ON_TRAINING_STATUS FOREIGN KEY (training_status_id) REFERENCES training_status (id);