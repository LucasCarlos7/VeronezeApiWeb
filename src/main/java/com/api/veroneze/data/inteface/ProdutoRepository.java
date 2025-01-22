package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.views.ProdutoComCompostoDTO;
import com.api.veroneze.data.entity.views.ProdutoCompostoDTO;
import com.api.veroneze.data.entity.views.ProdutoEstoqueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    @Query("SELECT new com.api.veroneze.data.entity.views.ProdutoEstoqueDTO(p.id, p.nome, p.tipoProduto, p.preco, p.dataCriacao, p.dataAtualizacao) " +
            "FROM ProdutoEntity p " +
            "WHERE p.id = :produtoId")
    ProdutoEstoqueDTO findProdutoEstoqueById(@Param("produtoId") Integer produtoId);

    @Query("SELECT new com.api.veroneze.data.entity.views.ProdutoComCompostoDTO(p.id, p.nome, p.tipoProduto, p.preco, p.dataCriacao, p.dataAtualizacao) " +
            "FROM ProdutoEntity p " +
            "WHERE p.id = :produtoId")
    ProdutoComCompostoDTO findProdutoComCompostoByProdutoId(@Param("produtoId") Integer produtoId);

    @Query("SELECT new com.api.veroneze.data.entity.views.ProdutoCompostoDTO(pce.produtoCompostoId, p.nome, pce.proporcao) " +
            "FROM ProdutoCompostoEntity pce INNER JOIN ProdutoEntity p ON p.id = pce.produtoCompostoId " +
            "WHERE pce.produtoId = :produtoId")
    List<ProdutoCompostoDTO> findProdutoCompostoByProdutoId(@Param("produtoId") Integer produtoId);

    @Query("SELECT MAX(p.id) FROM ProdutoEntity p")
    Integer findLastId();
}
