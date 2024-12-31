package com.api.veroneze.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "Estoque")
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double movimentoEntrada;
    private double movimentoSaida;
    private double saldoTotal;

    @NotNull
    private Integer localEstoqueId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAtualizacao;

    // Construtor

    public EstoqueEntity() {
    }

    public EstoqueEntity(Integer id, double movimentoEntrada, double movimentoSaida, double saldoTotal, Integer localEstoqueId, Date dataAtualizacao) {
        this.id = id;
        this.movimentoEntrada = movimentoEntrada;
        this.movimentoSaida = movimentoSaida;
        this.saldoTotal = saldoTotal;
        this.localEstoqueId = localEstoqueId;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMovimentoEntrada() {
        return movimentoEntrada;
    }

    public void setMovimentoEntrada(double movimentoEntrada) {
        this.movimentoEntrada = movimentoEntrada;
    }

    public double getMovimentoSaida() {
        return movimentoSaida;
    }

    public void setMovimentoSaida(double movimentoSaida) {
        this.movimentoSaida = movimentoSaida;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
