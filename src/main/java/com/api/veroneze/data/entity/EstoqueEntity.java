package com.api.veroneze.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(EstoqueIdEntity.class)
@Table(name = "estoque")
public class EstoqueEntity {

    @Id
    private Integer produtoId;

    @Id
    private Integer localEstoqueId;

    private Double movimentoEntrada;
    private Double movimentoSaida;
    private Double saldoTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAtualizacao;

    // Construtor

    public EstoqueEntity() {
    }

    public EstoqueEntity(Integer produtoId, Integer localEstoqueId, Double movimentoEntrada, Double movimentoSaida, Double saldoTotal, Date dataAtualizacao) {
        this.produtoId = produtoId;
        this.localEstoqueId = localEstoqueId;
        this.movimentoEntrada = movimentoEntrada;
        this.movimentoSaida = movimentoSaida;
        this.saldoTotal = saldoTotal;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public Double getMovimentoEntrada() {
        return movimentoEntrada;
    }

    public void setMovimentoEntrada(Double movimentoEntrada) {
        this.movimentoEntrada = movimentoEntrada;
    }

    public Double getMovimentoSaida() {
        return movimentoSaida;
    }

    public void setMovimentoSaida(Double movimentoSaida) {
        this.movimentoSaida = movimentoSaida;
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
