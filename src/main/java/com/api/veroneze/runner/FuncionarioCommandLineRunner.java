package com.api.veroneze.runner;

import com.api.veroneze.data.entity.FuncionarioEntity;
import com.api.veroneze.data.entity.dto.FuncionarioRequestDTO;
import com.api.veroneze.data.entity.enums.CargoEnum;
import com.api.veroneze.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FuncionarioCommandLineRunner implements CommandLineRunner {

    @Autowired
    FuncionarioService funcionarioService;

    @Override
    public void run(String... args) throws Exception {

        List<FuncionarioEntity> funcionariosList = funcionarioService.listarTodosFuncionarios();

        boolean funcionario1JaExiste = false;
        boolean funcionario2JaExiste = false;

        FuncionarioRequestDTO funcionario1 = new FuncionarioRequestDTO(
                "Bernardo Daniel Jesus", "1", "1", CargoEnum.DIRETORIA, "57610885177",
                "6136117591", "bernardo_daniel_jesus@pobox.com", "73310156",
                "Área Setor Educacional Lote D", "Setor de Educação (Planaltina)", "742",
                "Brasília", "DF");

        FuncionarioRequestDTO funcionario2 = new FuncionarioRequestDTO(
                "Lorenzo Augusto Souza", "2", "2", CargoEnum.DIRETORIA, "03586089457",
                "4737129813", "lorenzo.augusto@edwar.com", "89036490",
                "Rua Oswaldo Cruz", "Velha", "132",
                "Blumenau", "SC");

        for (FuncionarioEntity funcionarios : funcionariosList) {
            if (Objects.equals(funcionarios.getCpf(), funcionario1.cpf())) {
                funcionario1JaExiste = true;
            } else if (Objects.equals(funcionarios.getCpf(), funcionario2.cpf())) {
                funcionario2JaExiste = true;
            }
        }

        if (funcionariosList.size() == 0) {
            funcionarioService.salvarFuncionario(funcionario1);
            funcionarioService.salvarFuncionario(funcionario2);
        } else if (!funcionario1JaExiste) {
            funcionarioService.salvarFuncionario(funcionario1);
        } else if (!funcionario2JaExiste) {
            funcionarioService.salvarFuncionario(funcionario1);
        }
    }
}
