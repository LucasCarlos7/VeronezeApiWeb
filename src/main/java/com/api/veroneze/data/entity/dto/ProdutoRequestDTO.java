package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.TipoProdutoEnum;

public record ProdutoRequestDTO(String nome, TipoProdutoEnum tipoProduto, double preco) {
}
