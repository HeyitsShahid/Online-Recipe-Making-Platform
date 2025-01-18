CREATE DATABASE recipe_db;

USE recipe_db;

CREATE TABLE recipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ingredients TEXT NOT NULL
);
