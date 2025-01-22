package com.api.veroneze.service;

import com.api.veroneze.data.entity.*;
import com.api.veroneze.data.entity.views.EstoqueDTO;
import com.api.veroneze.data.inteface.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LocalEstoqueService localEstoqueService;

    public List<EstoqueDTO> getEstoqueByProdutoId(Integer produtoId) {
        ProdutoEntity produto = produtoService.listarProdutoId(produtoId);

        return estoqueRepository.findEstoqueByProdutoId(produto.getId());
    }

    public EstoqueEntity findBylocalEstoqueIdAndProdutoId(Integer localEstoqueId, Integer produtoId) {
        // Valida o Local Estoque consultado;
        LocalEstoqueEntity localEstoquePesquisado = localEstoqueService.listarLocalEstoqueId(localEstoqueId);
        // Valida o Produto consultado;
        ProdutoEntity produtoPesquisado = produtoService.listarProdutoId(produtoId);
        // Realiza a consulta do Estoque existente;
        EstoqueEntity estoque = estoqueRepository.findBylocalEstoqueIdAndProdutoId(localEstoquePesquisado.getId(), produtoPesquisado.getId());

        return estoque;
    }
}
