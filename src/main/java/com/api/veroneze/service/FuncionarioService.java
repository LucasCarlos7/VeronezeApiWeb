package com.api.veroneze.service;

import com.api.veroneze.data.entity.FuncionarioEntity;
import com.api.veroneze.data.entity.dto.FuncionarioRequestDTO;
import com.api.veroneze.data.inteface.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public FuncionarioEntity salvarFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {

//        if (funcionarioRepository.findByLogin(funcionarioRequestDTO.login()) != null) {
//            throw new RuntimeException("Usuário já cadastrado! Operação cancelada.");
//        }

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

        funcionarioEntity.setNome(funcionarioRequestDTO.nome());
        funcionarioEntity.setLogin(funcionarioRequestDTO.login());
//        funcionarioEntity.setSenha(passwordEncoder.encode(funcionarioRequestDTO.senha()));
        funcionarioEntity.setSenha(funcionarioRequestDTO.senha());
        funcionarioEntity.setCargo(funcionarioRequestDTO.cargo());
        funcionarioEntity.setCpf(funcionarioRequestDTO.cpf());
        funcionarioEntity.setTelefone(funcionarioRequestDTO.telefone());
        funcionarioEntity.setEmail(funcionarioRequestDTO.email());
        funcionarioEntity.setCep(funcionarioRequestDTO.cep());
        funcionarioEntity.setEndereco(funcionarioRequestDTO.endereco());
        funcionarioEntity.setBairro(funcionarioRequestDTO.bairro());
        funcionarioEntity.setNumeroEndereco(funcionarioRequestDTO.numeroEndereco());
        funcionarioEntity.setDataCriacao(new Date());
        funcionarioEntity.setCidade(funcionarioRequestDTO.cidade());
        funcionarioEntity.setUF(funcionarioRequestDTO.UF());

        return funcionarioRepository.save(funcionarioEntity);
    }

    public FuncionarioEntity atualizarFuncionario(Integer funcionarioId, FuncionarioRequestDTO funcionarioRequestDTO) {

        FuncionarioEntity funcionarioAtualizado = listarFuncionarioId(funcionarioId);

        funcionarioAtualizado.setNome(funcionarioRequestDTO.nome());
        funcionarioAtualizado.setLogin(funcionarioRequestDTO.login());
//        funcionarioAtualizado.setSenha(passwordEncoder.encode(funcionarioRequestDTO.senha()));
        funcionarioAtualizado.setSenha(funcionarioRequestDTO.senha());
        funcionarioAtualizado.setCargo(funcionarioRequestDTO.cargo());
        funcionarioAtualizado.setCpf(funcionarioRequestDTO.cpf());
        funcionarioAtualizado.setTelefone(funcionarioRequestDTO.telefone());
        funcionarioAtualizado.setEmail(funcionarioRequestDTO.email());
        funcionarioAtualizado.setCep(funcionarioRequestDTO.cep());
        funcionarioAtualizado.setEndereco(funcionarioRequestDTO.endereco());
        funcionarioAtualizado.setBairro(funcionarioRequestDTO.bairro());
        funcionarioAtualizado.setNumeroEndereco(funcionarioRequestDTO.numeroEndereco());
        funcionarioAtualizado.setDataAtualizacao(new Date());
        funcionarioAtualizado.setCidade(funcionarioRequestDTO.cidade());
        funcionarioAtualizado.setUF(funcionarioRequestDTO.UF());

        return funcionarioRepository.save(funcionarioAtualizado);
    }

    public FuncionarioEntity listarFuncionarioId(Integer funcionarioId) {
        Optional<FuncionarioEntity> obj = funcionarioRepository.findById(funcionarioId);

        return obj.orElseThrow(() -> new RuntimeException("Funcionario ID: " + funcionarioId + " não encontrado"));
    }

    public List<FuncionarioEntity> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void deletarFuncionario(Integer funcionarioId) {
        FuncionarioEntity funcionarioDeletado = listarFuncionarioId(funcionarioId);

        funcionarioRepository.deleteById(funcionarioDeletado.getId());
    }

    public Integer getNextId() {
        Integer lastId = funcionarioRepository.findLastId();

        return (lastId != null) ? lastId + 1 : 1;
    }
}
