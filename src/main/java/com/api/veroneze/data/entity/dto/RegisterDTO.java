package com.api.veroneze.data.entity.dto;

import com.api.veroneze.data.entity.enums.CargoEnum;

public record RegisterDTO(String login, String senha, CargoEnum cargo) {
}
