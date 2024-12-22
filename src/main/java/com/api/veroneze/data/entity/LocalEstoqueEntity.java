package com.api.veroneze.data.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "LocalEstoque")
public class LocalEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Date dataAtualizacao;

    //Contrutor

    public LocalEstoqueEntity() {
    }

    public LocalEstoqueEntity(int id, String nome, Date dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.dataAtualizacao = dataAtualizacao;
    }

    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
