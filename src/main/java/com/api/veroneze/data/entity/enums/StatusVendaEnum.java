package com.api.veroneze.data.entity.enums;

public enum StatusVendaEnum {
    ABERTO(1),
    FECHADO(2);

    private int code;

    private StatusVendaEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusVendaEnum valueOf(int code) {
        for (StatusVendaEnum value : StatusVendaEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código status inválido");
    }
}
