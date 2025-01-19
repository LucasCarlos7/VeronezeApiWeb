package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ItensMovimentoEntity;
import com.api.veroneze.data.entity.dto.ItensMovimentoRequestDTO;
import com.api.veroneze.service.ItensMovimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-movimento")
public class ItensMovimentoController {

    @Autowired
    private ItensMovimentoService itensMovimentoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ItensMovimentoEntity> addItensMovimento(@Valid @RequestBody ItensMovimentoRequestDTO itensMovimentoRequest) {
        var novoItem = itensMovimentoService.salvarItensMovimento(itensMovimentoRequest);

        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @GetMapping("/pesquisar/{movimentoId}")
    public ResponseEntity<List> getItensMovimentoById(@PathVariable Integer movimentoId){
        List<ItensMovimentoEntity> itens = itensMovimentoService.findByMovimentoEstoqueId(movimentoId);

        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarItensMovimento(@PathVariable Integer id) {
        itensMovimentoService.deletarItensMovimento(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
