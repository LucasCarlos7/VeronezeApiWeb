package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.views.MovimentoEstoqueComProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoqueEntity, Integer> {

    @Query("SELECT m.statusOperacao FROM MovimentoEstoqueEntity m WHERE m.id = :id")
    Integer findStatusById(@Param("id") Integer id);

    @Query("SELECT MAX(me.id) FROM MovimentoEstoqueEntity me")
    Integer findLastId();

    @Query("SELECT new com.api.veroneze.data.entity.views.MovimentoEstoqueComProdutoDTO" +
            "(id, localEstoqueId, localEstoqueSaidaId, fornecedorId, tipoOperacao, statusOperacao, " +
            "valorOperacao, dataOperacao)" +
            "FROM MovimentoEstoqueEntity " +
            "WHERE id = :movimentoEstoqueId")
    MovimentoEstoqueComProdutoDTO findMovimentoEstoqueComProdutoById(
            @Param("movimentoEstoqueId") Integer movimentoEstoqueId);
}
