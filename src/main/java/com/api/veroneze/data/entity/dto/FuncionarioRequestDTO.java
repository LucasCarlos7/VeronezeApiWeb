package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.CargoEnum;

public record FuncionarioRequestDTO(String nome, String login, String senha, CargoEnum cargo, String cpf,
                                    String telefone, String email, String cep, String endereco, String bairro,
                                    String numeroEndereco, String cidade, String UF) {
}
