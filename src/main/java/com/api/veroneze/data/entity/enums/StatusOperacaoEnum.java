package com.api.veroneze.data.entity.enums;

public enum StatusOperacaoEnum {
    INICIADO(1),
    CONCLUIDO(2),
    CANCELADO(3);

    private int code;

    private StatusOperacaoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusOperacaoEnum valueOf(int code) {
        for (StatusOperacaoEnum value : StatusOperacaoEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Status inválido.");
    }
}
