
/*CADASTRO DE PRODUTO*/

/* Outras informações do cadastro do produto */
const abasinfoadd = document.querySelectorAll('.aba-btn');

abasinfoadd.forEach((btnaba) => btnaba.addEventListener('click', () => clicar_na_abaprod(btnaba)));

const clicar_na_abaprod = (btnaba) => {
    const infosprod = document.querySelectorAll(".contentprod");
    infosprod.forEach((contentprod) => contentprod.classList.remove('show'));

    const ContentIDprod = btnaba.getAttribute("content-id");
    const continfo = document.getElementById(ContentIDprod);
    continfo.classList.add('show');

};

let isEditing = false; // Controla se está editando um produto existente
let editingId = null;  // Armazena o ID do produto sendo editado

document.getElementById('nomeprod').setAttribute('disabled', '');
document.getElementById('precoprod').setAttribute('disabled', '');


// Buscar produto pelo ID
document.querySelector('#buscarprod').addEventListener('click', async (event) => {
    event.preventDefault();

    document.getElementById('nomeprod').removeAttribute('disabled');
    document.getElementById('precoprod').removeAttribute('disabled');
    document.getElementById("buscarprod").disabled = true;


    const idproduto = document.getElementById("idprod").value;
    if (!idproduto) {
        alert("Por favor, insira o ID do produto.");
        return;
    }



    try {
        //Locais de estoque e quantidade de saldo
        const estoqueprod = `http://127.0.0.1:8080/estoque/saldo-produto/${idproduto}`; // Exemplo de API pública
        const responseestoque = await fetch(estoqueprod);
        const estoque = await responseestoque.json();

        // Verificando se os dados retornados são um array
        if (Array.isArray(estoque)) {
            adicionarLinhasprod(estoque);
        } else {
            console.error('Os dados retornados não estão no formato esperado:', estoque);
        }

        // Função para adicionar linhas na tabela
        function adicionarLinhasprod(estoque) {
            const tabelaestoque = document.getElementById('saldoestoque').querySelector('tbody');

            // Itera sobre os dados retornados pela API
            estoque.forEach(item => {
                const row = document.createElement('tr');

                // Adicionando valores nas células (ajustando para os campos retornados pela API)
                const idle = document.createElement('td');
                idle.textContent = item.localEstoqueId || '-';
                row.appendChild(idle);

                const descestoque = document.createElement('td');
                descestoque.textContent = item.descricao || '-';
                row.appendChild(descestoque);

                const saldoestoque = document.createElement('td');
                saldoestoque.textContent = item.saldoTotal || '0';
                row.appendChild(saldoestoque);

                // Adicionando a linha ao corpo da tabela
                tabelaestoque.appendChild(row);
            });
        }


        //PRODUTO COMPOSTO DO PRODUTO PESQUISADO
        const prodcomposto = `http://127.0.0.1:8080/produtoComposto/listar-produtos/${idproduto}`; // Exemplo de API pública
        const responsecomposto = await fetch(prodcomposto);
        const resultcomposto = await responsecomposto.json();

        // Verificando se os dados retornados são um array
        if (Array.isArray(resultcomposto)) {
            adicionarLinhascomp(resultcomposto);
        } else {
            console.error('Os dados retornados não estão no formato esperado:', resultcomposto);
        }

        // Função para adicionar linhas na tabela
        function adicionarLinhascomp(resultcomposto) {
            const tabelacomposto = document.getElementById('table_comp_kit').querySelector('tbody');

            // Itera sobre os dados retornados pela API
            resultcomposto.forEach(comp => {
                const row = document.createElement('tr');

                // Adicionando valores nas células (ajustando para os campos retornados pela API)
                const idcomposto = document.createElement('td');
                idcomposto.textContent = comp.produtoCompostoId || '-';
                row.appendChild(idcomposto);

                const nomeprodcomp = document.createElement('td');
                nomeprodcomp.textContent = comp.nomeProdutoComposto || '-';
                row.appendChild(nomeprodcomp);

                const compprop = document.createElement('td');
                compprop.textContent = comp.proporcao || '0';
                row.appendChild(compprop);

                // Adicionando a linha ao corpo da tabela
                tabelacomposto.appendChild(row);
            });
        }







        const rota = `http://127.0.0.1:8080/produto/pesquisar/${idproduto}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Produto com ID ${idproduto} não encontrado.`);
        }

        const produto = await response.json();
        console.log("Produto encontrado:", produto);

        // Preenche o formulário com os dados do produto
        document.getElementById('idprod').value = produto.id;
        document.getElementById('idprod').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('nomeprod').value = produto.nome;
        document.getElementById('precoprod').value = parseFloat(produto.preco).toFixed(2);
        document.getElementById('add_date_prod').value = produto.dataCriacao || '';
        document.getElementById('atuali_prod').value = produto.dataAtualizacao || '';
        document.querySelector(`input[name="tipoprod"][value="${produto.tipoProduto}"]`).checked = true;




        // Atualiza o estado para edição
        isEditing = true;
        editingId = produto.id;
    } catch (error) {
        console.error("Erro ao buscar produto:", error);
        alert(error.message);
    }
});

