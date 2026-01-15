-- 1. Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS db_biblioteca;
USE db_biblioteca;

-- 2. Criação da tabela de Autores
-- O nome tem a constraint UNIQUE para evitar duplicidade
CREATE TABLE autores (
    idautor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    PRIMARY KEY (idautor),
    UNIQUE INDEX nome_UNIQUE (nome ASC)
) ENGINE=InnoDB;

-- 3. Criação da tabela de Livros
-- Com relacionamento (FK) e deleção em cascata
CREATE TABLE livros (
    idlivro INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    ano_publicacao CHAR(4) NOT NULL,
    genero ENUM('AVENTURA', 'FANTASIA', 'TERROR', 'ROMANCE', 'BIOGRAFIA', 'HISTORIA', 'AUTOAJUDA') NOT NULL,
    id_autor INT NOT NULL,
    PRIMARY KEY (idlivro),
    INDEX fk_livros_autores_idx (id_autor ASC),
    CONSTRAINT fk_livros_autores
        FOREIGN KEY (id_autor)
        REFERENCES autores (idautor)
        ON DELETE RESTRICT -- Impede apagar autor se houver livros dele
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- populando as tabelas (primeiro autor devido a restrição not null na tabela livros)
INSERT INTO autores (nome) VALUES 
('Stephen King'),
('J.R.R. Tolkien'),
('George R.R. Martin'),
('J.K. Rowling'),
('Machado de Assis'),
('Agatha Christie'),
('Augusto Cury');

INSERT INTO livros (titulo, ano_publicacao, genero, id_autor) VALUES 
('O Iluminado', '1977', 'TERROR', 1),
('It: A Coisa', '1986', 'TERROR', 1),
('O Senhor dos Anéis: A Sociedade do Anel', '1954', 'FANTASIA', 2),
('O Hobbit', '1937', 'AVENTURA', 2),
('A Guerra dos Tronos', '1996', 'FANTASIA', 3),
('Harry Potter e a Pedra Filosofal', '1997', 'FANTASIA', 4),
('Dom Casmurro', '1899', 'ROMANCE', 5),
('Memórias Póstumas de Brás Cubas', '1881', 'ROMANCE', 5),
('Assassinato no Expresso do Oriente', '1934', 'ROMANCE', 6),
('O Vendedor de Sonhos', '2008', 'AUTOAJUDA', 7);