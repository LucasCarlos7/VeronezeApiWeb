package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

    @Query("SELECT MAX(v.id) FROM VendaEntity v")
    Integer findLastId();
}
