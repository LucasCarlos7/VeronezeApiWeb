-- Criação das Tabelas Principais
CREATE TABLE produto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo_produto INT NOT NULL,
    preco DOUBLE,
    data_criacao DATE,
    data_atualizacao DATE
);

CREATE TABLE local_estoque (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    data_criacao DATE,
    data_atualizacao DATE
);

CREATE TABLE estoque (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    quantidade DOUBLE NOT NULL,
    id_produto INT NOT NULL,
    id_local_estoque INT NOT NULL,
    data_atualizacao DATE,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_local_estoque) REFERENCES local_estoque(id)
);

CREATE TABLE produto_composto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_produto_composto INT NOT NULL,
    proporcao DOUBLE NOT NULL,
    FOREIGN KEY (id_produto_composto) REFERENCES produto(id)
);

CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo_pessoa INT NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(50),
    endereco TEXT,
    bairro VARCHAR(50),
    numero_end VARCHAR(10),
    data_criacao DATE,
    data_atualizacao DATE
);

CREATE TABLE fornecedor (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo_pessoa INT NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,
    inscricao_estadual VARCHAR(20),
    telefone VARCHAR(15),
    email VARCHAR(50),
    endereco TEXT,
    bairro VARCHAR(50),
    numero_end VARCHAR(10),
    data_criacao DATE,
    data_atualizacao DATE
);

CREATE TABLE funcionario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    cargo INT NOT NULL,
    cpf VARCHAR(20),
    telefone VARCHAR(15),
    email VARCHAR(50),
    cep VARCHAR(20),
    endereco TEXT,
    bairro VARCHAR(100),
    numero_end VARCHAR(10),
    data_criacao DATE,
    data_atualizacao DATE
);

CREATE TABLE movimento_estoque (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_estoque_entrada INT,
    id_estoque_saida INT,
    id_fornecedor INT,
    id_funcionario INT,
    operacao INT NOT NULL,
    data_operacao DATE,
    FOREIGN KEY (id_estoque_entrada) REFERENCES estoque(id),
    FOREIGN KEY (id_estoque_saida) REFERENCES estoque(id),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);

CREATE TABLE produto_entrada (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    id_movimento_estoque INT NOT NULL,
    quantidade DOUBLE NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_movimento_estoque) REFERENCES movimento_estoque(id)
);

CREATE TABLE venda (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_funcionario INT NOT NULL,
    data_venda DATE,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);

CREATE TABLE produto_venda (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    id_venda INT NOT NULL,
    quantidade DOUBLE NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_venda) REFERENCES venda(id)
);