package com.api.veroneze.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProdutoComposto")
public class ProdutoCompostoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer produtoId;

    private Integer produtoCompostoId;
    private Double proporcao;

    //Construtor

    public ProdutoCompostoEntity() {
    }

    public ProdutoCompostoEntity(Integer id, Integer produtoCompostoId, Integer produtoId, Double proporcao) {
        this.id = id;
        this.produtoCompostoId = produtoCompostoId;
        this.produtoId = produtoId;
        this.proporcao = proporcao;
    }

    //Getters e Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoCompostoId() {
        return produtoCompostoId;
    }

    public void setProdutoCompostoId(Integer produtoCompostoId) {
        this.produtoCompostoId = produtoCompostoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Double getProporcao() {
        return proporcao;
    }

    public void setProporcao(Double proporcao) {
        this.proporcao = proporcao;
    }
}
