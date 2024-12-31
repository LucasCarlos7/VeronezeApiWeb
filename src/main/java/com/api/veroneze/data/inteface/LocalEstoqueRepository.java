package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.LocalEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocalEstoqueRepository extends JpaRepository<LocalEstoqueEntity, Integer> {

    @Query("SELECT MAX(le.id) FROM LocalEstoqueEntity le")
    Integer findLastId();
}
