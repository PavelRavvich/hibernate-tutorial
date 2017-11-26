CREATE DATABASE academy ENCODING 'UTF-8';

CREATE TABLE director (
  id   SERIAL,
  name VARCHAR(10)
);

INSERT INTO director (id, name) VALUES (DEFAULT, 'boss');


CREATE TABLE head_department (
  id          SERIAL,
  name        VARCHAR(10),
  director_id INTEGER,
  FOREIGN KEY (director_id) REFERENCES director (id)
);

INSERT INTO head_department (id, name, director_id) VALUES (DEFAULT, 'sub_boss', 1);


CREATE TABLE teachers (
  id                 SERIAL,
  name               VARCHAR(10),
  head_department_id INTEGER,
  FOREIGN KEY (head_department_id) REFERENCES head_department (id)
);

INSERT INTO teachers (id, name, head_department_id) VALUES (DEFAULT, 'teacher_01', 1);
INSERT INTO teachers (id, name, head_department_id) VALUES (DEFAULT, 'teacher_02', 1);


CREATE TABLE students (
  id   SERIAL,
  name VARCHAR(10)
);

INSERT INTO students (id, name) VALUES (DEFAULT, 'student_01');
INSERT INTO students (id, name) VALUES (DEFAULT, 'student_02');
INSERT INTO students (id, name) VALUES (DEFAULT, 'student_03');


CREATE TABLE students_to_teacher (
  id         SERIAL,
  student_id INTEGER,
  teacher_id INTEGER,
  UNIQUE (student_id, teacher_id),
  FOREIGN KEY (student_id) REFERENCES students (id),
  FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

INSERT INTO students_to_teacher (id, student_id, teacher_id) VALUES (DEFAULT, 1, 1);
INSERT INTO students_to_teacher (id, student_id, teacher_id) VALUES (DEFAULT, 2, 1);
INSERT INTO students_to_teacher (id, student_id, teacher_id) VALUES (DEFAULT, 3, 1);