package com.api.veroneze.data.entity.views;

import com.api.veroneze.data.entity.EstoqueEntity;

import java.util.Date;
import java.util.List;

public class ProdutoEstoqueDTO {

    private Integer id;
    private String nomeProduto;
    private Integer tipoProduto;
    private Double preco;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private List<EstoqueDTO> estoqueList;

    public ProdutoEstoqueDTO(Integer id, String nomeProduto, Integer tipoProduto, Double preco, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.nomeProduto = nomeProduto;
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

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(Integer tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

    public List<EstoqueDTO> getEstoqueList() {
        return estoqueList;
    }

    public void setEstoqueList(List<EstoqueDTO> estoqueList) {
        this.estoqueList = estoqueList;
    }
}
