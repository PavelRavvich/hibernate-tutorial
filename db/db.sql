CREATE DATABASE one_to_many_lesson ENCODING 'UTF-8';


CREATE TABLE IF NOT EXISTS cars (
  id    SERIAL PRIMARY KEY,
  cost INTEGER,
  mark  VARCHAR(25)
);

INSERT INTO cars (mark) VALUES ('ford');
INSERT INTO cars (mark) VALUES ('ford');


CREATE TABLE IF NOT EXISTS engines (
  id       SERIAL PRIMARY KEY,
  name    VARCHAR(25) NOT NULL,
  power    INTEGER     NOT NULL,
  car_mark VARCHAR(25) NOT NULL
);

INSERT INTO engines (name, power, car_mark) VALUES ('super-engine', 10000, 'ford');