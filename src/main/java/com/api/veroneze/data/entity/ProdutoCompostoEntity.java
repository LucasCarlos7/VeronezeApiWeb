package com.api.veroneze.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProdutoComposto")
public class ProdutoCompostoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "produto_primario_id")
    private ProdutoEntity produtoPrimarioId;

    @OneToOne
    @JoinColumn(name = "produto_secundario_id")
    private ProdutoEntity produtoSecundarioId;
    private double proporcao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProdutoEntity getProdutoPrimarioId() {
        return produtoPrimarioId;
    }

    public void setProdutoPrimarioId(ProdutoEntity produtoPrimarioId) {
        this.produtoPrimarioId = produtoPrimarioId;
    }

    public ProdutoEntity getProdutoSecundarioId() {
        return produtoSecundarioId;
    }

    public void setProdutoSecundarioId(ProdutoEntity produtoSecundarioId) {
        this.produtoSecundarioId = produtoSecundarioId;
    }

    public double getProporcao() {
        return proporcao;
    }

    public void setProporcao(double proporcao) {
        this.proporcao = proporcao;
    }
}
