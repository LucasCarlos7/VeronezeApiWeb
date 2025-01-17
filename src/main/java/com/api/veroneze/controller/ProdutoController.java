package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoRequestDTO;
import com.api.veroneze.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoEntity> addProduto(@Valid @RequestBody ProdutoRequestDTO produto) {
        var novoProduto = produtoService.salvarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@Valid @PathVariable Integer id, @RequestBody ProdutoRequestDTO produto) {
        var produtoAtualizado = produtoService.atualizarProduto(id, produto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllProdutos() {
        List<ProdutoEntity> produtos = produtoService.listarTodosProdutos();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ProdutoEntity> getProdutoById(@PathVariable Integer id) {
        ProdutoEntity produto = produtoService.listarProdutoId(id);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = produtoService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}

