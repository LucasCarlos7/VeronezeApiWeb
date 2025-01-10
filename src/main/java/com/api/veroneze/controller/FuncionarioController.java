package com.api.veroneze.controller;

import com.api.veroneze.data.entity.FuncionarioEntity;
import com.api.veroneze.data.entity.dto.FuncionarioRequestDTO;
import com.api.veroneze.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/adicionar")
    public ResponseEntity<FuncionarioEntity> addFuncionario(@Valid @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        var novoFuncionario = funcionarioService.salvarFuncionario(funcionarioRequestDTO);

        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioEntity> atualizarFuncionario(@Valid @PathVariable Integer id, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        var funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioRequestDTO);

        return new ResponseEntity<>(funcionarioAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllFuncionarios() {
        List<FuncionarioEntity> funcionarios = funcionarioService.listarTodosFuncionarios();

        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FuncionarioEntity> getFuncionarioById(@PathVariable Integer id) {
        FuncionarioEntity funcionario = funcionarioService.listarFuncionarioId(id);

        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFuncionario(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = funcionarioService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
