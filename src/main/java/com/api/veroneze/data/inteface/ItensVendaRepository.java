package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ItensVendaEntity;
import com.api.veroneze.data.entity.ItensVendaIdEntity;
import com.api.veroneze.data.entity.views.ItensMovimentoEstoqueDTO;
import com.api.veroneze.data.entity.views.ItensVendaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItensVendaRepository extends JpaRepository<ItensVendaEntity, ItensVendaIdEntity> {

    List<ItensVendaEntity> findByVendaId(Integer vendaId);

    Optional<ItensVendaEntity> findByVendaIdAndItem(Integer vendaId, Integer item);

    @Query("SELECT MAX(i.item) FROM ItensVendaEntity i")
    Integer findLastItem();

    @Query("SELECT new com.api.veroneze.data.entity.views.ItensVendaDTO " +
            "(vendaId, item, produtoId, nomeProduto, quantidade, " +
            "valorUnitarioProduto, valorTotalProduto, statusProdutoVenda) " +
            "FROM ItensVendaEntity " +
            "WHERE vendaId = :vendaId")
    List<ItensVendaDTO> findItensVendaByVendaId(@Param("vendaId") Integer vendaId);
}
