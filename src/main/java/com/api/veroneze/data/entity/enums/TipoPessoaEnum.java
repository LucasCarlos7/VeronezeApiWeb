package com.api.veroneze.data.entity.enums;

public enum TipoPessoaEnum {
    FISICA(1),
    JURIDICA(2);

    private int code;

    private TipoPessoaEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoPessoaEnum valueOf(int code) {
        for (TipoPessoaEnum value : TipoPessoaEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Tipo Pesssoa inválido");
    }
}
