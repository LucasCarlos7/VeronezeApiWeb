package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.CargoEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Funcionario")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String login;
    private String senha;
    private Integer cargo ;
    private String cpf;
    private String telefone;
    private String email;
    private String cep;
    private String endereco;
    private String bairro;
    private String numeroEnredeco;
    private Date dataCriacao;
    private Date dataAtualizacao;

    //Construtor

    public FuncionarioEntity() {
    }

    public FuncionarioEntity(String bairro, CargoEnum cargo, String cep, String cpf, Date dataAtualizacao, Date dataCriacao, String email, String endereco, int id, String nome, String numeroEnredeco, String telefone) {
        this.bairro = bairro;
        this.cep = cep;
        this.cpf = cpf;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
        this.email = email;
        this.endereco = endereco;
        this.id = id;
        this.nome = nome;
        this.numeroEnredeco = numeroEnredeco;
        this.telefone = telefone;
        setCargo(cargo);
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

    public CargoEnum getCargo() {
        return CargoEnum.valueOf(cargo);
    }

    public void setCargo(CargoEnum cargo) {
        if (cargo != null) {
            this.cargo = cargo.getCode();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNumeroEnredeco() {
        return numeroEnredeco;
    }

    public void setNumeroEnredeco(String numeroEnredeco) {
        this.numeroEnredeco = numeroEnredeco;
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
