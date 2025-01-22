package com.api.veroneze.data.entity.views;

import com.api.veroneze.data.entity.enums.StatusProdutoVendaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ItensVendaDTO {

    private Integer vendaId;
    private Integer item;
    private Integer produtoId;
    private String nomeProduto;
    private Double quantidade;
    private Double valorUnitarioProduto;
    private Double valorTotalProduto;
    private Integer statusProdutoVenda;

    public ItensVendaDTO(Integer vendaId, Integer item, Integer produtoId, String nomeProduto,
                         Double quantidade, Double valorUnitarioProduto, Double valorTotalProduto,
                         StatusProdutoVendaEnum statusProdutoVenda) {
        this.vendaId = vendaId;
        this.item = item;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        setStatusProdutoVenda(statusProdutoVenda);
    }

    public ItensVendaDTO(Integer vendaId, Integer item, Integer produtoId, String nomeProduto,
                         Double quantidade, Double valorUnitarioProduto, Double valorTotalProduto,
                         Integer statusProdutoVenda) {
        this.vendaId = vendaId;
        this.item = item;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        this.statusProdutoVenda = statusProdutoVenda;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(Double valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }

    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public StatusProdutoVendaEnum getStatusProdutoVenda() {
        return StatusProdutoVendaEnum.valueOf(statusProdutoVenda);
    }

    public void setStatusProdutoVenda(StatusProdutoVendaEnum statusProdutoVenda) {
        if (statusProdutoVenda != null) {
            this.statusProdutoVenda = statusProdutoVenda.getCode();
        }
    }
}
