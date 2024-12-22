package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MovimentoEstoque")
public class MovimentoEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int estoqueEntradaId;
    private int estoqueSaidaId;
    private int fornecedorId;
    private int funcionarioId;
    private Integer tipoOperacao;
    private Date dataOperacao;

    //Construtor


    public MovimentoEstoqueEntity() {
    }

    public MovimentoEstoqueEntity(int id, int estoqueEntradaId, int estoqueSaidaId, int fornecedorId, int funcionarioId, TipoOperacaoEnum tipoOperacao, Date dataOperacao) {
        this.id = id;
        this.estoqueEntradaId = estoqueEntradaId;
        this.estoqueSaidaId = estoqueSaidaId;
        this.fornecedorId = fornecedorId;
        this.funcionarioId = funcionarioId;
        this.dataOperacao = dataOperacao;
        setTipoOperacao(tipoOperacao);
    }

    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoqueEntradaId() {
        return estoqueEntradaId;
    }

    public void setEstoqueEntradaId(int estoqueEntradaId) {
        this.estoqueEntradaId = estoqueEntradaId;
    }

    public int getEstoqueSaidaId() {
        return estoqueSaidaId;
    }

    public void setEstoqueSaidaId(int estoqueSaidaId) {
        this.estoqueSaidaId = estoqueSaidaId;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
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

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
