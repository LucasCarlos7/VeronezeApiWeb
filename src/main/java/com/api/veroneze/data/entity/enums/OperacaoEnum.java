package com.api.veroneze.data.entity.enums;

public enum OperacaoEnum {

    ENTRADA(1),
    SAIDA(2),
    SAIDA_COMPOSTO(3);

    private int code;

    private OperacaoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OperacaoEnum valueOf(int code) {
        for (OperacaoEnum value : OperacaoEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Operação inválido.");
    }
}
