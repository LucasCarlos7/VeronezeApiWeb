package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.StatusOperacaoEnum;
import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "MovimentoEstoque")
public class MovimentoEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer localEstoqueId;
    private Integer localEstoqueSaidaId;

    @NotNull
    private Integer fornecedorId;

    @NotNull
    private Integer tipoOperacao;
    private Integer statusOperacao;

    private Double valorOperacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataOperacao;

    //Construtor

    public MovimentoEstoqueEntity() {
    }

    public MovimentoEstoqueEntity(Integer id, Integer localEstoqueId, Integer localEstoqueSaidaId,
                                  Integer fornecedorId, TipoOperacaoEnum tipoOperacao,
                                  StatusOperacaoEnum statusOperacao, Double valorOperacao, Date dataOperacao) {
        this.id = id;
        this.localEstoqueId = localEstoqueId;
        this.localEstoqueSaidaId = localEstoqueSaidaId;
        this.fornecedorId = fornecedorId;
        setTipoOperacao(tipoOperacao);
        setStatusOperacao(statusOperacao);
        this.valorOperacao = valorOperacao;
        this.dataOperacao = dataOperacao;
    }

    //Getters e Setters

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

    public TipoOperacaoEnum getTipoOperacao() {
        return TipoOperacaoEnum.valueOf(tipoOperacao);
    }

    public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
        if (tipoOperacao != null) {
            this.tipoOperacao = tipoOperacao.getCode();
        }
    }

    public StatusOperacaoEnum getStatusOperacao() {
        return StatusOperacaoEnum.valueOf(statusOperacao);
    }

    public void setStatusOperacao(StatusOperacaoEnum statusOperacao) {
        if (statusOperacao != null) {
            this.statusOperacao = statusOperacao.getCode();
        }
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
}
