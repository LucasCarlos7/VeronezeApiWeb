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

    @Autowired
    private LocalEstoqueService localEstoqueService;

    public VendaEntity salvarVenda(VendaRequestDTO vendaRequestDTO) {
        VendaEntity vendaEntity = new VendaEntity();
        ClienteEntity clienteEntity = clienteService.listarClienteId(vendaRequestDTO.clienteId());
        LocalEstoqueEntity localEstoque = localEstoqueService.listarLocalEstoqueId(vendaRequestDTO.localestoqueId());

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
        vendaEntity.setLocalEstoqueId(localEstoque.getId());
        vendaEntity.setStatusVenda(StatusVendaEnum.ABERTO);
        vendaEntity.setDataVenda(new Date());

        return vendaRepository.save(vendaEntity);
    }

    public VendaEntity atualizarVenda(Integer vendaId, VendaRequestDTO vendaRequestDTO) {

        VendaEntity vendaEntity = listarVendaId(vendaId);
        ClienteEntity clienteEntity = clienteService.listarClienteId(vendaRequestDTO.clienteId());
        LocalEstoqueEntity localEstoque = localEstoqueService.listarLocalEstoqueId(vendaRequestDTO.localestoqueId());

        if (clienteEntity.getCpf() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCnpj());
        } else if (clienteEntity.getCnpj() == null) {
            vendaEntity.setCpf_cnpj(clienteEntity.getCpf());
        }

        vendaEntity.setClienteId(clienteEntity.getId());
        vendaEntity.setNomeCliente(clienteEntity.getNome());
        vendaEntity.setLocalEstoqueId(localEstoque.getId());
        vendaEntity.setStatusVenda(vendaEntity.getStatusVenda());
        vendaEntity.setDataAtualizacao(new Date());

        return vendaRepository.save(vendaEntity);
    }

    public VendaEntity finalizarOuCancelarVenda(Integer vendaId, VendaRequestDTO vendaRequestDTO) {
        VendaEntity venda = listarVendaId(vendaId);

        venda.setDataAtualizacao(new Date());

        if (venda.getStatusVenda().equals(StatusVendaEnum.ABERTO)) {
            venda.setDesconto(vendaRequestDTO.desconto());
            vendaRepository.save(venda);
            atualizarTotalOrcamento(venda.getId());
            atualizaStatusVenda(venda.getId());
            return venda;
        }

        vendaRepository.save(venda);
        atualizaStatusVenda(venda.getId());

        return venda;
    }

    public void atualizaStatusVenda(Integer vendaId) {
        VendaEntity venda = listarVendaId(vendaId);

        if (venda.getStatusVenda().equals(StatusVendaEnum.ABERTO)) {
            venda.setStatusVenda(StatusVendaEnum.FECHADO);
            atualizarEstoqueVenda(venda.getId());
            vendaRepository.save(venda);
        } else {
            venda.setStatusVenda(StatusVendaEnum.ABERTO);
            cancelarEstoqueVenda(venda.getId());
            vendaRepository.save(venda);
        }
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

    public List<VendaEntity> listarTodasVendas() {
        return vendaRepository.findAll();
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

    public Integer getNextId() {
        Integer lastId = vendaRepository.findLastId();
        return (lastId != null) ? lastId + 1 : 1;
    }
}
