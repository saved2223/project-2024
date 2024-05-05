
ALTER TABLE person
ADD password VARCHAR(100) NOT NULL default 'password',
DROP COLUMN person_role_id;

DROP TABLE person_role;

CREATE TABLE person_role
(
    id   INTEGER     NOT NULL,
    name VARCHAR(15) NOT NULL,
    CONSTRAINT pk_person_role PRIMARY KEY (id)
);

CREATE TABLE person_roles
(
    person_id UUID    NOT NULL,
    roles_id  INTEGER NOT NULL,
    CONSTRAINT pk_person_roles PRIMARY KEY (person_id, roles_id)
);

ALTER TABLE person_roles
    ADD CONSTRAINT fk_perrol_on_person FOREIGN KEY (person_id) REFERENCES person (id);

ALTER TABLE person_roles
    ADD CONSTRAINT fk_perrol_on_person_role FOREIGN KEY (roles_id) REFERENCES person_role (id);