package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.TipoProdutoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, message = "Informe ao menos 5 caracteres para o campo Nome")
    private String nome;

    @NotNull(message = "TipoProduto não pode ser vazio.")
    private Integer tipoProduto;
    private double preco;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataCriacao;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
