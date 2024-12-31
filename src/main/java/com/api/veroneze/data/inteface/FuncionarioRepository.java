package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {

    @Query("SELECT MAX(f.id) FROM FuncionarioEntity f")
    Integer findLastId();
}
