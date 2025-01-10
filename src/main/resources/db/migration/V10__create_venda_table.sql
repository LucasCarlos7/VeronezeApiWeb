CREATE TABLE venda (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    nome_cliente VARCHAR(100),
    cpf_cnpj VARCHAR(18),
    funcionario_id INT NOT NULL,
    nome_funcionario VARCHAR(100),
    total_orcamento_inicial DOUBLE NOT NULL,
    desconto DOUBLE,
    total_orcamento_final DOUBLE,
    local_estoque_id INT NOT NULL,
    status_venda INT NOT NULL,
    data_venda DATE,
    data_atualizacao DATE,
    CONSTRAINT fk_cliente_venda_id FOREIGN KEY (cliente_id)
        REFERENCES cliente (id) ON DELETE CASCADE,
    CONSTRAINT fk_funcionario_venda_id FOREIGN KEY (funcionario_id)
        REFERENCES funcionario (id) ON DELETE CASCADE,
    CONSTRAINT fk_local_estoque_venda_id FOREIGN KEY (local_estoque_id)
            REFERENCES local_estoque (id) ON DELETE CASCADE
);