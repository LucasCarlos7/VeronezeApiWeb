package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ItensVendaEntity;
import com.api.veroneze.data.entity.ItensVendaIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItensVendaRepository extends JpaRepository<ItensVendaEntity, ItensVendaIdEntity> {

    List<ItensVendaEntity> findByVendaId(Integer vendaId);

    Optional<ItensVendaEntity> findByVendaIdAndItem(Integer vendaId, Integer item);

    @Query("SELECT MAX(i.item) FROM ItensVendaEntity i")
    Integer findLastItem();
}
