package com.api.veroneze.data.entity.enums;

public enum StatusProdutoVendaEnum {

    ATIVO(1),
    CANCELADO(2);

    private int code;

    private StatusProdutoVendaEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusProdutoVendaEnum valueOf(int code) {
        for (StatusProdutoVendaEnum value : StatusProdutoVendaEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo StatusProdutoVenda inválido.");
    }
}
