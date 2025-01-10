package com.api.veroneze.data.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItensVendaIdEntity implements Serializable {

    private Integer vendaId;
    private Integer item;

    public ItensVendaIdEntity() {
    }

    public ItensVendaIdEntity(Integer vendaId, Integer item) {
        this.vendaId = vendaId;
        this.item = item;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensVendaIdEntity itensVendaIdEntity = (ItensVendaIdEntity) o;
        return Objects.equals(vendaId, itensVendaIdEntity.vendaId) &&
                Objects.equals(item, itensVendaIdEntity.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, item);
    }
}
