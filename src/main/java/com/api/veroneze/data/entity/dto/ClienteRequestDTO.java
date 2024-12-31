package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.TipoPessoaEnum;

public record ClienteRequestDTO(String nome, TipoPessoaEnum tipoPessoa, String cpf, String cnpj, String telefone,
                                String email, String cep, String endereco, String bairro, String numeroEnd) {
}
