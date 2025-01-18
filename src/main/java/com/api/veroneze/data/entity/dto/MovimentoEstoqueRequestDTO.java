package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.StatusOperacaoEnum;
import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;

public record MovimentoEstoqueRequestDTO(Integer localEstoqueId, Integer localEstoqueSaidaId, Integer fornecedorId,
                                         Double valorOperacao, TipoOperacaoEnum tipoOperacao,
                                         StatusOperacaoEnum statusOperacao) {
}
