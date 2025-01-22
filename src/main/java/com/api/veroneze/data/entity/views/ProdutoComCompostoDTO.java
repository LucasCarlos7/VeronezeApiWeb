package com.api.veroneze.data.entity.views;

import java.util.Date;
import java.util.List;

public class ProdutoComCompostoDTO {

    private Integer id;
    private String nome;
    private Integer tipoProduto;
    private double preco;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private List<ProdutoCompostoDTO> produtosList;

    public ProdutoComCompostoDTO(Integer id, String nome, Integer tipoProduto, double preco, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(Integer tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<ProdutoCompostoDTO> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<ProdutoCompostoDTO> produtosList) {
        this.produtosList = produtosList;
    }
}
