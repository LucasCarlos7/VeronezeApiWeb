CREATE TABLE itens_venda (
    venda_id INT NOT NULL,
    item INT NOT NULL,
    produto_id INT NOT NULL,
    nome_produto VARCHAR(150),
    quantidade DOUBLE,
    valor_unitario_produto DOUBLE,
    valor_total_produto DOUBLE,
    status_produto_venda INT NOT NULL,
    data_atualizacao DATE,
    PRIMARY KEY (venda_id, item),
    CONSTRAINT fk_venda_itens_venda_id FOREIGN KEY (venda_id)
        REFERENCES venda (id) ON DELETE CASCADE,
    CONSTRAINT fk_produto_itens_venda_id FOREIGN KEY (produto_id)
        REFERENCES produto (id) ON DELETE CASCADE
);