package com.api.veroneze.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EstoqueIdEntity implements Serializable {


    private Integer produtoId;
    private Integer localEstoqueId;

    public EstoqueIdEntity() {
    }

    public EstoqueIdEntity(Integer produtoId, Integer localEstoqueId) {
        this.produtoId = produtoId;
        this.localEstoqueId = localEstoqueId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    public void setLocalEstoqueId(Integer localEstoqueId) {
        this.localEstoqueId = localEstoqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueIdEntity estoqueIdEntity = (EstoqueIdEntity) o;
        return Objects.equals(produtoId, estoqueIdEntity.produtoId) &&
                Objects.equals(localEstoqueId, estoqueIdEntity.localEstoqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, localEstoqueId);
    }
}
