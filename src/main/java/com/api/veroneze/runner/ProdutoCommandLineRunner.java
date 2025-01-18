package com.api.veroneze.runner;

import com.api.veroneze.data.entity.ProdutoEntity;
import com.api.veroneze.data.entity.dto.ProdutoRequestDTO;
import com.api.veroneze.data.entity.enums.TipoProdutoEnum;
import com.api.veroneze.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Order(1)
public class ProdutoCommandLineRunner implements CommandLineRunner {

    @Autowired
    ProdutoService produtoService;

    @Override
    public void run(String... args) throws Exception {

        List<ProdutoEntity> produtosList = produtoService.listarTodosProdutos();

        boolean produtoMateriaPrima1JaExiste = false;
        boolean produtoMateriaPrima2JaExiste = false;
        boolean produtoEmProcessamento1JaExiste = false;
        boolean produtoEmProcessamento2JaExiste = false;
        boolean produtoAcabado1JaExiste = false;
        boolean produtoAcabado2JaExiste = false;
        boolean produtoAcabado3JaExiste = false;
        boolean produtoAcabado4JaExiste = false;

        ProdutoRequestDTO produtoMateriaPrima1 = new ProdutoRequestDTO(
                "VERGALHO BOVINO CONGELADO", TipoProdutoEnum.MATÉRIA_PRIMA, 57.53);

        ProdutoRequestDTO produtoEmProcessamento1 = new ProdutoRequestDTO(
                "VERGALHO BOVINO DESIDRATADO", TipoProdutoEnum.PRODUTO_EM_PROCESSO, 281.45);

        ProdutoRequestDTO produtoAcabado1 = new ProdutoRequestDTO(
                "VERGALHO BOVINO DESIDRATADO - PALITO 6''", TipoProdutoEnum.PRODUTO_ACABADO, 565.84);

        ProdutoRequestDTO produtoAcabado2 = new ProdutoRequestDTO(
                "VERGALHO BOVINO DESIDRATADO - PALITO 12''", TipoProdutoEnum.PRODUTO_ACABADO, 619.48);

        ProdutoRequestDTO produtoMateriaPrima2 = new ProdutoRequestDTO(
                "TRAQUEIA BOVINA CONGELADA", TipoProdutoEnum.MATÉRIA_PRIMA, 33.88);

        ProdutoRequestDTO produtoEmProcessamento2 = new ProdutoRequestDTO(
                "TRAQUEIA BOVINA DESIDRATADA", TipoProdutoEnum.PRODUTO_EM_PROCESSO, 172.99);

        ProdutoRequestDTO produtoAcabado3 = new ProdutoRequestDTO(
                "TRAQUEIA BOVINA DESIDRATADA - PALITO 6''", TipoProdutoEnum.PRODUTO_ACABADO, 367.45);

        ProdutoRequestDTO produtoAcabado4 = new ProdutoRequestDTO(
                "TRAQUEIA BOVINA DESIDRATADA - PALITO 12''", TipoProdutoEnum.PRODUTO_ACABADO, 431.12);

        for (ProdutoEntity produtos : produtosList) {
            if (Objects.equals(produtos.getNome(), produtoMateriaPrima1.nome())) {
                produtoMateriaPrima1JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoMateriaPrima2.nome())) {
                produtoMateriaPrima2JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoEmProcessamento1.nome())) {
                produtoEmProcessamento1JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoEmProcessamento2.nome())) {
                produtoEmProcessamento2JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoAcabado1.nome())) {
                produtoAcabado1JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoAcabado2.nome())) {
                produtoAcabado2JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoAcabado3.nome())) {
                produtoAcabado3JaExiste = true;
            }
            if (Objects.equals(produtos.getNome(), produtoAcabado4.nome())) {
                produtoAcabado4JaExiste = true;
            }
        }

        if (produtosList.size() == 0) {
            produtoService.salvarProduto(produtoMateriaPrima1);
            produtoService.salvarProduto(produtoMateriaPrima2);
            produtoService.salvarProduto(produtoEmProcessamento1);
            produtoService.salvarProduto(produtoEmProcessamento2);
            produtoService.salvarProduto(produtoAcabado1);
            produtoService.salvarProduto(produtoAcabado2);
            produtoService.salvarProduto(produtoAcabado3);
            produtoService.salvarProduto(produtoAcabado4);
        } else if (!produtoMateriaPrima1JaExiste) {
            produtoService.salvarProduto(produtoMateriaPrima1);
        } else if (!produtoMateriaPrima2JaExiste) {
            produtoService.salvarProduto(produtoMateriaPrima2);
        } else if (!produtoEmProcessamento1JaExiste) {
            produtoService.salvarProduto(produtoEmProcessamento1);
        } else if (!produtoEmProcessamento2JaExiste) {
            produtoService.salvarProduto(produtoEmProcessamento2);
        } else if (!produtoAcabado1JaExiste) {
            produtoService.salvarProduto(produtoAcabado1);
        } else if (!produtoAcabado2JaExiste) {
            produtoService.salvarProduto(produtoAcabado2);
        } else if (!produtoAcabado3JaExiste) {
            produtoService.salvarProduto(produtoAcabado3);
        } else if (!produtoAcabado4JaExiste) {
            produtoService.salvarProduto(produtoAcabado4);
        }
    }
}
