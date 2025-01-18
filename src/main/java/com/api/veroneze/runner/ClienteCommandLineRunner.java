package com.api.veroneze.runner;

import com.api.veroneze.data.entity.ClienteEntity;
import com.api.veroneze.data.entity.dto.ClienteRequestDTO;
import com.api.veroneze.data.entity.enums.TipoPessoaEnum;
import com.api.veroneze.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClienteCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void run(String... args) throws Exception {

        List<ClienteEntity> clientes = clienteService.listarTodosClientes();

        boolean clienteCpfJaExiste = false;
        boolean clienteCnpjJaExiste = false;

        ClienteRequestDTO clienteCPF = new ClienteRequestDTO(
                "Bento Lucca Pereira", TipoPessoaEnum.FISICA, "86131408823", null,
                "8325625154", "bento_pereira@eguia.com", "58700123",
                "Travessa Cruzeiro da Paz", "Centro", "212", "Araguaína", "TO");

        ClienteRequestDTO clienteCNPJ = new ClienteRequestDTO(
                "Guilherme e Emanuel ME", TipoPessoaEnum.JURIDICA, null, "32763259000180",
                "6329941740", "posvenda@guilhermeeemanuelme.com", "77063232",
                "Quadra T 22 Rua LO 9", "Taquaralto", "366", "Palmas", "TO");

        for (ClienteEntity clienteList : clientes) {
            if (Objects.equals(clienteList.getCpf(), clienteCPF.cpf())) {
                clienteCpfJaExiste = true;
            }
            if (Objects.equals(clienteList.getCnpj(), clienteCNPJ.cnpj())) {
                clienteCnpjJaExiste = true;
            }
        }

        if (clientes.size() == 0) {
            clienteService.salvarCliente(clienteCPF);
            clienteService.salvarCliente(clienteCNPJ);
        } else if (!clienteCpfJaExiste) {
            clienteService.salvarCliente(clienteCPF);
        } else if (!clienteCnpjJaExiste) {
            clienteService.salvarCliente(clienteCNPJ);
        }
    }
}
