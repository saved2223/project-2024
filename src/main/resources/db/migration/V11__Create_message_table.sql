CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE message
(
    id             UUID                        NOT NULL,
    person_from_id UUID                        NOT NULL,
    person_to_id   UUID                        NOT NULL,
    timestamp      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    text           OID                         NOT NULL,
    CONSTRAINT pk_message PRIMARY KEY (id)
);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_PERSON_FROM FOREIGN KEY (person_from_id) REFERENCES person (id);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_PERSON_TO FOREIGN KEY (person_to_id) REFERENCES person (id);