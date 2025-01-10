package com.api.veroneze.controller;

import com.api.veroneze.data.entity.ItensVendaEntity;
import com.api.veroneze.data.entity.dto.ItensVendaRequestDTO;
import com.api.veroneze.service.ItensVendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-venda")
public class ItensVendaController {

    @Autowired
    private ItensVendaService itensVendaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ItensVendaEntity> addItensVenda(@Valid @RequestBody ItensVendaRequestDTO itensVendaRequestDTO) {
        var novoItem = itensVendaService.salvarItensVenda(itensVendaRequestDTO);

        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @PutMapping("/{vendaId}/atualiza-status/{itemId}")
    public ResponseEntity<Void> atualizaStatusItemVenda(@PathVariable Integer vendaId, @PathVariable Integer itemId) {
        itensVendaService.atualizarStatusItemVenda(vendaId, itemId);

        return ResponseEntity.ok().build();
    }
}
