package com.api.veroneze.data.entity.views;

import com.api.veroneze.data.entity.enums.StatusVendaEnum;

import java.util.Date;
import java.util.List;

public class VendaComProdutosDTO {

    private Integer id;
    private Integer clienteId;
    private String nomeCliente;
    private String cpf_cnpj;
    private Double totalOrcamentoInicial;
    private Double desconto;
    private Double totalOrcamentoFinal;
    private Integer localEstoqueId;
    private Integer statusVenda;
    private Date dataVenda;
    private Date dataAtualizacao;
    private List<ItensVendaDTO> itensVendaList;

    public VendaComProdutosDTO(Integer id, Integer clienteId, String nomeCliente, String cpf_cnpj,
                               Double totalOrcamentoInicial, Double desconto, Double totalOrcamentoFinal,
                               Integer localEstoqueId, StatusVendaEnum statusVenda, Date dataVenda,
                               Date dataAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
        this.cpf_cnpj = cpf_cnpj;
        this.totalOrcamentoInicial = totalOrcamentoInicial;
        this.desconto = desconto;
        this.totalOrcamentoFinal = totalOrcamentoFinal;
        this.localEstoqueId = localEstoqueId;
        setStatusVenda(statusVenda);
        this.dataVenda = dataVenda;
        this.dataAtualizacao = dataAtualizacao;
    }

    public VendaComProdutosDTO(Integer id, Integer clienteId, String nomeCliente, String cpf_cnpj,
                               Double totalOrcamentoInicial, Double desconto, Double totalOrcamentoFinal,
                               Integer localEstoqueId, Integer statusVenda, Date dataVenda, Date dataAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
        this.cpf_cnpj = cpf_cnpj;
        this.totalOrcamentoInicial = totalOrcamentoInicial;
        this.desconto = desconto;
        this.totalOrcamentoFinal = totalOrcamentoFinal;
        this.localEstoqueId = localEstoqueId;
        this.statusVenda = statusVenda;
        this.dataVenda = dataVenda;
        this.dataAtualizacao = dataAtualizacao;
    }

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

    public List<ItensVendaDTO> getItensVendaList() {
        return itensVendaList;
    }

    public void setItensVendaList(List<ItensVendaDTO> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }
}
