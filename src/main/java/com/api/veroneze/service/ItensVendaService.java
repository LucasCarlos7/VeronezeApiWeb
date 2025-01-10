package com.api.veroneze.service;

import com.api.veroneze.data.entity.FuncionarioEntity;
import com.api.veroneze.data.entity.ItensVendaEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.VendaEntity;
import com.api.veroneze.data.entity.dto.ItensVendaRequestDTO;
import com.api.veroneze.data.entity.enums.StatusProdutoVendaEnum;
import com.api.veroneze.data.entity.enums.StatusVendaEnum;
import com.api.veroneze.data.inteface.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItensVendaService {

    @Autowired
    private ItensVendaRepository itensVendaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private VendaService vendaService;

    public ItensVendaEntity salvarItensVenda(ItensVendaRequestDTO itensVendaRequestDTO) {

        ItensVendaEntity itensVendaEntity = new ItensVendaEntity();
        ProdutoEntity produto = produtoService.listarProdutoId(itensVendaRequestDTO.produtoId());
        FuncionarioEntity funcionario = funcionarioService.listarFuncionarioId(1);

        itensVendaEntity.setVendaId(itensVendaRequestDTO.vendaId());
        itensVendaEntity.setItem(getNextItem());
        itensVendaEntity.setProdutoId(produto.getId());
        itensVendaEntity.setNomeProduto(produto.getNome());
        itensVendaEntity.setValorUnitarioProduto(produto.getPreco());
        itensVendaEntity.setQuantidade(itensVendaRequestDTO.quantidade());
        itensVendaEntity.setValorTotalProduto(getValorTotalProduto(itensVendaRequestDTO));
        itensVendaEntity.setFuncionarioId(funcionario.getId());
        itensVendaEntity.setDataAtualizacao(new Date());
        itensVendaEntity.setStatusProdutoVenda(StatusProdutoVendaEnum.ATIVO);

        return itensVendaRepository.save(itensVendaEntity);
    }

    public Integer getNextItem() {
        Integer lastItem = itensVendaRepository.findLastItem();

        return (lastItem != null) ? lastItem + 1 : 1;
    }

    public Double getValorTotalProduto(ItensVendaRequestDTO itensVendaRequestDTO) {
        ProdutoEntity produto = produtoService.listarProdutoId(itensVendaRequestDTO.produtoId());

        return produto.getPreco() * itensVendaRequestDTO.quantidade();
    }

    public void atualizarStatusItemVenda(Integer vendaId, Integer itemId) {
        // Busca o item da venda pelo ID da venda e do item
        ItensVendaEntity itemVenda = listarItensVendaId(vendaId, itemId);

        // Busca a venda pelo ID vendaId;
        VendaEntity venda = vendaService.listarVendaId(vendaId);

        // Valida se a venda está com status diferente de ABERTO
        if (venda.getStatusVenda().equals(StatusVendaEnum.FECHADO)) {
            throw new RuntimeException("Operação não permitida para venda diferente de ABERTO!");
        }

        // Valida se o statusProduto é igual a CANCELADO
        if (itemVenda.getStatusProdutoVenda() == StatusProdutoVendaEnum.CANCELADO) {
            throw new RuntimeException("Operação não permitida!");
        }

        // Atualiza o status do item
        itemVenda.setStatusProdutoVenda(StatusProdutoVendaEnum.CANCELADO);
        itensVendaRepository.save(itemVenda);

        // Recalcula o total do orçamento da venda
        vendaService.atualizarTotalOrcamento(vendaId);
    }

    public ItensVendaEntity listarItensVendaId(Integer vendaId, Integer itemId) {
        // Lista o item da venda filtrado pelos parametros vendaId e itemId
        ItensVendaEntity itemVenda = itensVendaRepository.findByVendaIdAndItem(vendaId, itemId)
                .orElseThrow(() -> new RuntimeException("Item " + itemId + " da venda COD: " + vendaId + " não encontrado."));

        return itemVenda;
    }
}