// Botão incluir
document.querySelector('#incluirprod').addEventListener('click', async (event) => {
    event.preventDefault();
    document.getElementById('nomeprod').removeAttribute('disabled');
    document.getElementById('precoprod').removeAttribute('disabled');
    try {
        const rota = `http://127.0.0.1:8080/produto/next-id`;
        const response = await fetch(rota);
        const nextIdProd = parseInt(await response.json(), 10);

        document.getElementById('idprod').value = nextIdProd;
        document.getElementById('idprod').setAttribute('disabled', '');
        document.getElementById('quantidadeprod').setAttribute('disabled', '');
    } catch (error) {
        console.error("Erro ao buscar próximo ID:", error);
        alert("Erro ao obter o próximo ID do produto.");
    }
});




// Salvar (Criar ou Atualizar) produto
document.querySelector('#salvarprod').addEventListener('click', async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nomeprod").value;
    const preco = parseFloat(document.getElementById("precoprod").value);
    const tipoProduto = document.querySelector('input[name="tipoprod"]:checked')?.value;

    if (!nome || isNaN(preco) || !tipoProduto) {
        alert("Por favor, preencha todos os campos antes de salvar.");
        return;
    }

    const produto = {
        nome: nome,
        preco: preco,
        tipoProduto: tipoProduto
    };

    try {
        if (isEditing && editingId) {
            // Atualizar produto existente (PUT)
            const updateUrl = `http://127.0.0.1:8080/produto/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(produto),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar produto com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Produto atualizado com sucesso!");
            console.log("Produto atualizado:", resultado);
        } else {
            // Criar novo produto (POST)
            const postProdUrl = `http://127.0.0.1:8080/produto/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(produto),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar produto: ${response.status}`);
            }

            const resultado = await response.json();
            alert("Produto criado com sucesso!");
            console.log("Produto criado:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar produto:", error);
        alert(error.message);
    }






});

// Botão EXCLUIR PRODUTO
document.querySelector('#excluirprod').addEventListener('click', async (event) => {
    event.preventDefault();
    const idprod = document.getElementById("idprod").value;
    console.log(idprod)
    const deleteprod = `http://127.0.0.1:8080/produto/deletar/${idprod}`

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
    const tabelaestoque = document.getElementById('saldoestoque').querySelector('tbody');
    tabelaestoque.innerHTML = '';

    const tabelacompkit = document.getElementById('table_comp_kit').querySelector('tbody');
    tabelacompkit.innerHTML = '';
    document.getElementById('buscarprod').removeAttribute('disabled');
    resetForm();
});

// Função para resetar o formulário
function resetForm() {
    document.getElementById('idprod').value = "";
    document.getElementById('idprod').removeAttribute('disabled'); // Reativa o campo ID
    document.getElementById('nomeprod').value = "";
    document.getElementById('precoprod').value = "";
    document.getElementById('add_date_prod').value = "";
    document.getElementById('atuali_prod').value = "";
    document.querySelectorAll('input[name="tipoprod"]').forEach((radio) => (radio.checked = false));
    document.getElementById('nomeprod').setAttribute('disabled', '');
    document.getElementById('precoprod').setAttribute('disabled', '');





    isEditing = false;
    editingId = null;
}


// Função para buscar dados da API e adicionar linhas na tabela
