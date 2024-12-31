package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Integer> {

    @Query("SELECT MAX(f.id) FROM FornecedorEntity f")
    Integer findLastId();
}
