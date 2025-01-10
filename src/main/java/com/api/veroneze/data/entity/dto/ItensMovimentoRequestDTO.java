package com.api.veroneze.data.entity.dto;

import jakarta.validation.constraints.NotNull;

public record ItensMovimentoRequestDTO(Integer produtoId, Integer MovimentoEstoqueId, Double quantidade) {
}