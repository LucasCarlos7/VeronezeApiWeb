CREATE TABLE funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(30) NOT NULL,
    senha TEXT NOT NULL,
    cargo INT NOT NULL,
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    email VARCHAR(50),
    cep VARCHAR(10),
    endereco VARCHAR(150),
    bairro VARCHAR(30),
    numero_endereco VARCHAR(10),
    data_criacao DATE NULL,
    data_atualizacao DATE NULL,
    cidade VARCHAR(50),
    UF VARCHAR(2)
);