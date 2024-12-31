CREATE TABLE estoque (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    local_estoque_id INT NOT NULL,
    movimento_entrada DOUBLE,
    movimento_saida DOUBLE,
    saldo_total DOUBLE,
    data_atualizacao DATE,
    CONSTRAINT fk_local_estoque FOREIGN KEY (local_estoque_id)
    REFERENCES local_estoque (id) ON DELETE CASCADE
);