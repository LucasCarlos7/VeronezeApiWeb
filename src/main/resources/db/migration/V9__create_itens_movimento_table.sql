CREATE TABLE itens_movimento (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    movimento_estoque_id INT NOT NULL,
    nome_produto VARCHAR(150),
    quantidade DOUBLE NOT NULL,
    valor_unitario_produto DOUBLE,
    valor_total_produto DOUBLE,
    operacao INT,
    produto_primario_id INT,
    CONSTRAINT fk_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id) ON DELETE CASCADE,
    CONSTRAINT fk_movimento_estoque_id FOREIGN KEY (movimento_estoque_id)
        REFERENCES movimento_estoque (id) ON DELETE CASCADE
);