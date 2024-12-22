package com.api.veroneze.data.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Estoque")
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double quantidade;
    private int produtoId;
    private int localEstoqueId;
    private Date dataAtualizacao;

    // Construtor


    public EstoqueEntity() {
    }

    public EstoqueEntity(int id, double quantidade, int produtoId, int localEstoqueId, Date dataAtualizacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.localEstoqueId = localEstoqueId;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(int localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
