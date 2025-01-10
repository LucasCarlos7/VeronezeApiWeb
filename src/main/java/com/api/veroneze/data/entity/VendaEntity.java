package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.StatusOperacaoEnum;
import com.api.veroneze.data.entity.enums.StatusVendaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer clienteId;
    private String nomeCliente;
    private String cpf_cnpj;
    @NotNull
    private Integer funcionarioId;
    private String nomeFuncionario;
    private Double totalOrcamentoInicial;
    private Double desconto;
    private Double totalOrcamentoFinal;
    private Integer localEstoqueId;
    private Integer statusVenda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataVenda;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAtualizacao;

    //Construtor

    public VendaEntity() {
    }

    public VendaEntity(Integer id, Integer clienteId, String nomeCliente, String cpf_cnpj, Integer funcionarioId, String nomeFuncionario, Double totalOrcamentoInicial,
                       Double desconto, Double totalOrcamentoFinal, Integer localEstoqueId, StatusVendaEnum statusVenda, Date dataVenda, Date dataAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
        this.cpf_cnpj = cpf_cnpj;
        this.funcionarioId = funcionarioId;
        this.nomeFuncionario = nomeFuncionario;
        this.totalOrcamentoInicial = totalOrcamentoInicial;
        this.desconto = desconto;
        this.totalOrcamentoFinal = totalOrcamentoFinal;
        this.localEstoqueId = localEstoqueId;
        setStatusVenda(statusVenda);
        this.dataVenda = dataVenda;
        this.dataAtualizacao = dataAtualizacao;
    }
    //Getters e Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Double getTotalOrcamentoInicial() {
        return totalOrcamentoInicial;
    }

    public void setTotalOrcamentoInicial(Double totalOrcamentoInicial) {
        this.totalOrcamentoInicial = totalOrcamentoInicial;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTotalOrcamentoFinal() {
        return totalOrcamentoFinal;
    }

    public void setTotalOrcamentoFinal(Double totalOrcamentoFinal) {
        this.totalOrcamentoFinal = totalOrcamentoFinal;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    public StatusVendaEnum getStatusVenda() {
        return StatusVendaEnum.valueOf(statusVenda);
    }

    public void setStatusVenda(StatusVendaEnum statusVenda) {
        if (statusVenda != null) {
            this.statusVenda = statusVenda.getCode();
        }
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
