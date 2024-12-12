package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List> getAllProduto() {
        List<ProdutoEntity> produtos = produtoService.listarTodosProdutos();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ProdutoEntity> getProdutoById(@PathVariable Integer id) {
        ProdutoEntity produto = produtoService.getProdutoId(id);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoEntity> addProduto(@RequestBody ProdutoEntity produto) {
        var novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoEntity produto) {
        var produtoAtualizado = produtoService.atualizarProduto(id, produto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

