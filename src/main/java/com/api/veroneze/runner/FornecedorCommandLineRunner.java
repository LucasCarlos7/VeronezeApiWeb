package com.api.veroneze.runner;

import com.api.veroneze.data.entity.FornecedorEntity;
import com.api.veroneze.data.entity.dto.FornecedorRequestDTO;
import com.api.veroneze.data.entity.enums.TipoPessoaEnum;
import com.api.veroneze.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FornecedorCommandLineRunner implements CommandLineRunner {

    @Autowired
    FornecedorService fornecedorService;

    @Override
    public void run(String... args) throws Exception {

        List<FornecedorEntity> fornecedoresList = fornecedorService.listarTodosFornecedores();

        boolean fornecedorCpfExiste = false;
        boolean fornecedorCnpjExiste = false;

        FornecedorRequestDTO fornecedorCNPJ = new FornecedorRequestDTO(
                "Sebastiana e Pietra ME", TipoPessoaEnum.JURIDICA, null, "62587486000163", "58039915029",
                "6335815290", "presidencia@sebastianaepietrame.com", "77809270", "Rua dos Motoristas",
                "Jardim Paulista", "112", "Araguaína", "TO");

        FornecedorRequestDTO fornecedorCPF = new FornecedorRequestDTO(
                "Guilherme Vicente Souza", TipoPessoaEnum.FISICA, "72311528483", null, null,
                "6828621462", "guilherme_souza@oerlikon.com", "69918310", "Rua São Sebastião",
                "Isaura Parente", "892", "Rio Branco", "AC");

        for (FornecedorEntity fornecedores : fornecedoresList) {
            if (Objects.equals(fornecedores.getCpf(), fornecedorCPF.cpf())) {
                fornecedorCpfExiste = true;
            }
            if (Objects.equals(fornecedores.getCnpj(), fornecedorCNPJ.cnpj())) {
                fornecedorCnpjExiste = true;
            }
        }

        if (fornecedoresList.size() == 0) {
            fornecedorService.salvarFornecedor(fornecedorCPF);
            fornecedorService.salvarFornecedor(fornecedorCNPJ);
        } else if (!fornecedorCpfExiste) {
            fornecedorService.salvarFornecedor(fornecedorCPF);
        } else if (!fornecedorCnpjExiste) {
            fornecedorService.salvarFornecedor(fornecedorCNPJ);
        }
    }
}
