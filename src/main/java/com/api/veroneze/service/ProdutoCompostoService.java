package com.api.veroneze.service;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoCompostoRequestDTO;
import com.api.veroneze.data.inteface.ProdutoCompostoRepository;
import com.api.veroneze.data.inteface.ProdutoRepository;
//import com.api.veroneze.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoCompostoService {

    @Autowired
    private ProdutoCompostoRepository produtoCompostoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoCompostoEntity salvarProdutoComposto(ProdutoCompostoRequestDTO produtoComposto) {
        ProdutoEntity novoProduto = produtoRepository.findById(produtoComposto.produtoId()).orElseThrow(()
                -> new RuntimeException("Produto não encontrado"));
        ProdutoEntity novoProdutoComposto = produtoRepository.findById(produtoComposto.produtoCompostoId()).orElseThrow(()
                -> new RuntimeException("Produto não encontrado"));

        if (novoProduto == novoProdutoComposto) {
            throw new RuntimeException("Produto composto não pode ser o mesmo que o Produto primário.");
        } else if (produtoComposto.proporcao() <= 0) {
            throw new RuntimeException("A proporção deve ser maior que zero.");
        }

        ProdutoCompostoEntity produtoCompostoEntity = new ProdutoCompostoEntity();

        produtoCompostoEntity.setProdutoId(novoProduto.getId());
        produtoCompostoEntity.setProdutoCompostoId(novoProdutoComposto.getId());
        produtoCompostoEntity.setProporcao(produtoComposto.proporcao());

        return produtoCompostoRepository.save(produtoCompostoEntity);
    }

    public ProdutoCompostoEntity atualizarProdutoComposto(Integer prodCompostoId, ProdutoCompostoRequestDTO prodCompostoRequest) {
        ProdutoCompostoEntity produtoCompostoAtualizado = listarProdutoCompostoId(prodCompostoId);

        produtoCompostoAtualizado.setProdutoId(prodCompostoRequest.produtoId());
        produtoCompostoAtualizado.setProdutoCompostoId(prodCompostoRequest.produtoCompostoId());
        produtoCompostoAtualizado.setProporcao(prodCompostoRequest.proporcao());

        return produtoCompostoRepository.save(produtoCompostoAtualizado);
    }

    public List<ProdutoCompostoEntity> listarTodosProdutosComposto() {
        return produtoCompostoRepository.findAll();
    }

    public ProdutoCompostoEntity listarProdutoCompostoId(Integer prodCompostoId) {
        Optional<ProdutoCompostoEntity> prodComposto = produtoCompostoRepository.findById(prodCompostoId);
        return prodComposto.orElseThrow(() -> new RuntimeException("Produto Composto não encontrado " + prodCompostoId));
    }

    public void deletarProdutoComposto(Integer prodCompostoId) {
        ProdutoCompostoEntity produtoDeletado = listarProdutoCompostoId(prodCompostoId);

        produtoCompostoRepository.deleteById(produtoDeletado.getId());
    }

    public List<ProdutoCompostoEntity> findByProdutoId(Integer produtoId) {
        List<ProdutoCompostoEntity> produtosCompostos = produtoCompostoRepository.findByProdutoId(produtoId);

        if (produtosCompostos == null) {
            throw new RuntimeException("Produto composto não cadastrado!");
        }

        return produtosCompostos;
    }
}
