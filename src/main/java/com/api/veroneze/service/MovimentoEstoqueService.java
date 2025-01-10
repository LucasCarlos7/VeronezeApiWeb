package com.api.veroneze.service;

import com.api.veroneze.data.entity.EstoqueEntity;
import com.api.veroneze.data.entity.ItensMovimentoEntity;
import com.api.veroneze.data.entity.MovimentoEstoqueEntity;
import com.api.veroneze.data.entity.dto.MovimentoEstoqueRequestDTO;
import com.api.veroneze.data.entity.enums.StatusOperacaoEnum;
import com.api.veroneze.data.entity.enums.TipoOperacaoEnum;
import com.api.veroneze.data.inteface.EstoqueRepository;
import com.api.veroneze.data.inteface.MovimentoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimentoEstoqueService {

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    @Lazy
    private ItensMovimentoService itensMovimentoService;

    @Autowired
    private EstoqueService estoqueService;

    public MovimentoEstoqueEntity salvarMovimentoEstoque(MovimentoEstoqueRequestDTO movimentoEstoque) {

        if (movimentoEstoque.localEstoqueId() == movimentoEstoque.localEstoqueSaidaId()) {
            new RuntimeException("Operação não permitida! Locais de Estoque iguais.");
        }

        MovimentoEstoqueEntity movimentoEstoqueEntity = new MovimentoEstoqueEntity();

        movimentoEstoqueEntity.setLocalEstoqueId(movimentoEstoque.localEstoqueId());
        movimentoEstoqueEntity.setLocalEstoqueSaidaId(movimentoEstoque.localEstoqueSaidaId());
        movimentoEstoqueEntity.setFornecedorId(movimentoEstoque.fornecedorId());
        movimentoEstoqueEntity.setFuncionarioId(movimentoEstoque.funcionarioId());
        movimentoEstoqueEntity.setTipoOperacao(movimentoEstoque.tipoOperacao());
        movimentoEstoqueEntity.setStatusOperacao(StatusOperacaoEnum.INICIADO);
        movimentoEstoqueEntity.setValorOperacao(movimentoEstoque.valorOperacao());
        movimentoEstoqueEntity.setDataOperacao(new Date());

        return movimentoEstoqueRepository.save(movimentoEstoqueEntity);
    }

    public MovimentoEstoqueEntity atualizarMovimentoEstoque(Integer movimentoEstoqueId, MovimentoEstoqueRequestDTO movimentoEstoque) {

        // Lista o MovimentoEstoque consultado pelo id de movimentoEstoqueId
        MovimentoEstoqueEntity movimentoEstoqueAtualizado = listarMovimentoEstoqueId(movimentoEstoqueId);
        // Consulta os valores totais dos produtos incluidos na MovimentoEstoque
        Double valorTotalProdutos = itensMovimentoService.findValorTotalProdutos(movimentoEstoqueAtualizado.getId());

        movimentoEstoqueAtualizado.setLocalEstoqueId(movimentoEstoque.localEstoqueId());
        movimentoEstoqueAtualizado.setLocalEstoqueSaidaId(movimentoEstoque.localEstoqueSaidaId());
        movimentoEstoqueAtualizado.setFornecedorId(movimentoEstoque.fornecedorId());
        movimentoEstoqueAtualizado.setFuncionarioId(movimentoEstoque.funcionarioId());
        movimentoEstoqueAtualizado.setTipoOperacao(movimentoEstoque.tipoOperacao());
        movimentoEstoqueAtualizado.setStatusOperacao(movimentoEstoque.statusOperacao());
        movimentoEstoqueAtualizado.setValorOperacao(movimentoEstoque.valorOperacao());
        movimentoEstoqueAtualizado.setDataOperacao(new Date());

        // Validando se os valores totais dos produtos são iguais ao valor da Operação;
        if (validarValorTotal(movimentoEstoqueId, movimentoEstoque)) {
            throw new RuntimeException("Valor dos Produtos R$ " + movimentoEstoqueAtualizado.getValorOperacao() + " é diferente do valor total da Operação R$ " + valorTotalProdutos);
        }


        if (movimentoEstoqueAtualizado.getStatusOperacao().equals(StatusOperacaoEnum.CONCLUIDO)) {
            atualizarTotalEstoqueMovimento(movimentoEstoqueAtualizado.getId());
        } else if (movimentoEstoqueAtualizado.getStatusOperacao().equals(StatusOperacaoEnum.INICIADO)) {
            cancelarTotalEstoqueMovimento(movimentoEstoqueAtualizado.getId());
        }

        return movimentoEstoqueRepository.save(movimentoEstoqueAtualizado);
    }

    public MovimentoEstoqueEntity listarMovimentoEstoqueId(Integer movimentoEstoqueId) {
        Optional<MovimentoEstoqueEntity> obj = movimentoEstoqueRepository.findById(movimentoEstoqueId);

        return obj.orElseThrow(() -> new RuntimeException("Movimentação ID: " + movimentoEstoqueId + " não encontrada!"));
    }

    public List<MovimentoEstoqueEntity> listarTodosMovimentoEstoque() {
        return movimentoEstoqueRepository.findAll();
    }

    public Integer getNextId() {
        Integer lastId = movimentoEstoqueRepository.findLastId();

        return (lastId != null) ? lastId + 1 : 1;
    }

    public boolean validarValorTotal(Integer movimentoEstoqueId, MovimentoEstoqueRequestDTO movimentoEstoque) {

        // Lista o MovimentoEstoque consultado pelo id de movimentoEstoqueId
        MovimentoEstoqueEntity movimentoEstoqueListado = listarMovimentoEstoqueId(movimentoEstoqueId);

        // Recebe o valor inserido no MovimentoEstoque
        Double valorTotalOperacao = movimentoEstoque.valorOperacao();

        // Consulta os valores totais dos produtos incluidos na MovimentoEstoque
        Double valorTotalProdutos = itensMovimentoService.findValorTotalProdutos(movimentoEstoqueListado.getId());

        boolean valorValidado;

        // Retorna True ou False a depender dos valores retornados na consulta;
        // True: se o valores forem diferentes; False: se os valores forem iguais;
        // Se atentar ao operador de negação ! na função
        return valorValidado = !Objects.equals(valorTotalProdutos, valorTotalOperacao);
    }

    public void atualizarTotalEstoqueMovimento(Integer movimentoEstoqueId) {
        // Consulta o MovimentoEstoque pelo ID;
        MovimentoEstoqueEntity movimentoEstoque = listarMovimentoEstoqueId(movimentoEstoqueId);
        // Lista todos os itens incluso no movimentoEstoque
        List<ItensMovimentoEntity> itensMovimento = itensMovimentoService.findByMovimentoEstoqueId(movimentoEstoque.getId());
        // Instancia um EstoqueEntity
        EstoqueEntity estoque;

        if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
                estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                // Retorna o valor total de Entrada no estoque
                Double movimentoEntrada = estoque.getMovimentoEntrada();
                // Adiciona o novo valor somando com o valor já existente na tabela
                estoque.setMovimentoEntrada(movimentoEntrada + itens.getQuantidade());
                // Atualizada a data
                estoque.setDataAtualizacao(new Date());
                // Atualiza o saldo_total do estoque;
                estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                // Salva a alteração no banco
                estoqueRepository.save(estoque);
            }
        } else if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Consulta o Estoque em que será inserido o valor de Saida, filtrando pelo LocalEstoqueId + ProdutoId
                estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                // Retorna o valor total de Saida no estoque
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
        } else if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.TRANSFERENCIA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Realiza a atualização de saldo no LocalEstoque Entrada
                if (itens.getOperacao() == 'E') {
                    // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
                    estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                    // Retorna o valor total de Entrada no estoque
                    Double movimentoEntrada = estoque.getMovimentoEntrada();
                    // Atualizada a data
                    estoque.setDataAtualizacao(new Date());
                    // Adiciona o novo valor somando com o valor já existente na tabela
                    estoque.setMovimentoEntrada(movimentoEntrada + itens.getQuantidade());
                    // Atualiza o saldo_total do estoque;
                    estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                    // Salva a alteração no banco
                    estoqueRepository.save(estoque);
                }
                // Realiza a atualização de saldo no LocalEstoque Entrada
                else {
                    // Consulta o Estoque em que será inserido o valor de Saida, filtrando pelo LocalEstoqueId + ProdutoId
                    estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueSaidaId(), itens.getProdutoId());
                    // Retorna o valor total de Saida no estoque
                    Double movimentoSaida = estoque.getMovimentoSaida();
                    // Adiciona o novo valor somando com o valor já existente na tabela
                    estoque.setMovimentoSaida(movimentoSaida + itens.getQuantidade());
                    // Atualiza a data
                    estoque.setDataAtualizacao(new Date());
                    // Atualiza o saldo_total do estoque;
                    estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                    // Salva a alteração no banco
                    estoqueRepository.save(estoque);
                }
            }
        }
    }

    public void cancelarTotalEstoqueMovimento(Integer movimentoEstoqueId) {
        // Consulta o MovimentoEstoque pelo ID;
        MovimentoEstoqueEntity movimentoEstoque = listarMovimentoEstoqueId(movimentoEstoqueId);
        // Lista todos os itens incluso no movimentoEstoque
        List<ItensMovimentoEntity> itensMovimento = itensMovimentoService.findByMovimentoEstoqueId(movimentoEstoque.getId());
        // Instancia um EstoqueEntity
        EstoqueEntity estoque;

        if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
                estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                // Retorna o valor total de Entrada no estoque
                Double movimentoEntrada = estoque.getMovimentoEntrada();
                // Adiciona o novo valor somando com o valor já existente na tabela
                estoque.setMovimentoEntrada(movimentoEntrada - itens.getQuantidade());
                // Atualizada a data
                estoque.setDataAtualizacao(new Date());
                // Atualiza o saldo_total do estoque;
                estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                // Salva a alteração no banco
                estoqueRepository.save(estoque);
            }
        } else if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Consulta o Estoque em que será inserido o valor de Saida, filtrando pelo LocalEstoqueId + ProdutoId
                estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                // Retorna o valor total de Saida no estoque
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
        } else if (movimentoEstoque.getTipoOperacao().equals(TipoOperacaoEnum.TRANSFERENCIA)) {
            for (ItensMovimentoEntity itens : itensMovimento) {
                // Realiza a atualização de saldo no LocalEstoque Entrada
                if (itens.getOperacao() == 'E') {
                    // Consulta o Estoque em que será inserido o valor de Entrada, filtrando pelo LocalEstoqueId + ProdutoId
                    estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueId(), itens.getProdutoId());
                    // Retorna o valor total de Entrada no estoque
                    Double movimentoEntrada = estoque.getMovimentoEntrada();
                    // Atualizada a data
                    estoque.setDataAtualizacao(new Date());
                    // Adiciona o novo valor somando com o valor já existente na tabela
                    estoque.setMovimentoEntrada(movimentoEntrada - itens.getQuantidade());
                    // Atualiza o saldo_total do estoque;
                    estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                    // Salva a alteração no banco
                    estoqueRepository.save(estoque);
                }
                // Realiza a atualização de saldo no LocalEstoque Entrada
                else {
                    // Consulta o Estoque em que será inserido o valor de Saida, filtrando pelo LocalEstoqueId + ProdutoId
                    estoque = estoqueService.findBylocalEstoqueIdAndProdutoId(movimentoEstoque.getLocalEstoqueSaidaId(), itens.getProdutoId());
                    // Retorna o valor total de Saida no estoque
                    Double movimentoSaida = estoque.getMovimentoSaida();
                    // Adiciona o novo valor somando com o valor já existente na tabela
                    estoque.setMovimentoSaida(movimentoSaida - itens.getQuantidade());
                    // Atualiza a data
                    estoque.setDataAtualizacao(new Date());
                    // Atualiza o saldo_total do estoque;
                    estoque.setSaldoTotal(estoque.getMovimentoEntrada() - estoque.getMovimentoSaida());
                    // Salva a alteração no banco
                    estoqueRepository.save(estoque);
                }
            }
        }
    }
}
