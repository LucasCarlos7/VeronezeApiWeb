package com.api.veroneze.service;

import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.dto.MovimentoEstoqueRequestDTO;
import com.api.veroneze.data.inteface.MovimentoEstoqueRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoEstoqueService {

    @Autowired
    MovimentoEstoqueRepository movimentoEstoqueRepository;

    public MovimentoEstoqueEntity salvarMovimentoEstoque(MovimentoEstoqueRequestDTO movimentoEstoque) {

        MovimentoEstoqueEntity movimentoEstoqueEntity = new MovimentoEstoqueEntity();

        movimentoEstoqueEntity.setLocalEstoqueEntradaId(movimentoEstoque.localEstoqueEntradaId());
        movimentoEstoqueEntity.setLocalEstoqueSaidaId(movimentoEstoque.localEstoqueSaidaId());
        movimentoEstoqueEntity.setFornecedorId(movimentoEstoque.fornecedorId());
        movimentoEstoqueEntity.setFuncionarioId(movimentoEstoque.funcionarioId());
        movimentoEstoqueEntity.setTipoOperacao(movimentoEstoque.tipoOperacao());
        movimentoEstoqueEntity.setStatus(movimentoEstoque.status());
        movimentoEstoqueEntity.setDataOperacao(new Date());

        return movimentoEstoqueRepository.save(movimentoEstoqueEntity);
    }

    public MovimentoEstoqueEntity atualizarMovimentoEstoque(Integer movimentoEstoqueId, MovimentoEstoqueRequestDTO movimentoEstoque) {

        MovimentoEstoqueEntity movimentoEstoqueAtualizado = listarMovimentoEstoqueId(movimentoEstoqueId);

        if (movimentoEstoqueAtualizado.getStatus() == 2) {
            return null;
        }

        movimentoEstoqueAtualizado.setLocalEstoqueEntradaId(movimentoEstoque.localEstoqueEntradaId());
        movimentoEstoqueAtualizado.setLocalEstoqueSaidaId(movimentoEstoque.localEstoqueSaidaId());
        movimentoEstoqueAtualizado.setFornecedorId(movimentoEstoque.fornecedorId());
        movimentoEstoqueAtualizado.setFuncionarioId(movimentoEstoque.funcionarioId());
        movimentoEstoqueAtualizado.setTipoOperacao(movimentoEstoque.tipoOperacao());
        movimentoEstoqueAtualizado.setStatus(movimentoEstoque.status());
        movimentoEstoqueAtualizado.setDataOperacao(new Date());

        return movimentoEstoqueRepository.save(movimentoEstoqueAtualizado);
    }

    public MovimentoEstoqueEntity listarMovimentoEstoqueId(Integer movimentoEstoqueId) {
        Optional<MovimentoEstoqueEntity> obj = movimentoEstoqueRepository.findById(movimentoEstoqueId);

        return obj.orElseThrow(() -> new RuntimeException("Movimentação ID: " + movimentoEstoqueId + " não encontrada!"));
    }

    public List<MovimentoEstoqueEntity> listarTodosMovimentoEstoque() {
        return movimentoEstoqueRepository.findAll();
    }

    public Integer getNextId() {
        Integer lastId = movimentoEstoqueRepository.findLastId();

        return (lastId != null) ? lastId + 1 : 1;
    }
}
