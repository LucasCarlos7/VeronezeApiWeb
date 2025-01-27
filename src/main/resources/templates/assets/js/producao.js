
/*CADASTRO DE PRODUÇÃO*/


let isEditing = false; // Controla se está editando um produto existente
let editingId = null;  // Armazena o ID do produto sendo editado

// Buscar produto pelo ID
document.querySelector('#buscar').addEventListener('click', async (event) => {
    event.preventDefault();
    

    const idreg = document.getElementById("id").value;
    if (!idreg) {
        alert("Por favor, insira o ID do REGISTRO.");
        return;
    }

    try {
 

        const rota = `http://127.0.0.1:8080/produtoComposto/pesquisar/${idreg}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Produto com ID ${idreg} não encontrado.`);
        }

        const produtocomp = await response.json();
        console.log("Produto encontrado:", produtocomp);

        // Preenche o formulário com os dados do produto
        document.getElementById('id').value = produtocomp.id;
        document.getElementById('id').setAttribute('disabled', ''); // Desabilita o campo ID
       
        document.getElementById('prodid').value = produtocomp.produtoId;
        const idprod = document.getElementById('prodid').value
        const rotaprod = `http://127.0.0.1:8080/produto/pesquisar/${idprod}`;
        const responseprod = await fetch(rotaprod);
        const produto = await responseprod.json();
        document.getElementById('nomeprod').value = produto.nome;
        console.log(`PRODUTO PRINCIPAL: ${produto.nome}`)


        document.getElementById('prodcomp').value = produtocomp.produtoCompostoId;
        const idprodcomp = document.getElementById('prodcomp').value
        const rotaprodcomp = `http://127.0.0.1:8080/produto/pesquisar/${idprodcomp}`;
        const responseprodcomp = await fetch(rotaprodcomp);
        const produto_comp = await responseprodcomp.json();
        document.getElementById('nomeprodcomp').value = produto_comp.nome;
        console.log(`PRODUTO COMPOSTO: ${produto_comp.nome}`)


        document.getElementById('prop').value = produtocomp.proporcao;
        
 
        // Atualiza o estado para edição
        isEditing = true;
        editingId = produtocomp.id;
    } catch (error) {
        console.error("Erro ao buscar produto:", error);
        alert(error.message);
    }
});



// Botão incluir
document.querySelector('#incluir').addEventListener('click', async (event) => {
    event.preventDefault();
    document.getElementById('id').setAttribute('disabled', '');
    document.getElementById('nomeprod').setAttribute('disabled', '');
    document.getElementById('nomeprodcomp').setAttribute('disabled', '');
    
});




// Salvar (Criar ou Atualizar) produto
document.querySelector('#salvar').addEventListener('click', async (event) => {
    event.preventDefault();

    const produtoId = document.getElementById("prodid").value;
    const produtoCompostoId = document.getElementById("prodcomp").value;
    const proporcao = document.getElementById("prop").value;

    if (!produtoId || !(produtoCompostoId) || !proporcao) {
        alert("Por favor, preencha todos os campos antes de salvar.");
        return;
    }




    const produtocomp = {
        produtoId: produtoId,
        produtoCompostoId: produtoCompostoId,
        proporcao: proporcao
    };

    try {
        if (isEditing && editingId) {
            // Atualizar produto existente (PUT)
            const updateUrl = `http://127.0.0.1:8080/produtoComposto/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(produtocomp),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar produto com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Produto atualizado com sucesso!");
            console.log("Produto atualizado:", resultado);
        } else {
            // Criar novo produto (POST)
            const postProdUrl = `http://127.0.0.1:8080/produtoComposto/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(produtocomp),
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
document.querySelector('#excluir').addEventListener('click', async (event) => {
    event.preventDefault();
    const idprod = document.getElementById("idprod").value;
    console.log(idprod)
    const deleteprod = `http://127.0.0.1:8080/produtoComposto/deletar/${idprod}`

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
});


document.querySelector('#canceloperacao').addEventListener('click', async (event) => {
    event.preventDefault();
    resetForm();   
    
});

// Função para resetar o formulário
function resetForm() {
    document.getElementById('id').value = "";
    document.getElementById('id').removeAttribute('disabled'); // Reativa o campo ID
    document.getElementById('prodid').value = "";
    document.getElementById('nomeprod').value = "";
    document.getElementById('prodcomp').value = "";
    document.getElementById('nomeprodcomp').value = "";
    document.getElementById('prop').value = "";
    isEditing = false;
    editingId = null;
}


 