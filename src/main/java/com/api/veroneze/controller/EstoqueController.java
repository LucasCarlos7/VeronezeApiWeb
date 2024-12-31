package com.api.veroneze.controller;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.dto.EstoqueRequestDTO;
import com.api.veroneze.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @PostMapping("/adicionar")
    public ResponseEntity<EstoqueEntity> addEstoque(@Valid @RequestBody EstoqueRequestDTO estoqueRequestDTO) {
        var novoEstoque = estoqueService.salvarEstoque(estoqueRequestDTO);

        return new ResponseEntity<>(novoEstoque, HttpStatus.OK);
    }
}
