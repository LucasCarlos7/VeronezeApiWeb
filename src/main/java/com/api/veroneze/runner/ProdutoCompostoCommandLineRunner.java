package com.api.veroneze.runner;

import com.api.veroneze.data.entity.ProdutoCompostoEntity;
import com.api.veroneze.data.entity.dto.ProdutoCompostoRequestDTO;
import com.api.veroneze.service.ProdutoCompostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Order(2)
public class ProdutoCompostoCommandLineRunner implements CommandLineRunner {

    @Autowired
    ProdutoCompostoService produtoCompostoService;

    @Override
    public void run(String... args) throws Exception {

        List<ProdutoCompostoEntity> produtoCompostoList = produtoCompostoService.listarTodosProdutosComposto();

        boolean vergalhoCongeladoJaExiste = false;
        boolean vergalhoDesidratado6JaExiste = false;
        boolean vergalhoDesidratado12JaExiste = false;
        boolean traqueiaCongeladaJaExiste = false;
        boolean traqueiaDesidratada6JaExiste = false;
        boolean traqueiaDesidratada12JaExiste = false;

        ProdutoCompostoRequestDTO vergalhoCongelado = new ProdutoCompostoRequestDTO(
                3, 1, 2.0);

        ProdutoCompostoRequestDTO traqueiaCongelada = new ProdutoCompostoRequestDTO(
                4, 2, 2.0);

        ProdutoCompostoRequestDTO vergalhoDesidratado6 = new ProdutoCompostoRequestDTO(
                5, 3, 2.0);

        ProdutoCompostoRequestDTO vergalhoDesidratado12 = new ProdutoCompostoRequestDTO(
                6, 3, 2.0);

        ProdutoCompostoRequestDTO traqueiaDesidratada6 = new ProdutoCompostoRequestDTO(
                7, 4, 2.0);

        ProdutoCompostoRequestDTO traqueiaDesidratada12 = new ProdutoCompostoRequestDTO(
                8, 4, 2.0);

        for (ProdutoCompostoEntity produtosComposto : produtoCompostoList) {
            if (Objects.equals(produtosComposto.getProdutoId(), vergalhoCongelado.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), vergalhoCongelado.produtoCompostoId())) {
                vergalhoCongeladoJaExiste = true;
            }
            if (Objects.equals(produtosComposto.getProdutoId(), vergalhoDesidratado6.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), vergalhoDesidratado6.produtoCompostoId())) {
                vergalhoDesidratado6JaExiste = true;
            }
            if (Objects.equals(produtosComposto.getProdutoId(), vergalhoDesidratado12.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), vergalhoDesidratado12.produtoCompostoId())) {
                vergalhoDesidratado12JaExiste = true;
            }
            if (Objects.equals(produtosComposto.getProdutoId(), traqueiaCongelada.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), traqueiaCongelada.produtoCompostoId())) {
                traqueiaCongeladaJaExiste = true;
            }
            if (Objects.equals(produtosComposto.getProdutoId(), traqueiaDesidratada6.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), traqueiaDesidratada6.produtoCompostoId())) {
                traqueiaDesidratada6JaExiste = true;
            }
            if (Objects.equals(produtosComposto.getProdutoId(), traqueiaDesidratada12.produtoId()) &&
                    Objects.equals(produtosComposto.getProdutoCompostoId(), traqueiaDesidratada12.produtoCompostoId())) {
                traqueiaDesidratada12JaExiste = true;
            }
        }

        if (produtoCompostoList.size() == 0) {
            produtoCompostoService.salvarProdutoComposto(vergalhoCongelado);
            produtoCompostoService.salvarProdutoComposto(vergalhoDesidratado6);
            produtoCompostoService.salvarProdutoComposto(vergalhoDesidratado12);
            produtoCompostoService.salvarProdutoComposto(traqueiaCongelada);
            produtoCompostoService.salvarProdutoComposto(traqueiaDesidratada6);
            produtoCompostoService.salvarProdutoComposto(traqueiaDesidratada12);
        } else if (!vergalhoCongeladoJaExiste) {
            produtoCompostoService.salvarProdutoComposto(vergalhoCongelado);
        } else if (!vergalhoDesidratado6JaExiste) {
            produtoCompostoService.salvarProdutoComposto(vergalhoDesidratado6);
        } else if (!vergalhoDesidratado12JaExiste) {
            produtoCompostoService.salvarProdutoComposto(vergalhoDesidratado12);
        } else if (!traqueiaCongeladaJaExiste) {
            produtoCompostoService.salvarProdutoComposto(traqueiaCongelada);
        } else if (!traqueiaDesidratada6JaExiste) {
            produtoCompostoService.salvarProdutoComposto(traqueiaDesidratada6);
        } else if (!traqueiaDesidratada12JaExiste) {
            produtoCompostoService.salvarProdutoComposto(traqueiaDesidratada12);
        }
    }
}
