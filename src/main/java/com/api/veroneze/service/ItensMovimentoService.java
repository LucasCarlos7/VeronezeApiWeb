package com.api.veroneze.service;

import com.api.veroneze.data.entity.ItensMovimentoEntity;
import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ItensMovimentoRequestDTO;
import com.api.veroneze.data.entity.enums.StatusOperacaoEnum;
import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;
import com.api.veroneze.data.inteface.ItensMovimentoRepository;
import com.api.veroneze.data.inteface.MovimentoEstoqueRepository;
import com.api.veroneze.data.inteface.ProdutoCompostoRepository;
import com.api.veroneze.data.inteface.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensMovimentoService {

    @Autowired
    private ItensMovimentoRepository itensMovimentoRepository;

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @Autowired
    private ProdutoCompostoService produtoCompostoService;

    @Autowired
    private ProdutoService produtoService;

    public ItensMovimentoEntity salvarItensMovimento(ItensMovimentoRequestDTO itensMovimentoRequest) {
        // Instancia um ItensMovimentoEntity
        ItensMovimentoEntity itensMovimentoEntity = new ItensMovimentoEntity();
        // Consulta o ProdutoEntity filtrando pelo ID
        ProdutoEntity produto = produtoService.listarProdutoId(itensMovimentoRequest.produtoId());
        // Lista todos os produtos compostos de receita para o produtoId informado
        List<ProdutoCompostoEntity> produtosCompostos = produtoCompostoService.findByProdutoId(produto.getId());
        //Busca o MovimentoEstoque
        MovimentoEstoqueEntity movimentoEstoque = movimentoEstoqueService.listarMovimentoEstoqueId(itensMovimentoRequest.MovimentoEstoqueId());

        //Verifica se o status é diferente de 0
        if (movimentoEstoque.getStatusOperacao().equals(StatusOperacaoEnum.CONCLUIDO)) {
            throw new RuntimeException("Operação não permitida para Movimento com status CONCLUÍDO.");
        }

        itensMovimentoEntity.setProdutoId(itensMovimentoRequest.produtoId());
        itensMovimentoEntity.setMovimentoEstoqueId(itensMovimentoRequest.MovimentoEstoqueId());
        itensMovimentoEntity.setNomeProduto(produto.getNome());
        itensMovimentoEntity.setValorUnitarioProduto(produto.getPreco());
        itensMovimentoEntity.setQuantidade(itensMovimentoRequest.quantidade());
        itensMovimentoEntity.setValorTotalProduto(produto.getPreco() * itensMovimentoRequest.quantidade());
        itensMovimentoEntity.setOperacao('E');
        itensMovimentoEntity.setProdutoPrimarioId(itensMovimentoRequest.produtoId());

        itensMovimentoRepository.save(itensMovimentoEntity);

        if (!movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA)) {

            for (ProdutoCompostoEntity produtos : produtosCompostos) {
                ItensMovimentoEntity itensMovimentoComposto = new ItensMovimentoEntity();

                ProdutoEntity produtoComposto = produtoService.listarProdutoId(produtos.getProdutoCompostoId());

                itensMovimentoComposto.setProdutoId(produtoComposto.getId());
                itensMovimentoComposto.setMovimentoEstoqueId(itensMovimentoRequest.MovimentoEstoqueId());
                itensMovimentoComposto.setNomeProduto(produtoComposto.getNome());
                itensMovimentoComposto.setValorUnitarioProduto(produtoComposto.getPreco());
                itensMovimentoComposto.setQuantidade(itensMovimentoRequest.quantidade() * produtos.getProporcao());
                itensMovimentoComposto.setValorTotalProduto(produtoComposto.getPreco() * itensMovimentoRequest.quantidade());
                itensMovimentoComposto.setOperacao('S');
                itensMovimentoComposto.setProdutoPrimarioId(produtos.getProdutoId());

                itensMovimentoRepository.save(itensMovimentoComposto);
            }
        }
        return itensMovimentoEntity;
    }

    public ItensMovimentoEntity listarItensMovimentoById(Integer itemId) {
        Optional<ItensMovimentoEntity> obj = itensMovimentoRepository.findById(itemId);

        return obj.orElseThrow(() -> new RuntimeException("Item ID: " + itemId + " não encontrado."));
    }

    public void deletarItensMovimento(Integer itemId) {
        ItensMovimentoEntity itemDeletado = listarItensMovimentoById(itemId);

        itensMovimentoRepository.deleteByProdutoId(itemDeletado.getProdutoPrimarioId());
    }

    public List<ItensMovimentoEntity> findByMovimentoEstoqueId(Integer movimentoEstoqueId) {
        List<ItensMovimentoEntity> objs = itensMovimentoRepository.findByMovimentoEstoqueId(movimentoEstoqueId);

        if (objs.equals(null)) {
            throw new RuntimeException("Item(ns) não encontrado.");
        }

        return objs;
    }

    public Double findValorTotalProdutos(Integer movimentoEstoqueId) {
        Double valorTotal = itensMovimentoRepository.findValorTotalProdutos(movimentoEstoqueId);

        if (valorTotal == null) {
            valorTotal = 0.0;
        }
        return valorTotal;
    }
}
