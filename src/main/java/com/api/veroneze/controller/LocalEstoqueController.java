package com.api.veroneze.controller;

import com.api.veroneze.data.entity.LocalEstoqueEntity;
import com.api.veroneze.data.entity.dto.LocalEstoqueRequestDTO;
import com.api.veroneze.service.LocalEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local-estoque")
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueService localEstoqueService;

    @PostMapping("/adicionar")
    public ResponseEntity<LocalEstoqueEntity> addLocalEstoque(@RequestBody LocalEstoqueRequestDTO localEstoque) {
        var novoLocalEstoque = localEstoqueService.salvarLocalEstoque(localEstoque);

        return new ResponseEntity<>(novoLocalEstoque, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LocalEstoqueEntity> atualizarLocalEstoque(@PathVariable Integer id, @RequestBody LocalEstoqueRequestDTO localEstoqueRequest) {
        var localEstoqueAtualizado = localEstoqueService.atualizarLocalEstoque(id, localEstoqueRequest);

        return new ResponseEntity<>(localEstoqueAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllLocalEstoque() {
        List<LocalEstoqueEntity> localEstoques = localEstoqueService.listarTodosLocalEstoque();

        return new ResponseEntity<>(localEstoques, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<LocalEstoqueEntity> getLocalEstoqueById(@PathVariable Integer id) {
        LocalEstoqueEntity localEstoque = localEstoqueService.listarLocalEstoqueId(id);

        return new ResponseEntity<>(localEstoque, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarLocalEstoque(@PathVariable Integer id) {
        localEstoqueService.deletarLocalEstoque(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = localEstoqueService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
