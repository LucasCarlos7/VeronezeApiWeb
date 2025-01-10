package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoCompostoRepository extends JpaRepository<ProdutoCompostoEntity, Integer> {

    List<ProdutoCompostoEntity> findByProdutoId(Integer produtoId);
}
