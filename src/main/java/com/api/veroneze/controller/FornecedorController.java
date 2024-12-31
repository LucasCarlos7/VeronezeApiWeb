package com.api.veroneze.controller;

import com.api.veroneze.data.entity.FornecedorEntity;
import com.api.veroneze.data.entity.dto.FornecedorRequestDTO;
import com.api.veroneze.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping("/adicionar")
    public ResponseEntity<FornecedorEntity> addFornecedor(@Valid @RequestBody FornecedorRequestDTO fornecedor) {
        var novoFornecedor = fornecedorService.salvarFornecedor(fornecedor);

        return new ResponseEntity<>(novoFornecedor, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FornecedorEntity> atualizarFornecedor(@Valid @PathVariable Integer id, @RequestBody FornecedorRequestDTO fornecedorRequest) {
        var fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedorRequest);

        return new ResponseEntity<>(fornecedorAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllFornecedores() {
        List<FornecedorEntity> fornecedores = fornecedorService.listarTodosFornecedores();

        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FornecedorEntity> getFornecedorById(@PathVariable Integer id) {
        FornecedorEntity fornecedor = fornecedorService.listarFornecedorId(id);

        return new ResponseEntity<>(fornecedor, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFornecedor(@PathVariable Integer id) {
        fornecedorService.deletarFornecedor(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = fornecedorService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
