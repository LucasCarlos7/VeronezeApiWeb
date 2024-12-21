package com.api.veroneze.data.entity.enums;

public enum TipoOperacaoEnum {
    ENTRADA(1),
    SAIDA(2),
    TRANSFERENCIA(3);

    private int code;

    private TipoOperacaoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoOperacaoEnum valueOf(int code) {
        for (TipoOperacaoEnum value : TipoOperacaoEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Tipo Operação inválido");
    }
}
