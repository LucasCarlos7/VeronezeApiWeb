package com.api.veroneze.controller;

import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.dto.MovimentoEstoqueRequestDTO;
import com.api.veroneze.service.MovimentoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimento-estoque")
public class MovimentoEstoqueController {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @PostMapping("/adicionar")
    public ResponseEntity<MovimentoEstoqueEntity> addMovimentoEstoque(@Valid @RequestBody MovimentoEstoqueRequestDTO movimentoEstoqueRequest) {
        var novoMovimentoEstoque = movimentoEstoqueService.salvarMovimentoEstoque(movimentoEstoqueRequest);

        return new ResponseEntity<>(novoMovimentoEstoque, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MovimentoEstoqueEntity> atualizarMovimentoEstoque(@Valid @PathVariable Integer id, @RequestBody MovimentoEstoqueRequestDTO movimentoEstoqueRequest) {

        var movimentoEstoqueAtualizado = movimentoEstoqueService.atualizarMovimentoEstoque(id, movimentoEstoqueRequest);

        return new ResponseEntity<>(movimentoEstoqueAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllMovimentoEstoque() {
        List<MovimentoEstoqueEntity> movimentosEstoque = movimentoEstoqueService.listarTodosMovimentoEstoque();

        return new ResponseEntity<>(movimentosEstoque, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<MovimentoEstoqueEntity> getMovimentoEstoqueById(@PathVariable Integer id) {
        MovimentoEstoqueEntity movimentoEstoque = movimentoEstoqueService.listarMovimentoEstoqueId(id);

        return new ResponseEntity<>(movimentoEstoque, HttpStatus.OK);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = movimentoEstoqueService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
