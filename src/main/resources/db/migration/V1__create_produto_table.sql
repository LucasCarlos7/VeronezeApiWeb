CREATE TABLE produto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo_produto INT NOT NULL,
    preco DOUBLE,
    data_criacao DATE,
    data_atualizacao DATE
);