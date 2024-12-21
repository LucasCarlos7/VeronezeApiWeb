package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.TipoProdutoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "Produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer tipoProduto;
    private double preco;
    private Date dataCriacao;
    private Date dataAtualizacao;

    //Construtor

    public ProdutoEntity() {
    }

    public ProdutoEntity(Date dataAtualizacao, Date dataCriacao, Integer id, String nome, double preco, TipoProdutoEnum tipoProduto) {
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        setTipoProduto(tipoProduto);
    }

    //Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProdutoEnum getTipoProduto() {
        return TipoProdutoEnum.valueOf(tipoProduto);
    }

    public void setTipoProduto(TipoProdutoEnum tipoProduto) {
        if (tipoProduto != null) {
            this.tipoProduto = tipoProduto.getCode();
        }
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
