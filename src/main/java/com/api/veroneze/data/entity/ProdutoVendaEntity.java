package com.api.veroneze.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProdutoVenda")
public class ProdutoVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int produtoId;
    private int vendaId;
    private double quantidade;

    // Construtor

    public ProdutoVendaEntity() {
    }

    public ProdutoVendaEntity(int id, int produtoId, int vendaId, double quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.vendaId = vendaId;
        this.quantidade = quantidade;
    }

    // Getters e Setters


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

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
