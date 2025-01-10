CREATE TABLE estoque (
    produto_id INT NOT NULL,
    local_estoque_id INT NOT NULL,
    movimento_entrada DOUBLE,
    movimento_saida DOUBLE,
    saldo_total DOUBLE,
    data_atualizacao DATE,
    PRIMARY KEY (produto_id, local_estoque_id),
    CONSTRAINT fk_estoque_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id) ON DELETE CASCADE,
    CONSTRAINT fk_local_estoque FOREIGN KEY (local_estoque_id)
        REFERENCES local_estoque (id) ON DELETE CASCADE
);