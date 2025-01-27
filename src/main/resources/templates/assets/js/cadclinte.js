/*CADASTRO DE CLIENTES*////////////////////////////////////////////////////////////////////


let isEditing = false; // Controla se está editando um cliente existente
let editingId = null;  // Armazena o ID do cliente sendo editado


// Buscar cliente pelo ID
document.querySelector('#buscarcli').addEventListener('click', async (event) => {
    event.preventDefault();


    const idcliente = document.getElementById("idcli").value;
    if (!idcliente) {
        alert("Por favor, insira o ID do Cliente");
        return;
    }

    try {

        //
        const rota = `http://127.0.0.1:8080/cliente/pesquisar/${idcliente}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Cliente com ID ${idcliente} não encontrado.`);
        }

        const cliente = await response.json();

        console.table(cliente);

        // Preenche o formulário com os dados do cliente
        document.getElementById('idcli').value = cliente.id;
        document.getElementById('idcli').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('nomecli').value = cliente.nome;
        document.getElementById('cpf').value = cliente.cpf;
        document.getElementById('cnpj').value = cliente.cnpj;
        document.getElementById('enderecocli').value = cliente.endereco;
        document.getElementById('bairro').value = cliente.bairro;
        document.getElementById('cidade').value = cliente.cidade;
        document.getElementById('cep').value = cliente.cep;
        document.getElementById('telefone').value = cliente.telefone;
        document.getElementById('email').value = cliente.email;
        document.getElementById('ufcli').value = cliente.uf;
        document.getElementById('bairro').value = cliente.bairro;
        document.getElementById('numend').value = cliente.numeroEndereco;
        document.getElementById('add_date_cli').value = cliente.dataCriacao;
        document.getElementById('atuali_cli').value = cliente.dataAtualizacao;
        document.querySelector(`input[name="tipopessoa"][value="${cliente.tipoPessoa}"]`).checked = true;



        const tipopessoa = document.querySelector(`input[name="tipopessoa"][value="${cliente.tipoPessoa}"]`).value
        console.log(`tipo de pessoa é ${tipopessoa}`)

        if (tipopessoa == "FISICA") {
            document.getElementById('cnpj').setAttribute('disabled', '');
        }




        // Atualiza o estado para edição
        isEditing = true;
        editingId = cliente.id;
    } catch (error) {
        console.error("Erro ao buscar Cliente:", error);
        alert(error.message);
    }
});
// Botão incluir
document.querySelector('#incluircli').addEventListener('click', async (event) => {
    event.preventDefault();
    try {
        const rota = `http://127.0.0.1:8080/cliente/next-id`;
        const response = await fetch(rota);
        const nextIdCli = parseInt(await response.json(), 10);

        document.getElementById('idcli').value = nextIdCli;
        document.getElementById('idcli').setAttribute('disabled', '');
    } catch (error) {
        console.error("Erro ao buscar próximo ID:", error);
        alert("Erro ao obter o próximo ID do Cliente.");
    }
});





// Salvar (Criar ou Atualizar) cliente
document.querySelector('#salvarcli').addEventListener('click', async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nomecli").value;
    const cnpj = document.getElementById("cnpj").value;
    const cpf = document.getElementById("cpf").value;
    const endereco = document.getElementById("enderecocli").value;
    const bairro = document.getElementById("bairro").value;
    const cidade = document.getElementById("cidade").value;
    const numeroEndereco = document.getElementById('numend').value;
    const cep = document.getElementById("cep").value;
    const uf = document.getElementById('ufcli').value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById('email').value;
    const tipoPessoa = document.querySelector('input[name="tipopessoa"]:checked')?.value;
    


    const cliente = {
        nome: nome,
        tipoPessoa: tipoPessoa,
        cpf: cpf,
        cnpj: cnpj,
        telefone: telefone,
        email: email,
        endereco: endereco,
        bairro: bairro,
        numeroEnd: numeroEndereco,
        cidade: cidade,
        UF: uf,
        cep: cep,
    };

    console.log(JSON.stringify(cliente))

    try {
        if (isEditing && editingId) {
            // Atualizar produto existente (PUT)


            const updateUrl = `http://127.0.0.1:8080/cliente/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cliente),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar Cliente com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Cliente atualizado com sucesso!");
            console.log("Cliente atualizado:", resultado);
        } else {
            // Criar novo produto (POST)
            const postProdUrl = `http://127.0.0.1:8080/cliente/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cliente),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar Cliente: ${response.status}`);
            }

            const resultado = await response.json();
            alert("Cliente criado com sucesso!");
            console.log("Cliente criado:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar Cliente:", error);
        alert(error.message);
    }






});

// Botão EXCLUIR cliente
document.querySelector('#excluircli').addEventListener('click', async (event) => {
    event.preventDefault();
    const idcli = document.getElementById("idcli").value;
    console.log(idcli)
    const deleteprod = `http://127.0.0.1:8080/cliente/deletar/${idcli}`

    try {
        const response = await fetch(deleteprod, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error(`Erro: ${response.status}`);
        }

        console.log('Recurso excluído com sucesso');
    } catch (error) {
        console.error('Erro ao excluir recurso:', error);
    }
    resetForm()
}

);




// Cancelar operação
document.querySelector('#canceloperacao').addEventListener('click', (event) => {
    event.preventDefault();
    resetForm();
});

// Função para resetar o formulário
function resetForm() {
    document.getElementById('idcli').value = "";
    document.getElementById('idcli').removeAttribute('disabled'); // habilita o campo ID
    document.getElementById('nomecli').value = "";
    document.getElementById('cpf').value = "";
    document.getElementById('cnpj').value = "";
    document.getElementById('enderecocli').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('cidade').value = "";
    document.getElementById('cep').value = "";
    document.getElementById('telefone').value = "";
    document.getElementById('email').value = "";
    document.getElementById('ufcli').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('numend').value = "";
    document.getElementById('add_date_cli').value = "";
    document.getElementById('atuali_cli').value = "";



    isEditing = false;
    editingId = null;
}

