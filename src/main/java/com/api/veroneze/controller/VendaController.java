package com.api.veroneze.controller;

import com.api.veroneze.data.entity.VendaEntity;
import com.api.veroneze.data.entity.dto.VendaRequestDTO;
import com.api.veroneze.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/adicionar")
    public ResponseEntity<VendaEntity> addVenda(@Valid @RequestBody VendaRequestDTO vendaRequestDTO) {
        var novaVenda = vendaService.salvarVenda(vendaRequestDTO);

        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{vendaId}")
    public ResponseEntity<VendaEntity> atualizarVenda(@Valid @PathVariable Integer vendaId, @RequestBody VendaRequestDTO vendaRequestDTO) {
        var vendaAtualizada = vendaService.atualizarVenda(vendaId, vendaRequestDTO);

        return new ResponseEntity<>(vendaAtualizada, HttpStatus.OK);
    }

    @PutMapping("/finalizar-cancelar-venda/{vendaId}")
    public ResponseEntity<VendaEntity> finalizarVenda(@Valid @PathVariable Integer vendaId, @RequestBody VendaRequestDTO vendaRequestDTO) {
        var vendaAtualizada = vendaService.finalizarOuCancelarVenda(vendaId, vendaRequestDTO);

        var vendaConsultada = vendaService.listarVendaId(vendaAtualizada.getId());

        return new ResponseEntity<>(vendaConsultada, HttpStatus.OK);
    }

//    @PutMapping("/atualizar-total-orcamento/{vendaId}")
//    public ResponseEntity<Void> atualizarTotalOrcamentoInicial(@PathVariable Integer vendaId) {
//        vendaService.atualizarTotalOrcamento(vendaId);
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllVendas() {
        List<VendaEntity> vendas = vendaService.listarTodasVendas();

        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<VendaEntity> getVendaById(@PathVariable Integer id) {
        VendaEntity venda = vendaService.listarVendaId(id);

        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    @GetMapping("/next-id")
    public ResponseEntity<Integer> getNextId() {
        Integer nextId = vendaService.getNextId();

        return ResponseEntity.ok(nextId);
    }
}
