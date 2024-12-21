package com.api.veroneze.data.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Estoque")
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double quantidade;
    private Date dataAtualizacao;
    private int produtoId;
    private int localEstoqueId;
}
