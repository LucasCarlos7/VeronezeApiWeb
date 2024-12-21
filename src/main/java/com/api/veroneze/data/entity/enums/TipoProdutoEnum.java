package com.api.veroneze.data.entity.enums;

public enum TipoProdutoEnum {

    MATÉRIA_PRIMA(1),
    PRODUTO_EM_PROCESSO(2),
    PRODUTO_ACABADO(3),
    OUTROS(4);

    private int code;

    private TipoProdutoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoProdutoEnum valueOf(int code) {
        for (TipoProdutoEnum value : TipoProdutoEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código Tipo Produto inválido");
    }
}