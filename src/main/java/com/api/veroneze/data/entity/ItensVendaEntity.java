package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.OperacaoEnum;
import com.api.veroneze.data.entity.enums.StatusProdutoVendaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@IdClass(ItensVendaIdEntity.class)
@Table(name = "ItensVenda")
public class ItensVendaEntity {

    @Id
    private Integer vendaId;

    @Id
    private Integer item;

    @NotNull
    private Integer produtoId;
    private String nomeProduto;

    @NotNull
    private Double quantidade;
    private Double valorUnitarioProduto;
    private Double valorTotalProduto;
    private Integer statusProdutoVenda;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAtualizacao;

    // Construtor


    public ItensVendaEntity() {
    }

    public ItensVendaEntity(Integer vendaId, Integer item, Integer produtoId, String nomeProduto, Double quantidade,
                            Double valorUnitarioProduto, Double valorTotalProduto, Date dataAtualizacao,
                            StatusProdutoVendaEnum statusProdutoVenda) {
        this.vendaId = vendaId;
        this.item = item;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        this.dataAtualizacao = dataAtualizacao;
        setStatusProdutoVenda(statusProdutoVenda);
    }

    //Getter e Setters

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

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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
