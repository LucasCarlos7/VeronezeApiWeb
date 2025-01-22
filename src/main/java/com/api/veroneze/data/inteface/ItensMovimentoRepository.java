package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ItensMovimentoEntity;
import com.api.veroneze.data.entity.views.ItensMovimentoEstoqueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItensMovimentoRepository extends JpaRepository<ItensMovimentoEntity, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM itens_movimento where produto_primario_id = :produtoId")
    void deleteByProdutoId(@Param("produtoId") Integer produtoId);

    List<ItensMovimentoEntity> findByMovimentoEstoqueId(Integer movimentoEstoqueId);

    // Calcula o valor total dos produtos filtrando pelo id do MovimentoEstoque e retornando a soma
    @Query("SELECT SUM(im.valorTotalProduto) FROM ItensMovimentoEntity im " +
            "WHERE im.movimentoEstoqueId = :id AND im.operacao = 1")
    Double findValorTotalProdutos(@Param("id") Integer id);

    @Query("SELECT new com.api.veroneze.data.entity.views.ItensMovimentoEstoqueDTO " +
            "(i.produtoId, i.nomeProduto, i.quantidade, i.valorUnitarioProduto, i.valorTotalProduto, i.operacao) " +
            "FROM ItensMovimentoEntity i " +
            "WHERE i.movimentoEstoqueId = :movimentoEstoqueId")
    List<ItensMovimentoEstoqueDTO> findItensMovimentoEstoqueByMovimentoId(@Param("movimentoEstoqueId") Integer movimentoEstoqueId);

//    @Query(value = "SELECT new com.api.veroneze.data.entity.views.ItensMovimentoEstoqueDTO " +
//            "(produto_id, nome_produto, quantidade, valor_unitario_produto, valor_total_produto, operacao) " +
//            "FROM ItensMovimento " +
//            "WHERE movimento_estoque_id = :movimentoEstoqueId", nativeQuery = true)
//    List<ItensMovimentoEstoqueDTO> findItensMovimentoEstoqueByMovimentoId(@Param("movimentoEstoqueId") Integer movimentoEstoqueId);


}
