package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.views.ListagemProdutoCompostoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoCompostoRepository extends JpaRepository<ProdutoCompostoEntity, Integer> {

    List<ProdutoCompostoEntity> findByProdutoId(Integer produtoId);

    @Query("SELECT new com.api.veroneze.data.entity.views.ListagemProdutoCompostoDTO " +
            "(pc.produtoId, pp.nome, pc.produtoCompostoId, ps.nome, pc.proporcao) " +
            "FROM ProdutoCompostoEntity pc INNER JOIN ProdutoEntity pp ON pp.id = pc.produtoId " +
            "INNER JOIN ProdutoEntity ps ON ps.id = pc.produtoCompostoId " +
            "WHERE pc.produtoId = :produtoId")
    List<ListagemProdutoCompostoDTO> findProdutoCompostoByProdutoId(@Param("produtoId") Integer produtoId);
}
