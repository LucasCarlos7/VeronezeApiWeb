CREATE TABLE produto_composto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    produto_composto_id INT NOT NULL,
    proporcao DOUBLE NOT NULL,
    CONSTRAINT fk_produto_composto FOREIGN KEY (produto_composto_id)
        REFERENCES produto (id) ON DELETE CASCADE,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id)
        REFERENCES produto (id) ON DELETE CASCADE
);