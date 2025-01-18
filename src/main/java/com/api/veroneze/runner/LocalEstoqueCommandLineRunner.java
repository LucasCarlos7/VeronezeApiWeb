package com.api.veroneze.runner;

import com.api.veroneze.data.entity.LocalEstoqueEntity;
import com.api.veroneze.data.entity.dto.LocalEstoqueRequestDTO;
import com.api.veroneze.service.LocalEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class LocalEstoqueCommandLineRunner implements CommandLineRunner {

    @Autowired
    LocalEstoqueService localEstoqueService;

    @Override
    public void run(String... args) throws Exception {

        List<LocalEstoqueEntity> localEstoqueList = localEstoqueService.listarTodosLocalEstoque();

        boolean estoqueCongeladoJaExiste = false;
        boolean estoqueDesidratadoJaExiste = false;
        boolean estoqueAcabadoJaExiste = false;

        LocalEstoqueRequestDTO estoqueCongelado = new LocalEstoqueRequestDTO(
                "CONGELADO");

        LocalEstoqueRequestDTO estoqueDesidratado = new LocalEstoqueRequestDTO(
                "DESIDRATADO");

        LocalEstoqueRequestDTO estoqueAcabado = new LocalEstoqueRequestDTO(
                "ACABADO");

        for (LocalEstoqueEntity estoques : localEstoqueList) {
            if (Objects.equals(estoques.getNome(), estoqueCongelado.nome())) {
                estoqueCongeladoJaExiste = true;
            }
            if (Objects.equals(estoques.getNome(), estoqueDesidratado.nome())) {
                estoqueDesidratadoJaExiste = true;
            }
            if (Objects.equals(estoques.getNome(), estoqueAcabado.nome())) {
                estoqueAcabadoJaExiste = true;
            }
        }

        if (localEstoqueList.size() == 0) {
            localEstoqueService.salvarLocalEstoque(estoqueCongelado);
            localEstoqueService.salvarLocalEstoque(estoqueDesidratado);
            localEstoqueService.salvarLocalEstoque(estoqueAcabado);
        } else if (!estoqueCongeladoJaExiste) {
            localEstoqueService.salvarLocalEstoque(estoqueCongelado);
        } else if (!estoqueDesidratadoJaExiste) {
            localEstoqueService.salvarLocalEstoque(estoqueDesidratado);
        } else if (!estoqueAcabadoJaExiste) {
            localEstoqueService.salvarLocalEstoque(estoqueAcabado);
        }
    }
}
