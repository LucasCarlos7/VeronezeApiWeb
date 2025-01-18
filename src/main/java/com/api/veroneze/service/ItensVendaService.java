package com.api.veroneze.service;

import com.api.veroneze.data.entity.*;
import com.api.veroneze.data.entity.dto.ItensVendaRequestDTO;
import com.api.veroneze.data.entity.enums.OperacaoEnum;
import com.api.veroneze.data.entity.enums.StatusProdutoVendaEnum;
import com.api.veroneze.data.entity.enums.StatusVendaEnum;
import com.api.veroneze.data.inteface.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItensVendaService {

    @Autowired
    private ItensVendaRepository itensVendaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoCompostoService produtoCompostoService;

    @Autowired
    private VendaService vendaService;

    public ItensVendaEntity salvarItensVenda(ItensVendaRequestDTO itensVendaRequestDTO) {
        // Instancia um ItensVendaEntity;
        ItensVendaEntity novoItemVenda = new ItensVendaEntity();
        // Valida se existe a venda pelo param vendaId;
        VendaEntity vendaEntity = vendaService.listarVendaId(itensVendaRequestDTO.vendaId());
        // Valida se existe o produto pelo param produtoId;
        ProdutoEntity produto = produtoService.listarProdutoId(itensVendaRequestDTO.produtoId());
        // Lista todos os produtos compostos de receita para o produtoId informado
        List<ProdutoCompostoEntity> produtosCompostos = produtoCompostoService.findByProdutoId(produto.getId());

        // Valida se a venda está com o Status aberto
        if (vendaEntity.getStatusVenda().equals(StatusVendaEnum.FECHADO)) {
            throw new RuntimeException("Operação não permitida para Venda com status FECHADO.");
        }

        novoItemVenda.setVendaId(vendaEntity.getId());
        novoItemVenda.setItem(getNextItem());
        novoItemVenda.setProdutoId(produto.getId());
        novoItemVenda.setNomeProduto(produto.getNome());
        novoItemVenda.setValorUnitarioProduto(produto.getPreco());
        novoItemVenda.setQuantidade(itensVendaRequestDTO.quantidade());
        novoItemVenda.setValorTotalProduto(getValorTotalProduto(itensVendaRequestDTO));
        novoItemVenda.setDataAtualizacao(new Date());
        novoItemVenda.setStatusProdutoVenda(StatusProdutoVendaEnum.ATIVO);

        // Salva o novoItemVenda na Venda
        itensVendaRepository.save(novoItemVenda);

        // Percorre todos os ProdutosCompostos do ProdutoEntity e insere na venda com a Operação = SAIDA_COMPOSTO
        for (ProdutoCompostoEntity produtos : produtosCompostos) {
            ItensVendaEntity itensVendaComposto = new ItensVendaEntity();
            ProdutoEntity produtoComposto = produtoService.listarProdutoId(produtos.getProdutoCompostoId());

            itensVendaComposto.setProdutoId(produtoComposto.getId());
            itensVendaComposto.setItem(getNextItem());
            itensVendaComposto.setVendaId(vendaEntity.getId());
            itensVendaComposto.setNomeProduto(produtoComposto.getNome());
            itensVendaComposto.setValorUnitarioProduto(produtoComposto.getPreco());
            itensVendaComposto.setQuantidade(itensVendaRequestDTO.quantidade() * produtos.getProporcao());
            itensVendaComposto.setValorTotalProduto(produtoComposto.getPreco() * itensVendaComposto.getQuantidade());
            itensVendaComposto.setStatusProdutoVenda(StatusProdutoVendaEnum.ATIVO);

            itensVendaRepository.save(itensVendaComposto);
        }

        // Atualiza o valor total do orçamento ao inserir o novoItem
        vendaService.atualizarTotalOrcamento(vendaEntity.getId());

        // Retorna o item inserido
        return novoItemVenda;
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
        ItensVendaEntity itemVenda = listarItemVendaId(vendaId, itemId);

        // Busca a venda pelo ID vendaId;
        VendaEntity venda = vendaService.listarVendaId(vendaId);

        // Valida se a venda está com status ABERTO
        if (venda.getStatusVenda().equals(StatusVendaEnum.FECHADO)) {
            throw new RuntimeException("Operação não permitida para venda com Status FECHADO!");
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

    public ItensVendaEntity listarItemVendaId(Integer vendaId, Integer itemId) {
        // Lista o item da venda filtrado pelos parametros vendaId e itemId
        ItensVendaEntity itemVenda = itensVendaRepository.findByVendaIdAndItem(vendaId, itemId)
                .orElseThrow(() -> new RuntimeException("Item " + itemId + " da venda COD: " + vendaId + " não encontrado."));

        return itemVenda;
    }

    public List<ItensVendaEntity> findByVendaId(Integer vendaId) {
        List<ItensVendaEntity> itens = itensVendaRepository.findByVendaId(vendaId);

        if (itens == null) {
            throw new RuntimeException("Item(ns) não encontrado.");
        }

        return itens;
    }
}
