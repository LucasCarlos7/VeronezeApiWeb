package com.api.veroneze.data.entity.enums;

public enum CargoEnum {
    VENDEDOR(1),
    ADMINISTRATIVO(2),
    GERENTE(3),
    DIRETORIA(4);

    private int code;

    private CargoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CargoEnum valueOf(int code) {
        for (CargoEnum value : CargoEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Cargo inválido");
    }
}
