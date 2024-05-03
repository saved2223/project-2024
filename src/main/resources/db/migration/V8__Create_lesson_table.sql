CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE lesson
(
    id          UUID NOT NULL,
    training_id UUID NOT NULL,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_tasks
(
    lesson_id UUID NOT NULL,
    tasks_id  UUID NOT NULL,
    CONSTRAINT pk_lesson_tasks PRIMARY KEY (lesson_id, tasks_id)
);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TRAINING FOREIGN KEY (training_id) REFERENCES training (id);

ALTER TABLE lesson_tasks
    ADD CONSTRAINT fk_lestas_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE lesson_tasks
    ADD CONSTRAINT fk_lestas_on_task FOREIGN KEY (tasks_id) REFERENCES task (id);