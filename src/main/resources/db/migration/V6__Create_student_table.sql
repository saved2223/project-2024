CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE student
(
    id              UUID         NOT NULL,
    person_id       UUID         NOT NULL,
    university      VARCHAR(100) NOT NULL,
    departament     VARCHAR(100) NOT NULL,
    speciality      VARCHAR(50)  NOT NULL,
    course          SMALLINT,
    study_status_id INTEGER      NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE student_trainings
(
    student_id   UUID NOT NULL,
    trainings_id UUID NOT NULL,
    CONSTRAINT pk_student_trainings PRIMARY KEY (student_id, trainings_id)
);

ALTER TABLE student
    ADD CONSTRAINT uc_student_person UNIQUE (person_id);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_PERSON FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_STUDY_STATUS FOREIGN KEY (study_status_id) REFERENCES study_status (id);

ALTER TABLE student_trainings
    ADD CONSTRAINT fk_stutra_on_student FOREIGN KEY (student_id) REFERENCES student (id);

ALTER TABLE student_trainings
    ADD CONSTRAINT fk_stutra_on_training FOREIGN KEY (trainings_id) REFERENCES training (id);