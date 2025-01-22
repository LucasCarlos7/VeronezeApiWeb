package com.api.veroneze.data.entity.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class MovimentoEstoqueComProdutoDTO {

    private Integer id;
    private Integer localEstoqueId;
    private Integer localEstoqueSaidaId;
    private Integer fornecedorId;
    private Integer tipoOperacao;
    private Integer statusOperacao;
    private Double valorOperacao;
    private Date dataOperacao;
    private List<ItensMovimentoEstoqueDTO> itensMovimentoEstoque;

    public MovimentoEstoqueComProdutoDTO(Integer id, Integer localEstoqueId, Integer localEstoqueSaidaId,
                                         Integer fornecedorId, Integer tipoOperacao, Integer statusOperacao,
                                         Double valorOperacao, Date dataOperacao) {
        this.id = id;
        this.localEstoqueId = localEstoqueId;
        this.localEstoqueSaidaId = localEstoqueSaidaId;
        this.fornecedorId = fornecedorId;
        this.tipoOperacao = tipoOperacao;
        this.statusOperacao = statusOperacao;
        this.valorOperacao = valorOperacao;
        this.dataOperacao = dataOperacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public Integer getLocalEstoqueSaidaId() {
        return localEstoqueSaidaId;
    }

    public void setLocalEstoqueSaidaId(Integer localEstoqueSaidaId) {
        this.localEstoqueSaidaId = localEstoqueSaidaId;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Integer getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Integer tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Integer getStatusOperacao() {
        return statusOperacao;
    }

    public void setStatusOperacao(Integer statusOperacao) {
        this.statusOperacao = statusOperacao;
    }

    public Double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(Double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public List<ItensMovimentoEstoqueDTO> getItensMovimentoEstoque() {
        return itensMovimentoEstoque;
    }

    public void setItensMovimentoEstoque(List<ItensMovimentoEstoqueDTO> itensMovimentoEstoque) {
        this.itensMovimentoEstoque = itensMovimentoEstoque;
    }
}
