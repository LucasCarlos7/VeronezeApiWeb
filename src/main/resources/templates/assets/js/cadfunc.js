/*CADASTRO DE Funcionarios*////////////////////////////////////////////////////////////////////


let isEditing = false; // Controla se está editando um Funcionarios existente
let editingId = null;  // Armazena o ID do Funcionarios sendo editado


// Buscar fornecedor pelo ID
document.querySelector('#buscar').addEventListener('click', async (event) => {
    event.preventDefault();


    const idfunc = document.getElementById("id").value;
    if (!idfunc) {
        alert("Por favor, insira o ID do Funcionario");
        return;
    }

    try {

        //
        const rota = `http://127.0.0.1:8080/funcionario/pesquisar/${idfunc}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Funcionario com ID ${idfunc} não encontrado.`);
        }

        const funcionario = await response.json();

        console.table(funcionario);

        // Preenche o formulário com os dados do fornedor
        document.getElementById('id').value = funcionario.id;
        document.getElementById('id').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('login').value = funcionario.login;
        document.getElementById('senha').value = funcionario.senha;
        document.getElementById('nome').value = funcionario.nome;
        document.getElementById('cpf').value = funcionario.cpf;


        document.getElementById('endereco').value = funcionario.endereco;
        document.getElementById('bairro').value = funcionario.bairro;
        document.getElementById('cidade').value = funcionario.cidade;
        document.getElementById('cep').value = funcionario.cep;
        document.getElementById('telefone').value = funcionario.telefone;
        document.getElementById('email').value = funcionario.email;
        document.getElementById('uf').value = funcionario.UF;
        document.getElementById('bairro').value = funcionario.bairro;
        document.getElementById('numend').value = funcionario.numeroEndereco;
        document.getElementById('add_date').value = funcionario.dataCriacao;
        document.getElementById('atuali').value = funcionario.dataAtualizacao;
        document.querySelector(`input[name="cargo"][value="${funcionario.cargo}"]`).checked = true;






        // Atualiza o estado para edição
        isEditing = true;
        editingId = funcionario.id;


    } catch (error) {
        console.error("Erro ao buscar Funcionario:", error);
        alert(error.message);
    }
});


// Botão incluir
document.querySelector('#incluir').addEventListener('click', async (event) => {
    event.preventDefault();
    try {
        const rota = `http://127.0.0.1:8080/funcionario/next-id`;
        const response = await fetch(rota);
        const nextIdfunc = parseInt(await response.json(), 10);

        document.getElementById('id').value = nextIdfunc;
        document.getElementById('id').setAttribute('disabled', '');
    } catch (error) {
        console.error("Erro ao buscar próximo ID:", error);
        alert("Erro ao obter o próximo ID do Funcionario.");
    }
});





// Salvar (Criar ou Atualizar) cliente
document.querySelector('#salvar').addEventListener('click', async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const cargo = document.querySelector('input[name="cargo"]:checked')?.value;
    const cpf = document.getElementById("cpf").value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById('email').value;
    const endereco = document.getElementById("endereco").value;
    const bairro = document.getElementById("bairro").value;
    const cidade = document.getElementById("cidade").value;
    const numeroEndereco = document.getElementById('numend').value;
    const cep = document.getElementById("cep").value;
    const UF = document.getElementById('uf').value;

    const funcionario = {
        nome: nome,
        cpf: cpf,
        login: login,
        senha: senha,
        cargo: cargo,
        endereco: endereco,
        bairro: bairro,
        numeroEndereco: numeroEndereco,
        cidade: cidade,
        UF: UF,
        cep: cep,
        telefone: telefone,
        email: email,
    };

    try {
        if (isEditing && editingId) {
            // Atualizar fornecedor existente (PUT)


            const updateUrl = `http://127.0.0.1:8080/funcionario/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(funcionario),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar Funcionario com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Funcionario atualizado com sucesso!");
            console.log("Funcionario atualizado:", resultado);
        } else {
            // Criar novo Fornecedor (POST)
            const postProdUrl = `http://127.0.0.1:8080/funcionario/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(funcionario),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar funcionario: ${response.status}`);
            }

            const resultado = await response.json();
            alert("funcionario criado com sucesso!");
            console.log("funcionario criado:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar funcionario:", error);
        alert(error.message);
    }






});

// Botão EXCLUIR cliente
document.querySelector('#excluir').addEventListener('click', async (event) => {
    event.preventDefault();
    const idfunc = document.getElementById("id").value;
    console.log(idfunc)
    const deletefunc = `http://127.0.0.1:8080/funcionario/deletar/${idfunc}`

    try {
        const response = await fetch(deletefunc, {
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
    document.getElementById('id').value = "";
    document.getElementById('id').removeAttribute('disabled'); // habilita o campo ID
    document.getElementById('nome').value = "";
    document.getElementById('cpf').value = "";
    document.getElementById('login').value = "";

    document.getElementById('senha').value = "";

    document.getElementById('endereco').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('cidade').value = "";
    document.getElementById('cep').value = "";
    document.getElementById('telefone').value = "";
    document.getElementById('email').value = "";
    document.getElementById('uf').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('numend').value = "";
    document.getElementById('add_date').value = "";
    document.getElementById('atuali').value = "";

    isEditing = false;
    editingId = null;
}

