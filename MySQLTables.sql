Use MusicDB;

CREATE TABLE Music (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    composer VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    m_type VARCHAR(50) NOT NULL
);

-- Створення таблиці Song для зберігання пісень
CREATE TABLE Song (
    id INT PRIMARY KEY,
    lyrics TEXT NOT NULL,
    FOREIGN KEY (id) REFERENCES Music(id) ON DELETE CASCADE
);

CREATE DATABASE IF NOT EXISTS MusicDB;
Use MusicDB;

-- Створення таблиці PopMusic для зберігання даних поп-музики
CREATE TABLE PopMusic (
    id INT PRIMARY KEY,
    album VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Song(id) ON DELETE CASCADE
);

-- Створення таблиці Instumental для зберігання інструментальних творів
CREATE TABLE Instumental (
    id INT PRIMARY KEY,
    instrument VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Music(id) ON DELETE CASCADE
);

-- Створення таблиці Orchestra для зберігання даних оркестрових творів
CREATE TABLE Orchestra (
    id INT PRIMARY KEY,
    conductor VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Instumental(id) ON DELETE CASCADE
);