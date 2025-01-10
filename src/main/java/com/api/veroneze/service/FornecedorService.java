package com.api.veroneze.service;

import com.api.veroneze.data.entity.FornecedorEntity;
import com.api.veroneze.data.entity.dto.FornecedorRequestDTO;
import com.api.veroneze.data.inteface.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public FornecedorEntity salvarFornecedor(FornecedorRequestDTO fornecedorRequest) {

        FornecedorEntity fornecedorEntity = new FornecedorEntity();

        fornecedorEntity.setNome(fornecedorRequest.nome());
        fornecedorEntity.setTipoPessoa(fornecedorRequest.tipoPessoa());
        fornecedorEntity.setTelefone(fornecedorRequest.telefone());
        fornecedorEntity.setEmail(fornecedorRequest.email());
        fornecedorEntity.setCep(fornecedorRequest.cep());
        fornecedorEntity.setEndereco(fornecedorRequest.endereco());
        fornecedorEntity.setBairro(fornecedorRequest.bairro());
        fornecedorEntity.setNumeroEndereco(fornecedorRequest.numeroEnd());
        fornecedorEntity.setDataCriacao(new Date());
        fornecedorEntity.setInscricaoEstadual(fornecedorRequest.inscricaoEstadual());

        if (fornecedorRequest.tipoPessoa().getCode() == 1) { //Pessoa Fisica
            fornecedorEntity.setCpf(fornecedorRequest.cpf());
            fornecedorEntity.setCnpj(null);
        } else if (fornecedorRequest.tipoPessoa().getCode() == 2) { //Pessoa Juridica
            fornecedorEntity.setCnpj(fornecedorRequest.cnpj());
            fornecedorEntity.setCpf(null);
        }

        return fornecedorRepository.save(fornecedorEntity);
    }

    public FornecedorEntity atualizarFornecedor(Integer fornecedorId, FornecedorRequestDTO fornecedorRequest) {

        FornecedorEntity fornecedorAtualizado = listarFornecedorId(fornecedorId);

        fornecedorAtualizado.setNome(fornecedorRequest.nome());
        fornecedorAtualizado.setTipoPessoa(fornecedorRequest.tipoPessoa());
        fornecedorAtualizado.setTelefone(fornecedorRequest.telefone());
        fornecedorAtualizado.setEmail(fornecedorRequest.email());
        fornecedorAtualizado.setCep(fornecedorRequest.cep());
        fornecedorAtualizado.setEndereco(fornecedorRequest.endereco());
        fornecedorAtualizado.setBairro(fornecedorRequest.bairro());
        fornecedorAtualizado.setNumeroEndereco(fornecedorRequest.numeroEnd());
        fornecedorAtualizado.setDataAtualizacao(new Date());
        fornecedorAtualizado.setInscricaoEstadual(fornecedorRequest.inscricaoEstadual());

        if (fornecedorRequest.tipoPessoa().getCode() == 1) { //Pessoa Fisica
            fornecedorAtualizado.setCpf(fornecedorRequest.cpf());
            fornecedorAtualizado.setCnpj(null);
        } else if (fornecedorRequest.tipoPessoa().getCode() == 2) { //Pessoa Juridica
            fornecedorAtualizado.setCnpj(fornecedorRequest.cnpj());
            fornecedorAtualizado.setCpf(null);
        }

        return fornecedorRepository.save(fornecedorAtualizado);
    }

    public FornecedorEntity listarFornecedorId(Integer fornecedorId) {
        Optional<FornecedorEntity> obj = fornecedorRepository.findById(fornecedorId);

        return obj.orElseThrow(() -> new RuntimeException("Fornecedor ID: " + fornecedorId + " não encontrado"));
    }

    public List<FornecedorEntity> listarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public void deletarFornecedor(Integer fornecedorId) {
        FornecedorEntity fornecedorDeletado = listarFornecedorId(fornecedorId);

        fornecedorRepository.deleteById(fornecedorDeletado.getId());
    }

    public Integer getNextId() {
        Integer lastId = fornecedorRepository.findLastId();
        return (lastId != null) ? lastId + 1 : 1;
    }
}
