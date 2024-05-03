CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE mark
(
    id         UUID    NOT NULL,
    teacher_id UUID    NOT NULL,
    student_id UUID    NOT NULL,
    task_id    UUID    NOT NULL,
    result_id  INTEGER NOT NULL,
    comment    VARCHAR(255),
    marked_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_mark PRIMARY KEY (id)
);

ALTER TABLE mark
    ADD CONSTRAINT uc_mark_task UNIQUE (task_id);

ALTER TABLE mark
    ADD CONSTRAINT FK_MARK_ON_RESULT FOREIGN KEY (result_id) REFERENCES mark_result (id);

ALTER TABLE mark
    ADD CONSTRAINT FK_MARK_ON_STUDENT FOREIGN KEY (student_id) REFERENCES student (id);

ALTER TABLE mark
    ADD CONSTRAINT FK_MARK_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE mark
    ADD CONSTRAINT FK_MARK_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES person (id);