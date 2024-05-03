CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE mark_result
(
    id          INTEGER      NOT NULL,
    mark_result VARCHAR(255) NOT NULL,
    CONSTRAINT pk_mark_result PRIMARY KEY (id)
);

ALTER TABLE mark_result
    ADD CONSTRAINT uc_mark_result_mark_result UNIQUE (mark_result);