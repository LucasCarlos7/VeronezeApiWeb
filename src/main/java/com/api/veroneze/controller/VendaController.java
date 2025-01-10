package com.api.veroneze.controller;

import com.api.veroneze.data.entity.VendaEntity;
import com.api.veroneze.data.entity.dto.VendaRequestDTO;
import com.api.veroneze.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/adicionar")
    public ResponseEntity<VendaEntity> addVenda(@Valid @RequestBody VendaRequestDTO vendaRequestDTO) {
        var novaVenda = vendaService.salvarVenda(vendaRequestDTO);

        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar-total-orcamento/{vendaId}")
    public ResponseEntity<Void> atualizarTotalOrcamentoInicial(@PathVariable Integer vendaId) {
        vendaService.atualizarTotalOrcamento(vendaId);

        return ResponseEntity.ok().build();
    }
}
