package com.api.veroneze.data.entity;

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

    private Integer localEstoqueEntradaId;
    private Integer localEstoqueSaidaId;

    @NotNull
    private Integer fornecedorId;

    @NotNull
    private Integer funcionarioId;

    @NotNull
    private Integer tipoOperacao;
    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataOperacao;

    //Construtor


    public MovimentoEstoqueEntity() {
    }

    public MovimentoEstoqueEntity(Integer id, Integer localEstoqueEntradaId, Integer localEstoqueSaidaId, Integer fornecedorId, Integer funcionarioId, TipoOperacaoEnum tipoOperacao, Integer status, Date dataOperacao) {
        this.id = id;
        this.localEstoqueEntradaId = localEstoqueEntradaId;
        this.localEstoqueSaidaId = localEstoqueSaidaId;
        this.fornecedorId = fornecedorId;
        this.funcionarioId = funcionarioId;
        setTipoOperacao(tipoOperacao);
        this.status = status;
        this.dataOperacao = dataOperacao;
    }

    //Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocalEstoqueEntradaId() {
        return localEstoqueEntradaId;
    }

    public void setLocalEstoqueEntradaId(Integer localEstoqueEntradaId) {
        this.localEstoqueEntradaId = localEstoqueEntradaId;
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

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public TipoOperacaoEnum getTipoOperacao() {
        return TipoOperacaoEnum.valueOf(tipoOperacao);
    }

    public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
        if (tipoOperacao != null) {
            this.tipoOperacao = tipoOperacao.getCode();
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
