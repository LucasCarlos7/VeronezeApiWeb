package com.api.veroneze.service;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoRequestDTO;
import com.api.veroneze.data.inteface.ProdutoRepository;
import com.api.veroneze.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public ProdutoEntity atualizarProduto(Integer produtoId, ProdutoRequestDTO produtoRequest) {

        ProdutoEntity produtoAtualizado = listarProdutoId(produtoId);

        produtoAtualizado.setNome(produtoRequest.nome());
        produtoAtualizado.setTipoProduto(produtoRequest.tipoProduto());
        produtoAtualizado.setPreco(produtoRequest.preco());
        produtoAtualizado.setDataAtualizacao(new Date());

        return produtoRepository.save(produtoAtualizado);
    }

    public ProdutoEntity listarProdutoId(Integer produtoId) {
        Optional<ProdutoEntity> obj = produtoRepository.findById(produtoId);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado " + produtoId));
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Integer produtoId) {
        ProdutoEntity produtoDeletado = listarProdutoId(produtoId);

        produtoRepository.deleteById(produtoDeletado.getId());
    }
}
