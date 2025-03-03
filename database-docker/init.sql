CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    numero INT NOT NULL ,
    titulo VARCHAR(255) NOT NULL,
    edicao VARCHAR(50),
    isbn VARCHAR(20) UNIQUE,
    categoria VARCHAR(100),
    CONSTRAINT fk_livro_autor FOREIGN KEY (numero) REFERENCES Autor(numero)
);