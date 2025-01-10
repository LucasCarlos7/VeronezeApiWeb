package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.dto.ProdutoCompostoRequestDTO;
import com.api.veroneze.service.ProdutoCompostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtoComposto")
public class ProdutoCompostoController {

    @Autowired
    private ProdutoCompostoService produtoCompostoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoCompostoEntity> addProdutoComposto(@RequestBody ProdutoCompostoRequestDTO produtoComposto) {
        var novoProdutoComposto = produtoCompostoService.salvarProdutoComposto(produtoComposto);

        return new ResponseEntity<>(novoProdutoComposto, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoCompostoEntity> atualizarProdutoComposto(@PathVariable Integer id, @RequestBody ProdutoCompostoRequestDTO produtoComposto) {
        var produtoAtualizado = produtoCompostoService.atualizarProdutoComposto(id, produtoComposto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllProdutoComposto() {
        List<ProdutoCompostoEntity> produtos = produtoCompostoService.listarTodosProdutosComposto();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ProdutoCompostoEntity> getProdutoCompostoById(@PathVariable Integer id) {
        ProdutoCompostoEntity produto = produtoCompostoService.listarProdutoCompostoId(id);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarProdutoComposto(@PathVariable Integer id) {
        produtoCompostoService.deletarProdutoComposto(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
