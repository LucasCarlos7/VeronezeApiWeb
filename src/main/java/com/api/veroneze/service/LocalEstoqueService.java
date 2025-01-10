package com.api.veroneze.service;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.LocalEstoqueEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.LocalEstoqueRequestDTO;
import com.api.veroneze.data.inteface.EstoqueRepository;
import com.api.veroneze.data.inteface.LocalEstoqueRepository;
import com.api.veroneze.data.inteface.ProdutoRepository;
import com.api.veroneze.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LocalEstoqueService {

    @Autowired
    private LocalEstoqueRepository localEstoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public LocalEstoqueEntity salvarLocalEstoque(LocalEstoqueRequestDTO localEstoqueRequest) {

        // Obtendo todos os produtos cadastrados
        List<ProdutoEntity> produtos = produtoRepository.findAll();

        LocalEstoqueEntity localEstoqueEntity = new LocalEstoqueEntity();

        localEstoqueEntity.setNome(localEstoqueRequest.nome().toUpperCase());
        localEstoqueEntity.setDataCriacao(new Date());

        localEstoqueRepository.save(localEstoqueEntity);

        // Inserindo registros na tabela Estoque
        for (ProdutoEntity produto : produtos) {
            EstoqueEntity estoque = new EstoqueEntity();
            estoque.setProdutoId(produto.getId());
            estoque.setLocalEstoqueId(localEstoqueEntity.getId());
            estoque.setMovimentoEntrada(0.0);
            estoque.setMovimentoSaida(0.0);
            estoque.setSaldoTotal(0.0);

            estoqueRepository.save(estoque);
        }

        return localEstoqueEntity;
    }

    public LocalEstoqueEntity atualizarLocalEstoque(Integer localEstoqueId, LocalEstoqueRequestDTO localEstoqueRequest) {

        LocalEstoqueEntity localEstoqueAtualizado = listarLocalEstoqueId(localEstoqueId);

        localEstoqueAtualizado.setNome(localEstoqueRequest.nome().toUpperCase());
        localEstoqueAtualizado.setDataAtualizacao(new Date());

        return localEstoqueRepository.save(localEstoqueAtualizado);
    }

    public LocalEstoqueEntity listarLocalEstoqueId(Integer localEstoqueId) {
        Optional<LocalEstoqueEntity> obj = localEstoqueRepository.findById(localEstoqueId);

        return obj.orElseThrow(() -> new ResourceNotFoundException("Local Estoque não encontrado."));
    }

    public List<LocalEstoqueEntity> listarTodosLocalEstoque() {
        return localEstoqueRepository.findAll();
    }

    public void deletarLocalEstoque(Integer localEstoqueId) {
        LocalEstoqueEntity localEstoqueDeletado = listarLocalEstoqueId(localEstoqueId);

        localEstoqueRepository.deleteById(localEstoqueId);
    }

    public Integer getNextId() {
        Integer lastId = localEstoqueRepository.findLastId();
        return (lastId != null) ? lastId + 1 : 1;
    }
}
