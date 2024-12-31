package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ClienteEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ClienteRequestDTO;
import com.api.veroneze.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/adicionar")
    public ResponseEntity<ClienteEntity> addCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        var novoCliente = clienteService.salvarCliente(clienteRequestDTO);

        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteEntity> atualizarCliente(@Valid @PathVariable Integer id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        var clienteAtualizado = clienteService.atualizarCliente(id, clienteRequestDTO);

        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllClientes() {
        List<ClienteEntity> clientes = clienteService.listarTodosClientes();

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Integer id) {
        ClienteEntity cliente = clienteService.listarClienteId(id);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarCliente(@PathVariable Integer id) {
        clienteService.deletarCliente(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = clienteService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
