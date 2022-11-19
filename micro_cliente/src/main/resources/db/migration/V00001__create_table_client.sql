CREATE TABLE IF NOT EXISTS clients
(
    id INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    first_name VARCHAR(40) NOT NULL CHECK(first_name <> ''),
    last_name VARCHAR(40) NOT NULL CHECK(last_name <> ''),
    cpf VARCHAR(15) UNIQUE,
    sex VARCHAR NOT NULL CHECK(sex IN('M', 'F')),
    genre VARCHAR(30) NOT NULL CHECK(genre != ''),
    birth_date DATE NOT NULL,
    marital_status VARCHAR(30) NOT NULL CHECK(marital_status <> ''),
    education VARCHAR(200) NOT NULL CHECK(education <> '')
);

