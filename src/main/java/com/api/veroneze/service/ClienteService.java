package com.api.veroneze.service;

import com.api.veroneze.data.entity.ClienteEntity;
import com.api.veroneze.data.entity.dto.ClienteRequestDTO;
import com.api.veroneze.data.inteface.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity salvarCliente(ClienteRequestDTO clienteRequestDTO) {

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome(clienteRequestDTO.nome().toUpperCase());
        clienteEntity.setTipoPessoa(clienteRequestDTO.tipoPessoa());
        clienteEntity.setTelefone(clienteRequestDTO.telefone());
        clienteEntity.setEmail(clienteRequestDTO.email());
        clienteEntity.setCep(clienteRequestDTO.cep());
        clienteEntity.setEndereco(clienteRequestDTO.endereco());
        clienteEntity.setBairro(clienteRequestDTO.bairro());
        clienteEntity.setNumeroEndereco(clienteRequestDTO.numeroEnd());
        clienteEntity.setDataCriacao(new Date());
        clienteEntity.setCidade(clienteRequestDTO.cidade());
        clienteEntity.setUF(clienteRequestDTO.UF());

        if (clienteRequestDTO.tipoPessoa().getCode() == 1) { //Pessoa Fisica
            clienteEntity.setCpf(clienteRequestDTO.cpf());
            clienteEntity.setCnpj(null);
        } else if (clienteRequestDTO.tipoPessoa().getCode() == 2) { //Pessoa Juridica
            clienteEntity.setCnpj(clienteRequestDTO.cnpj());
            clienteEntity.setCpf(null);
        }

        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity atualizarCliente(Integer clienteId, ClienteRequestDTO clienteRequestDTO) {

        ClienteEntity clienteAtualizado = listarClienteId(clienteId);

        clienteAtualizado.setNome(clienteRequestDTO.nome().toUpperCase());
        clienteAtualizado.setTipoPessoa(clienteRequestDTO.tipoPessoa());
        clienteAtualizado.setTelefone(clienteRequestDTO.telefone());
        clienteAtualizado.setEmail(clienteRequestDTO.email());
        clienteAtualizado.setCep(clienteRequestDTO.cep());
        clienteAtualizado.setEndereco(clienteRequestDTO.endereco());
        clienteAtualizado.setBairro(clienteRequestDTO.bairro());
        clienteAtualizado.setNumeroEndereco(clienteRequestDTO.numeroEnd());
        clienteAtualizado.setDataAtualizacao(new Date());
        clienteAtualizado.setCidade(clienteRequestDTO.cidade());
        clienteAtualizado.setUF(clienteRequestDTO.UF());

        if (clienteRequestDTO.tipoPessoa().getCode() == 1) { //Pessoa Fisica
            clienteAtualizado.setCpf(clienteRequestDTO.cpf());
            clienteAtualizado.setCnpj(null);
        } else if (clienteRequestDTO.tipoPessoa().getCode() == 2) { //Pessoa Juridica
            clienteAtualizado.setCnpj(clienteRequestDTO.cnpj());
            clienteAtualizado.setCpf(null);
        }

        return clienteRepository.save(clienteAtualizado);
    }

    public ClienteEntity listarClienteId(Integer clienteId) {
        Optional<ClienteEntity> obj = clienteRepository.findById(clienteId);

        return obj.orElseThrow(() -> new RuntimeException("Cliente ID: " + clienteId + " não encontrado"));
    }

    public List<ClienteEntity> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public void deletarCliente(Integer clienteId) {
        ClienteEntity clienteDeletado = listarClienteId(clienteId);

        clienteRepository.deleteById(clienteDeletado.getId());
    }

    public Integer getNextId() {
        Integer lastId = clienteRepository.findLastId();

        return (lastId != null) ? lastId + 1 : 1;
    }
}
