/*CADASTRO DE estoque*/


let isEditing = false; // Controla se está editando um estoque existente
let editingId = null;  // Armazena o ID do Funcionarios sendo editado


// Buscar fornecedor pelo ID
document.querySelector('#buscar').addEventListener('click', async (event) => {
    event.preventDefault();


    const idlocal = document.getElementById("id").value;
    if (!idlocal) {
        alert("Por favor, insira o ID do Local de estoque");
        return;
    }

    try {

        //
        const rota = `http://127.0.0.1:8080/local-estoque/pesquisar/${idlocal}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Local de Estoque com ID ${idlocal} não encontrado.`);
        }

        const localestoque = await response.json();

        console.table(localestoque);

        // Preenche o formulário com os dados do fornedor
        document.getElementById('id').value = localestoque.id;
        document.getElementById('id').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('nome').value = localestoque.nome;
        document.getElementById('add_date').value = localestoque.dataCriacao;
        document.getElementById('atuali').value = localestoque.dataAtualizacao;






        // Atualiza o estado para edição
        isEditing = true;
        editingId = localestoque.id;


    } catch (error) {
        console.error("Erro ao buscar estoque:", error);
        alert(error.message);
    }
});


// Botão incluir
document.querySelector('#incluir').addEventListener('click', async (event) => {
    event.preventDefault();
    try {
        const rota = `http://127.0.0.1:8080/local-estoque/next-id`;
        const response = await fetch(rota);
        const nextIdlocal = parseInt(await response.json(), 10);

        document.getElementById('id').value = nextIdlocal;
        document.getElementById('id').setAttribute('disabled', '');
    } catch (error) {
        console.error("Erro ao buscar próximo ID:", error);
        alert("Erro ao obter o próximo ID do estoque.");
    }
});





// Salvar (Criar ou Atualizar) 
document.querySelector('#salvar').addEventListener('click', async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value;


    const localestoque = {
        nome: nome
    };

    try {
        if (isEditing && editingId) {
            // Atualizar fornecedor existente (PUT)


            const updateUrl = `http://127.0.0.1:8080/local-estoque/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(localestoque),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar estoque com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Local de Estoque atualizado com sucesso!");
            console.log("Local de Estoque:", resultado);
        } else {
            // Criar novo Fornecedor (POST)
            const postProdUrl = `http://127.0.0.1:8080/local-estoque/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(localestoque),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar local de estoque: ${response.status}`);
            }

            const resultado = await response.json();
            alert("local de estoque criado com sucesso!");
            console.log("local de estoque criado:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar local de estoque:", error);
        alert(error.message);
    }






});



// Botão EXCLUIR 
document.querySelector('#excluir').addEventListener('click', async (event) => {
    event.preventDefault();
    const id = document.getElementById("id").value;
    console.log(id)
    const deletefunc = `http://127.0.0.1:8080/local-estoque/deletar/${id}`

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
    document.getElementById('add_date').value = "";
    document.getElementById('atuali').value = "";


    isEditing = false;
    editingId = null;
}

