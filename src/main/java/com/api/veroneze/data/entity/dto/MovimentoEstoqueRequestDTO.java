package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;
import com.api.veroneze.data.entity.enums.TipoPessoaEnum;

public record MovimentoEstoqueRequestDTO(Integer localEstoqueEntradaId, Integer localEstoqueSaidaId, Integer fornecedorId,
                                         Integer funcionarioId, TipoOperacaoEnum tipoOperacao, Integer status) {
}
