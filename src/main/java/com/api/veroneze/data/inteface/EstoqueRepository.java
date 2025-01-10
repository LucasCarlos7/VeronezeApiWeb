package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.EstoqueIdEntity;
import com.api.veroneze.data.entity.dto.EstoqueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, EstoqueIdEntity> {

    EstoqueEntity findBylocalEstoqueIdAndProdutoId(Integer localEstoqueId, Integer produtoId);

    @Query("SELECT new com.api.veroneze.data.entity.dto.EstoqueDTO(e.localEstoqueId, le.nome, e.saldoTotal) " +
            "FROM EstoqueEntity e " +
            "JOIN ProdutoEntity p ON e.produtoId = p.id " +
            "JOIN LocalEstoqueEntity le ON e.localEstoqueId = le.id " +
            "WHERE p.id = :produtoId")
    List<EstoqueDTO> findEstoqueByProdutoId(@Param("produtoId") Integer id);
}