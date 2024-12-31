package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoqueEntity, Integer> {

    @Query("SELECT MAX(me.id) FROM MovimentoEstoqueEntity me")
    Integer findLastId();
}
