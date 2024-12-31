package com.api.veroneze.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int clienteId;
    private int funcionarioId;
    private int estoqueId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataVenda;

    //Construtor


    public VendaEntity() {
    }

    public VendaEntity(int id, int clienteId, int funcionarioId, int estoqueId, Date dataVenda) {
        this.id = id;
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
        this.estoqueId = estoqueId;
        this.dataVenda = dataVenda;
    }

    //Getters e Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public int getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(int estoqueId) {
        this.estoqueId = estoqueId;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
