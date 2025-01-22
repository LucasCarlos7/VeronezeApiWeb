package com.api.veroneze.data.entity.views;

public class ProdutoCompostoDTO {

    private Integer produtoCompostoId;
    private String nomeProduto;
    private Double proporcao;

    public ProdutoCompostoDTO(Integer produtoCompostoId, String nomeProduto, Double proporcao) {
        this.produtoCompostoId = produtoCompostoId;
        this.nomeProduto = nomeProduto;
        this.proporcao = proporcao;
    }

    public Integer getProdutoCompostoId() {
        return produtoCompostoId;
    }

    public void setProdutoCompostoId(Integer produtoCompostoId) {
        this.produtoCompostoId = produtoCompostoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getProporcao() {
        return proporcao;
    }

    public void setProporcao(Double proporcao) {
        this.proporcao = proporcao;
    }
}
