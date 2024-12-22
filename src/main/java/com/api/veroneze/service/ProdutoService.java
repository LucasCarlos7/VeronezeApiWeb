package com.api.veroneze.service;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoRequestDTO;
import com.api.veroneze.data.inteface.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoEntity salvarProduto(ProdutoRequestDTO produtoRequest) {

        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity.setNome(produtoRequest.nome());
        produtoEntity.setTipoProduto(produtoRequest.tipoProduto());
        produtoEntity.setPreco(produtoRequest.preco());
        produtoEntity.setDataCriacao(new Date());

        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity atualizarProduto(Integer produtoId, ProdutoEntity produtoRequest) {

        ProdutoEntity produtoAtualizado = listarProdutoId(produtoId);

        produtoAtualizado.setNome(produtoRequest.getNome());
        produtoAtualizado.setTipoProduto(produtoRequest.getTipoProduto());
        produtoAtualizado.setPreco(produtoRequest.getPreco());
        produtoAtualizado.setDataAtualizacao(new Date());

        return produtoRepository.save(produtoAtualizado);
    }

    public ProdutoEntity listarProdutoId(Integer produtoId) {
        return produtoRepository.findById(produtoId).orElse(null);
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Integer produtoId) {
        ProdutoEntity produtoDeletado = produtoRepository.getReferenceById(produtoId);

        produtoRepository.deleteById(produtoDeletado.getId());
    }
}
