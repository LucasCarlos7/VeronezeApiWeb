package com.api.veroneze.service;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.inteface.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoEntity criarProduto(ProdutoEntity prod) {
        prod.setId(null);
        produtoRepository.save(prod);
        return prod;
    }

    public ProdutoEntity atualizarProduto(Integer produtoId, ProdutoEntity produtoRequest) {

        ProdutoEntity produtoAtualizado = getProdutoId(produtoId);

        produtoAtualizado.setNome(produtoRequest.getNome());
        produtoAtualizado.setPreco(produtoRequest.getPreco());

        produtoRepository.save(produtoAtualizado);

        return produtoAtualizado;
    }

    public ProdutoEntity getProdutoId(Integer produtoId) {
        return produtoRepository.findById(produtoId).orElse(null);
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Integer produtoId) {
        ProdutoEntity produtoDeletado = getProdutoId(produtoId);

        produtoRepository.deleteById(produtoDeletado.getId());
    }
}
