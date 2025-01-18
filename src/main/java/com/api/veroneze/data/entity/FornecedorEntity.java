package com.api.veroneze.data.entity;

import com.api.veroneze.data.entity.enums.TipoPessoaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Fornecedor")
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome obrigatório")
    @Size(min = 10, message = "Informe ao menos 10 caracteres para o campo Nome.")
    private String nome;

    @NotNull(message = "TipoPessoa obrigatório")
    private Integer tipoPessoa;

    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @CPF(message = "CPF inválido")
    private String cpf;
    private String inscricaoEstadual;

    @NotBlank(message = "Telefone obrigatório")
    private String telefone;

    @Email(message = "Email inválido")
    private String email;
    private String cep;
    private String endereco;
    private String bairro;
    private String numeroEndereco;
    private String cidade;
    private String UF;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAtualizacao;

    // Construtor

    public FornecedorEntity() {
    }

    public FornecedorEntity(Integer id, String nome, TipoPessoaEnum tipoPessoa, String cnpj, String cpf, String inscricaoEstadual,
                            String telefone, String email, String cep, String endereco, String bairro, String numeroEndereco,
                            Date dataCriacao, Date dataAtualizacao, String cidade, String UF) {
        this.id = id;
        this.nome = nome;
        setTipoPessoa(tipoPessoa);
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.inscricaoEstadual = inscricaoEstadual;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numeroEndereco = numeroEndereco;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.cidade = cidade;
        this.UF = UF;
    }

    // Getters e Setters


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

    public TipoPessoaEnum getTipoPessoa() {
        return TipoPessoaEnum.valueOf(tipoPessoa);
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        if (tipoPessoa != null) {
            this.tipoPessoa = tipoPessoa.getCode();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FornecedorEntity that = (FornecedorEntity) o;
        return Objects.equals(cnpj, that.cnpj) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(cnpj);
        result = 31 * result + Objects.hashCode(cpf);
        return result;
    }
}
