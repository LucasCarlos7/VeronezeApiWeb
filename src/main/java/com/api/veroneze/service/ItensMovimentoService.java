package com.api.veroneze.service;

import com.api.veroneze.data.entity.ItensMovimentoEntity;
import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ItensMovimentoRequestDTO;
import com.api.veroneze.data.entity.enums.OperacaoEnum;
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

    public ItensMovimentoEntity salvarItensMovimento(ItensMovimentoRequestDTO itemMovimentoRequest) {
        // Instancia um ItensMovimentoEntity
        ItensMovimentoEntity novoItemMovimento = new ItensMovimentoEntity();
        // Consulta o ProdutoEntity filtrando pelo ID
        ProdutoEntity produto = produtoService.listarProdutoId(itemMovimentoRequest.produtoId());
        // Lista todos os produtos compostos de receita para o produtoId informado
        List<ProdutoCompostoEntity> produtosCompostos = produtoCompostoService.findByProdutoId(produto.getId());
        //Busca o MovimentoEstoque
        MovimentoEstoqueEntity movimentoEstoque = movimentoEstoqueService.listarMovimentoEstoqueId(itemMovimentoRequest.MovimentoEstoqueId());

        //Verifica se o status é igual a CONCLUIDO
        if (movimentoEstoque.getStatusOperacao().equals(StatusOperacaoEnum.CONCLUIDO)) {
            throw new RuntimeException("Operação não permitida para Movimento com status CONCLUÍDO.");
        }

        novoItemMovimento.setProdutoId(itemMovimentoRequest.produtoId());
        novoItemMovimento.setMovimentoEstoqueId(itemMovimentoRequest.MovimentoEstoqueId());
        novoItemMovimento.setNomeProduto(produto.getNome());
        novoItemMovimento.setValorUnitarioProduto(produto.getPreco());
        novoItemMovimento.setQuantidade(itemMovimentoRequest.quantidade());
        novoItemMovimento.setValorTotalProduto(produto.getPreco() * itemMovimentoRequest.quantidade());
        novoItemMovimento.setOperacao(OperacaoEnum.ENTRADA);
        novoItemMovimento.setProdutoPrimarioId(itemMovimentoRequest.produtoId());

        // Salva o novo item no movimento
        itensMovimentoRepository.save(novoItemMovimento);

        // Valida se o tipoOperação do movimento é diferente de ENTRADA
        if (!movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA)) {

            // Percorre todos os ProdutosCompostos do ProdutoEntity e insere no movimento com a Operação = SAIDA
            for (ProdutoCompostoEntity produtos : produtosCompostos) {
                ItensMovimentoEntity itensMovimentoComposto = new ItensMovimentoEntity();

                ProdutoEntity produtoComposto = produtoService.listarProdutoId(produtos.getProdutoCompostoId());

                itensMovimentoComposto.setProdutoId(produtoComposto.getId());
                itensMovimentoComposto.setMovimentoEstoqueId(movimentoEstoque.getId());
                itensMovimentoComposto.setNomeProduto(produtoComposto.getNome());
                itensMovimentoComposto.setValorUnitarioProduto(produtoComposto.getPreco());
                itensMovimentoComposto.setQuantidade(itemMovimentoRequest.quantidade() * produtos.getProporcao());
                itensMovimentoComposto.setValorTotalProduto(produtoComposto.getPreco() * itensMovimentoComposto.getQuantidade());
                itensMovimentoComposto.setOperacao(OperacaoEnum.SAIDA);
                itensMovimentoComposto.setProdutoPrimarioId(produtos.getProdutoId());

                itensMovimentoRepository.save(itensMovimentoComposto);
            }
        }
        return novoItemMovimento;
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
        List<ItensMovimentoEntity> itens = itensMovimentoRepository.findByMovimentoEstoqueId(movimentoEstoqueId);

        if (itens == null) {
            throw new RuntimeException("Item(ns) não encontrado.");
        }

        return itens;
    }

    public Double findValorTotalProdutos(Integer movimentoEstoqueId) {
        Double valorTotal = itensMovimentoRepository.findValorTotalProdutos(movimentoEstoqueId);

        if (valorTotal == null) {
            valorTotal = 0.0;
        }
        return valorTotal;
    }
}
