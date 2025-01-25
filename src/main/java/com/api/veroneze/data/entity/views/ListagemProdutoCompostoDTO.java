package com.api.veroneze.data.entity.views;

public class ListagemProdutoCompostoDTO {

    private Integer produtoId;
    private String nomeProduto;
    private Integer produtoCompostoId;
    private String nomeProdutoComposto;
    private Double proporcao;

    public ListagemProdutoCompostoDTO(Integer produtoId, String nomeProduto, Integer produtoCompostoId, String nomeProdutoComposto, Double proporcao) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.produtoCompostoId = produtoCompostoId;
        this.nomeProdutoComposto = nomeProdutoComposto;
        this.proporcao = proporcao;
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

    public Integer getProdutoCompostoId() {
        return produtoCompostoId;
    }

    public void setProdutoCompostoId(Integer produtoCompostoId) {
        this.produtoCompostoId = produtoCompostoId;
    }

    public String getNomeProdutoComposto() {
        return nomeProdutoComposto;
    }

    public void setNomeProdutoComposto(String nomeProdutoComposto) {
        this.nomeProdutoComposto = nomeProdutoComposto;
    }

    public Double getProporcao() {
        return proporcao;
    }

    public void setProporcao(Double proporcao) {
        this.proporcao = proporcao;
    }
}
