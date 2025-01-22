package com.api.veroneze.controller;

import com.api.veroneze.data.entity.views.EstoqueDTO;
import com.api.veroneze.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/saldo-produto/{produtoId}")
    public ResponseEntity<List<EstoqueDTO>> getEstoqueByProdutoId(@PathVariable Integer produtoId) {
        List<EstoqueDTO> estoques = estoqueService.getEstoqueByProdutoId(produtoId);

        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }
}
