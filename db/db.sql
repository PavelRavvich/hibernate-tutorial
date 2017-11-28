CREATE DATABASE many_to_one_lesson ENCODING 'UTF-8';

CREATE TABLE IF NOT EXISTS engines (
  id    SERIAL PRIMARY KEY,
  model VARCHAR(25) NOT NULL,
  power INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
  id        SERIAL PRIMARY KEY,
  mark      VARCHAR(25) NOT NULL,
  model     VARCHAR(25) NOT NULL,
  engine_id INTEGER     NOT NULL,
  FOREIGN KEY (engine_id) REFERENCES engines (id)
);