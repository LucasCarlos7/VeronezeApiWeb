package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.TipoPessoaEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private Integer tipoPessoa;
    private String cnpj_cpf;
    private String telefone;
    private String email;
    private String cep;
    private String endereco;
    private String bairro;
    private String numeroEndereco;
    private Date dataCriacao;
    private Date dataAtualizacao;

    // Contrutor

    public ClienteEntity() {
    }

    public ClienteEntity(int id, String nome, TipoPessoaEnum tipoPessoa, String cnpj_cpf, String telefone, String email, String cep, String endereco, String bairro, String numeroEndereco, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj_cpf = cnpj_cpf;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numeroEndereco = numeroEndereco;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        setTipoPessoa(tipoPessoa);
    }

    //Getters e Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return TipoPessoaEnum.valueOf(tipoPessoa);
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        if (tipoPessoa != null) {
            this.tipoPessoa = tipoPessoa.getCode();
        }
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
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
