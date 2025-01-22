package com.api.veroneze.data.entity.views;

import com.api.veroneze.data.entity.enums.OperacaoEnum;

public class ItensMovimentoEstoqueDTO {

    private Integer produtoId;
    private String nomeProduto;
    private Double quantidade;
    private Double valorUnitarioProduto;
    private Double valorTotalProduto;
    private Integer operacao;

    public ItensMovimentoEstoqueDTO(Integer produtoId, String nomeProduto, Double quantidade,
                                    Double valorUnitarioProduto, Double valorTotalProduto, OperacaoEnum operacao) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        setOperacao(operacao);
    }

    public ItensMovimentoEstoqueDTO(Integer produtoId, String nomeProduto, Double quantidade, Double valorUnitarioProduto, Double valorTotalProduto, Integer operacao) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        this.operacao = operacao;
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

    public Double getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(Double valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public OperacaoEnum getOperacao() {
        return OperacaoEnum.valueOf(operacao);
    }

    public void setOperacao(OperacaoEnum operacao) {
        if (operacao != null) {
            this.operacao = operacao.getCode();
        }
    }
}
