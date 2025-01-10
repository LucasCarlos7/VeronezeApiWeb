package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    @Query("SELECT MAX(p.id) FROM ProdutoEntity p")
    Integer findLastId();
}
