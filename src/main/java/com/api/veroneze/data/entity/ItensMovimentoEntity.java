package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.OperacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ItensMovimento")
public class ItensMovimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer produtoId;
    @NotNull
    private Integer movimentoEstoqueId;
    @NotNull
    private Integer produtoPrimarioId;

    private Integer operacao;
    private String nomeProduto;
    private Double valorUnitarioProduto;
    private Double valorTotalProduto;
    @NotNull
    private Double quantidade;

    // Construtor

    public ItensMovimentoEntity() {
    }

    public ItensMovimentoEntity(Integer id, Integer produtoId, Integer movimentoEstoqueId, Integer produtoPrimarioId, OperacaoEnum operacao, String nomeProduto,
                                Double valorUnitarioProduto, Double valorTotalProduto, Double quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.movimentoEstoqueId = movimentoEstoqueId;
        this.produtoPrimarioId = produtoPrimarioId;
        setOperacao(operacao);
        this.nomeProduto = nomeProduto;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valorTotalProduto = valorTotalProduto;
        this.quantidade = quantidade;
    }

    //Getter e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getMovimentoEstoqueId() {
        return movimentoEstoqueId;
    }

    public void setMovimentoEstoqueId(Integer movimentoEstoqueId) {
        this.movimentoEstoqueId = movimentoEstoqueId;
    }

    public Integer getProdutoPrimarioId() {
        return produtoPrimarioId;
    }

    public void setProdutoPrimarioId(Integer produtoPrimarioId) {
        this.produtoPrimarioId = produtoPrimarioId;
    }

    public OperacaoEnum getOperacao() {
        return OperacaoEnum.valueOf(operacao);
    }

    public void setOperacao(OperacaoEnum operacao) {
        if (operacao != null) {
            this.operacao = operacao.getCode();
        }
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

    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
