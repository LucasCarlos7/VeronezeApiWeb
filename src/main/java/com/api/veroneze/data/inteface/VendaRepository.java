package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.VendaEntity;
import com.api.veroneze.data.entity.views.VendaComProdutosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

    @Query("SELECT MAX(v.id) FROM VendaEntity v")
    Integer findLastId();

    @Query("SELECT new com.api.veroneze.data.entity.views.VendaComProdutosDTO" +
            "(id, clienteId, nomeCliente, cpf_cnpj, totalOrcamentoInicial, desconto, " +
            "totalOrcamentoFinal, localEstoqueId, statusVenda, dataVenda, dataAtualizacao) " +
            "FROM VendaEntity " +
            "WHERE id = :vendaId")
    VendaComProdutosDTO findVendaComProdutoById(
            @Param("vendaId") Integer vendaId);
}
