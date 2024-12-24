package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoCompostoRepository extends JpaRepository<ProdutoCompostoEntity, Integer> {
}
