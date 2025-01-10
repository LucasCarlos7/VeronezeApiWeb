CREATE TABLE movimento_estoque (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    local_estoque_id INT,
    local_estoque_saida_id INT,
    fornecedor_id INT,
    funcionario_id INT NOT NULL,
    tipo_operacao INT NOT NULL,
    status_operacao INT NOT NULL,
    valor_operacao DOUBLE,
    data_operacao DATE,
    CONSTRAINT fk_local_estoque_id FOREIGN KEY (local_estoque_id)
        REFERENCES local_estoque (id) ON DELETE CASCADE,
    CONSTRAINT fk_local_estoque_saida FOREIGN KEY (local_estoque_saida_id)
        REFERENCES local_estoque (id) ON DELETE CASCADE,
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedor (id) ON DELETE CASCADE,
    CONSTRAINT fk_funcionario FOREIGN KEY (funcionario_id)
        REFERENCES funcionario (id) ON DELETE CASCADE
);