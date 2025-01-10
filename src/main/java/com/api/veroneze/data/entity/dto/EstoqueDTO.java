package com.api.veroneze.data.entity.dto;

public class EstoqueDTO {

    private Integer localEstoqueId;
    private String descricao;
    private Double saldoTotal;

    public EstoqueDTO(Integer localEstoqueId, String descricao, Double saldoTotal) {
        this.localEstoqueId = localEstoqueId;
        this.descricao = descricao;
        this.saldoTotal = saldoTotal;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }
}
