package com.api.veroneze.service;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.LocalEstoqueEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoRequestDTO;
import com.api.veroneze.data.entity.views.EstoqueDTO;
import com.api.veroneze.data.entity.views.ProdutoComCompostoDTO;
import com.api.veroneze.data.entity.views.ProdutoCompostoDTO;
import com.api.veroneze.data.entity.views.ProdutoEstoqueDTO;
import com.api.veroneze.data.inteface.EstoqueRepository;
import com.api.veroneze.data.inteface.LocalEstoqueRepository;
import com.api.veroneze.data.inteface.ProdutoRepository;
//import com.api.veroneze.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LocalEstoqueRepository localEstoqueRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public ProdutoEntity salvarProduto(ProdutoRequestDTO produtoRequest) {

        List<LocalEstoqueEntity> localEstoqueEntities = localEstoqueRepository.findAll();

        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity.setNome(produtoRequest.nome().toUpperCase());
        produtoEntity.setTipoProduto(produtoRequest.tipoProduto());
        produtoEntity.setPreco(produtoRequest.preco());
        produtoEntity.setDataCriacao(new Date());

        produtoRepository.save(produtoEntity);

        try {
            for (LocalEstoqueEntity local : localEstoqueEntities) {
                EstoqueEntity estoque = new EstoqueEntity();

                estoque.setProdutoId(produtoEntity.getId());
                estoque.setLocalEstoqueId(local.getId());
                estoque.setMovimentoEntrada(0.0);
                estoque.setMovimentoSaida(0.0);
                estoque.setSaldoTotal(0.0);

                estoqueRepository.save(estoque);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar o produto.");
        }

        return produtoEntity;
    }

    public ProdutoEntity atualizarProduto(Integer produtoId, ProdutoRequestDTO produtoRequest) {

        ProdutoEntity produtoAtualizado = listarProdutoId(produtoId);

        produtoAtualizado.setNome(produtoRequest.nome().toUpperCase());
        produtoAtualizado.setTipoProduto(produtoRequest.tipoProduto());
        produtoAtualizado.setPreco(produtoRequest.preco());
        produtoAtualizado.setDataAtualizacao(new Date());

        return produtoRepository.save(produtoAtualizado);
    }

    public ProdutoEntity listarProdutoId(Integer produtoId) {
        Optional<ProdutoEntity> obj = produtoRepository.findById(produtoId);
        return obj.orElseThrow(() -> new RuntimeException("Produto ID: " + produtoId + " não encontrado!"));
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Integer produtoId) {
        ProdutoEntity produtoDeletado = listarProdutoId(produtoId);

        produtoRepository.deleteById(produtoDeletado.getId());
    }

    public Integer getNextId() {
        Integer lastId = produtoRepository.findLastId();
        return (lastId != null) ? lastId + 1 : 1;
    }

    public ProdutoEstoqueDTO getProdutoEstoqueById(Integer produtoId) {
        // Busca os dados do produto
        ProdutoEstoqueDTO produtoEstoqueDTO = produtoRepository.findProdutoEstoqueById(produtoId);

        if (produtoEstoqueDTO == null) {
            throw new RuntimeException("Produto não encontrado para o ID: " + produtoId);
        }

        // Busca os estoques relacionados ao produto
        List<EstoqueDTO> estoques = estoqueRepository.findEstoqueByProdutoId(produtoId);

        // Preenche a lista de estoques no DTO
        produtoEstoqueDTO.setEstoqueList(estoques);

        return produtoEstoqueDTO;
    }

    public ProdutoComCompostoDTO getProdutoCompostoByProdutoId(Integer produtoId) {
        // Busca os dados do produto
        ProdutoComCompostoDTO produtoComCompostoDTO = produtoRepository.findProdutoComCompostoByProdutoId(produtoId);

        if (produtoComCompostoDTO == null) {
            throw new RuntimeException("Produto não encontrado para o ID: " + produtoId);
        }

        List<ProdutoCompostoDTO> produtoCompostoDTOList = produtoRepository.findProdutoCompostoByProdutoId(produtoComCompostoDTO.getId());

        produtoComCompostoDTO.setProdutosList(produtoCompostoDTOList);

        return produtoComCompostoDTO;
    }
}
