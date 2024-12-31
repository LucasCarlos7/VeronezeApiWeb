package com.api.veroneze.service;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.dto.EstoqueRequestDTO;
import com.api.veroneze.data.inteface.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    public EstoqueEntity salvarEstoque(EstoqueRequestDTO estoqueRequestDTO) {

        EstoqueEntity estoqueEntity = new EstoqueEntity();

        estoqueEntity.setLocalEstoqueId(estoqueRequestDTO.localEstoqueId());
        estoqueEntity.setMovimentoEntrada(estoqueRequestDTO.movimentoEntrada());
        estoqueEntity.setMovimentoSaida(estoqueRequestDTO.movimetoSaida());
        estoqueEntity.setDataAtualizacao(new Date());

        return estoqueRepository.save(estoqueEntity);
    }
}
