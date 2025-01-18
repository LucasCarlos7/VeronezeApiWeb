package com.api.veroneze.service;

import com.api.veroneze.data.entity.*;
import com.api.veroneze.data.entity.dto.VendaRequestDTO;
import com.api.veroneze.data.entity.enums.OperacaoEnum;
import com.api.veroneze.data.entity.enums.StatusProdutoVendaEnum;
import com.api.veroneze.data.entity.enums.StatusVendaEnum;
import com.api.veroneze.data.inteface.EstoqueRepository;
import com.api.veroneze.data.inteface.ItensVendaRepository;
import com.api.veroneze.data.inteface.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    @Lazy
    private ItensVendaService itensVendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstoqueService estoqueService;

    public VendaEntity salvarVenda(VendaRequestDTO vendaRequestDTO) {
        VendaEntity vendaEntity = new VendaEntity();
        ClienteEntity clienteEntity = clienteService.listarClienteId(vendaRequestDTO.clienteId());

        if (clienteEntity.getCpf() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCnpj());
        } else if (clienteEntity.getCnpj() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCpf());
        }

        vendaEntity.setClienteId(clienteEntity.getId());
        vendaEntity.setNomeCliente(clienteEntity.getNome());
        vendaEntity.setTotalOrcamentoInicial(0.0);
        vendaEntity.setDesconto(0.0);
        vendaEntity.setTotalOrcamentoFinal(0.0);
        vendaEntity.setLocalEstoqueId(vendaRequestDTO.localestoqueId());
        vendaEntity.setStatusVenda(StatusVendaEnum.ABERTO);
        vendaEntity.setDataVenda(new Date());

        return vendaRepository.save(vendaEntity);
    }

    public VendaEntity atualizarVenda(Integer vendaId, VendaRequestDTO vendaRequestDTO) {
        VendaEntity vendaEntity = new VendaEntity();
        ClienteEntity clienteEntity = clienteService.listarClienteId(vendaRequestDTO.clienteId());
        if (clienteEntity.getCpf() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCnpj());
        } else if (clienteEntity.getCnpj() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCpf());
        }

        vendaEntity.setClienteId(clienteEntity.getId());
        vendaEntity.setNomeCliente(clienteEntity.getNome());
        vendaEntity.setTotalOrcamentoInicial(0.0);
        vendaEntity.setDesconto(0.0);
        vendaEntity.setTotalOrcamentoFinal(0.0);
        vendaEntity.setLocalEstoqueId(vendaRequestDTO.localestoqueId());
        vendaEntity.setStatusVenda(StatusVendaEnum.ABERTO);
        vendaEntity.setDataVenda(new Date());

        if (vendaEntity.getStatusVenda().equals(StatusVendaEnum.FECHADO)) {
            atualizarEstoqueVenda(vendaEntity.getId());
        } else if (vendaEntity.getStatusVenda().equals(StatusVendaEnum.ABERTO)) {
            cancelarEstoqueVenda(vendaEntity.getId());
        }

        return vendaRepository.save(vendaEntity);
    }

    public void atualizarTotalOrcamento(Integer vendaId) {
        // Recupera todos os itens da venda pelo ID da venda
        List<ItensVendaEntity> itensVenda = itensVendaService.findByVendaId(vendaId);

        // Calcula o total do orçamento inicial somando os valores totais dos produtos com status ativo
        Double totalOrcamentoInicial = itensVenda.stream()
                .filter(item -> item.getStatusProdutoVenda() == StatusProdutoVendaEnum.ATIVO) // Filtra somente itens ativos
                .mapToDouble(ItensVendaEntity::getValorTotalProduto)
                .sum();

        // Busca a venda correpondente pelo ID
        VendaEntity venda = listarVendaId(vendaId);

        // Atualiza o total do orçamento inicial na entidade Venda
        venda.setTotalOrcamentoInicial(totalOrcamentoInicial);

        // Atualiza o total do orçamento final na entidade Venda
        venda.setTotalOrcamentoFinal(totalOrcamentoInicial - venda.getDesconto());

        // Salva a venda atualizada no banco de dados
        vendaRepository.save(venda);
    }

    public VendaEntity listarVendaId(Integer vendaId) {
        Optional<VendaEntity> obj = vendaRepository.findById(vendaId);
        return obj.orElseThrow(() -> new RuntimeException("Venda ID: " + vendaId + " não encontrada!"));
    }

    public VendaEntity atualizaStatusVenda(Integer vendaId) {
        VendaEntity venda = listarVendaId(vendaId);

        if (venda.getStatusVenda().equals(StatusVendaEnum.ABERTO)) {
            venda.setStatusVenda(StatusVendaEnum.FECHADO);
        } else {
            venda.setStatusVenda(StatusVendaEnum.ABERTO);
        }
        return venda;
    }

    public void atualizarEstoqueVenda(Integer vendaId) {
        // Consulta a venda pelo ID
        VendaEntity vendaConsultada = listarVendaId(vendaId);
        // Lista todos os itens incluso na venda
        List<ItensVendaEntity> itensVenda = itensVendaService.findByVendaId(vendaConsultada.getId());
        EstoqueEntity estoque;

        for (ItensVendaEntity itens : itensVenda) {
            // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
            estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(vendaConsultada.getLocalEstoqueId(), itens.getProdutoId());
            // Retorna o valor total de Entrada no estoque
            Double movimentoSaida = estoque.getMovimentoSaida();
            // Adiciona o novo valor somando com o valor já existente na tabela
            estoque.setMovimentoSaida(movimentoSaida + itens.getQuantidade());
            // Atualizada a data
            estoque.setDataAtualizacao(new Date());
            // Atualiza o saldo_total do estoque;
            estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
            // Salva a alteração no banco
            estoqueRepository.save(estoque);
        }
    }

    public void cancelarEstoqueVenda(Integer vendaId) {
        // Consulta a venda pelo ID
        VendaEntity vendaConsultada = listarVendaId(vendaId);
        // Lista todos os itens incluso na venda
        List<ItensVendaEntity> itensVenda = itensVendaService.findByVendaId(vendaConsultada.getId());
        EstoqueEntity estoque;

        for (ItensVendaEntity itens : itensVenda) {
            // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
            estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(vendaConsultada.getLocalEstoqueId(), itens.getProdutoId());
            // Retorna o valor total de Entrada no estoque
            Double movimentoSaida = estoque.getMovimentoSaida();
            // Adiciona o novo valor somando com o valor já existente na tabela
            estoque.setMovimentoSaida(movimentoSaida - itens.getQuantidade());
            // Atualizada a data
            estoque.setDataAtualizacao(new Date());
            // Atualiza o saldo_total do estoque;
            estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
            // Salva a alteração no banco
            estoqueRepository.save(estoque);
        }
    }
}
