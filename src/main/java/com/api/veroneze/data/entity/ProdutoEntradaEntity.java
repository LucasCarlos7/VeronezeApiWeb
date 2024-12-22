package com.api.veroneze.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProdutoEntrada")
public class ProdutoEntradaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int produtoId;
    private int MovimentoEstoqueId;
    private double quantidade;

    //Construtor


    public ProdutoEntradaEntity() {
    }

    public ProdutoEntradaEntity(int id, int produtoId, int movimentoEstoqueId, double quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        MovimentoEstoqueId = movimentoEstoqueId;
        this.quantidade = quantidade;
    }

    //Getter e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getMovimentoEstoqueId() {
        return MovimentoEstoqueId;
    }

    public void setMovimentoEstoqueId(int movimentoEstoqueId) {
        MovimentoEstoqueId = movimentoEstoqueId;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
