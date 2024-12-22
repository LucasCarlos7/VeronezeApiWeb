package com.api.veroneze.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProdutoComposto")
public class ProdutoCompostoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "produto_composto_id")
    private ProdutoEntity produtoCompostoId;

    private double proporcao;

    //Construtor

    public ProdutoCompostoEntity() {
    }

    public ProdutoCompostoEntity(Integer id, ProdutoEntity produtoPrimarioId, ProdutoEntity produtoSecundarioId, double proporcao) {
        this.id = id;
       // this.produtoPrimarioId = produtoPrimarioId;
       // this.produtoSecundarioId = produtoSecundarioId;
        this.proporcao = proporcao;
    }

    //Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getProporcao() {
        return proporcao;
    }

    public void setProporcao(double proporcao) {
        this.proporcao = proporcao;
    }
}
