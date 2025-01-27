/*CADASTRO DE FORNECEDORES*////////////////////////////////////////////////////////////////////


let isEditing = false; // Controla se está editando um fornecedor existente
let editingId = null;  // Armazena o ID do FORNECEDOR sendo editado


// Buscar fornecedor pelo ID
document.querySelector('#buscarforn').addEventListener('click', async (event) => {
    event.preventDefault();
    

    const idfornecedor = document.getElementById("idforn").value;
    if (!idfornecedor) {
        alert("Por favor, insira o ID do Fornecedor");
        return;
    }

    try {

        //
        const rota = `http://127.0.0.1:8080/fornecedor/pesquisar/${idfornecedor}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`FORNECEDOR com ID ${idfornecedor} não encontrado.`);
        }

        const fornecedor = await response.json();
        
        console.table(fornecedor);
        // Preenche o formulário com os dados do fornedor
        document.getElementById('idforn').value = fornecedor.id;
        document.getElementById('idforn').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('nomeforn').value = fornecedor.nome;
        document.getElementById('cpf').value = fornecedor.cpf;
        document.getElementById('cnpj').value = fornecedor.cnpj;
        document.getElementById('ie').value = fornecedor.inscricaoEstadual;
        document.getElementById('enderecoforn').value = fornecedor.endereco;
        document.getElementById('bairro').value = fornecedor.bairro;
        document.getElementById('cidade').value = fornecedor.cidade;
        document.getElementById('cep').value = fornecedor.cep;
        document.getElementById('telefone').value = fornecedor.telefone;
        document.getElementById('email').value = fornecedor.email;
        document.getElementById('uf').value = fornecedor.uf;
        document.getElementById('bairro').value = fornecedor.bairro;
        document.getElementById('numend').value = fornecedor.numeroEndereco;
        document.getElementById('add_date_forn').value = fornecedor.dataCriacao;
        document.getElementById('atuali_forn').value = fornecedor.dataAtualizacao;
        document.querySelector(`input[name="tipopessoa"][value="${fornecedor.tipoPessoa}"]`).checked = true;
       



        const tipopessoa  = document.querySelector(`input[name="tipopessoa"][value="${fornecedor.tipoPessoa}"]`).value
        console.log(`tipo de pessoa é ${tipopessoa}`)

        if(tipopessoa == "FISICA"){
            document.getElementById('cnpj').setAttribute('disabled', '');
            document.getElementById('cpf').removeAttribute('disabled');
        }
        else if (tipopessoa == "JURIDICA"){
            document.getElementById('cpf').setAttribute('disabled', '');
            document.getElementById('cnpj').removeAttribute('disabled');
        }
        else{
            console.log("não é juridico e nem fisico")
        }

 
  

        // Atualiza o estado para edição
        isEditing = true;
        editingId = fornecedor.id;

        
    } catch (error) {
        console.error("Erro ao buscar FORNECEDOR:", error);
        alert(error.message);
    }
});


// Botão incluir
document.querySelector('#incluirforn').addEventListener('click', async (event) => {
    event.preventDefault();
    try {
        const rota = `http://127.0.0.1:8080/fornecedor/next-id`;
        const response = await fetch(rota);
        const nextIdforn = parseInt(await response.json(), 10);
        
        document.getElementById('idforn').value = nextIdforn;
        document.getElementById('idforn').setAttribute('disabled', '');
        } catch (error) {
            console.error("Erro ao buscar próximo ID:", error);
            alert("Erro ao obter o próximo ID do produto.");
            }
});





// Salvar (Criar ou Atualizar) cliente
document.querySelector('#salvarforn').addEventListener('click', async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nomeforn").value;
    const cnpj = document.getElementById("cnpj").value;
    const cpf = document.getElementById("cpf").value;
    const inscricaoEstadual = document.getElementById("ie").value;
    const endereco = document.getElementById("enderecoforn").value;
    const numeroEndereco = document.getElementById('numend').value;
    const bairro = document.getElementById("bairro").value;
    const cidade = document.getElementById("cidade").value;
    const cep = document.getElementById("cep").value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById('email').value;    
    const uf = document.getElementById('uf').value;
    const tipoPessoa = document.querySelector('input[name="tipopessoa"]:checked')?.value;


    const fornecedor = {
        nome: nome,
        cpf: cpf,
        cnpj: cnpj,
        inscricaoEstadual: inscricaoEstadual,
        endereco: endereco,
        bairro: bairro,
        numeroEndereco: numeroEndereco,
        cidade: cidade,
        uf: uf,
        cep: cep,
        telefone: telefone,
        email: email,
        tipoPessoa: tipoPessoa
    };

    try {
        if (isEditing && editingId) {
            // Atualizar fornecedor existente (PUT)

            
            const updateUrl = `http://127.0.0.1:8080/fornecedor/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(fornecedor),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar produto com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Fornecedor atualizado com sucesso!");
            console.log("Fornecedor atualizado:", resultado);
        } else {
            // Criar novo Fornecedor (POST)
            const postProdUrl = `http://127.0.0.1:8080/fornecedor/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(fornecedor),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar FORNECEDOR: ${response.status}`);
            }

            const resultado = await response.json();
            alert("Fornecedor criado com sucesso!");
            console.log("Fornecedor criado:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar Fornecedor:", error);
        alert(error.message);
    }






});

// Botão EXCLUIR cliente
document.querySelector('#excluirforn').addEventListener('click', async (event) => {
    event.preventDefault();
    const idforn = document.getElementById("idforn").value;
    console.log(idforn)
    const deleteprod = `http://127.0.0.1:8080/fornecedor/deletar/${idcli}`

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
    document.getElementById('idforn').value = "";
    document.getElementById('idforn').removeAttribute('disabled'); // habilita o campo ID
    document.getElementById('nomeforn').value = "";
    document.getElementById('cpf').value = "";
    document.getElementById('cnpj').value = "";
    document.getElementById('ie').value = "";
    document.getElementById('enderecoforn').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('cidade').value = "";
    document.getElementById('cep').value = "";
    document.getElementById('telefone').value = "";
    document.getElementById('email').value = "";
    document.getElementById('uf').value = "";
    document.getElementById('bairro').value = "";
    document.getElementById('numend').value = "";
    document.getElementById('add_date_forn').value = "";
    document.getElementById('atuali_forn').value = "";



    isEditing = false;
    editingId = null;
}

