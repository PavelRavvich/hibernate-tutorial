CREATE DATABASE many_to_many_lesson ENCODING 'UTF-8';


CREATE TABLE IF NOT EXISTS cars (
  id   SERIAL PRIMARY KEY,
  cost INTEGER,
  mark VARCHAR(25)
);

INSERT INTO cars (mark, cost) VALUES ('ford', 100000);
INSERT INTO cars (mark, cost) VALUES ('ford', 10984673);
INSERT INTO cars (mark, cost) VALUES ('mazda', 109573);


CREATE TABLE IF NOT EXISTS engines (
  id       SERIAL PRIMARY KEY,
  name     VARCHAR(25) NOT NULL,
  power    INTEGER     NOT NULL
);

INSERT INTO engines (name, power) VALUES ('engine_1', 10003452);
INSERT INTO engines (name, power) VALUES ('engine_2', 123450);
INSERT INTO engines (name, power) VALUES ('engine_3', 13458);


CREATE TABLE IF NOT EXISTS cars_engines (
  car_id INTEGER NOT NULL,
  engine_id INTEGER NOT NULL,
  PRIMARY KEY (car_id, engine_id),
  CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars (id),
  CONSTRAINT fk_engine_id FOREIGN KEY (engine_id) REFERENCES engines (id)
);

INSERT INTO cars_engines (car_id, engine_id) VALUES (1, 1);
INSERT INTO cars_engines (car_id, engine_id) VALUES (1, 2);

INSERT INTO cars_engines (car_id, engine_id) VALUES (2, 1);
INSERT INTO cars_engines (car_id, engine_id) VALUES (3, 1);