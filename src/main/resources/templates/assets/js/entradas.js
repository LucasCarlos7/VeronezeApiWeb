


let isEditing = false; // Controla se está editando
let editingId = null;  // Armazena o ID  editado


// Buscar operacao pelo ID
document.querySelector('#buscar').addEventListener('click', async (event) => {
    event.preventDefault();
    

    const idoperacao = document.getElementById("id").value;
    if (!idoperacao) {
        alert("Por favor, insira o ID da Operação");
        return;
    }

    try {

        //Rota para modulo de entradas
        const rota = `http://127.0.0.1:8080/movimento-estoque/pesquisar/${idoperacao}`;
        const response = await fetch(rota);

        if (!response.ok) {
            throw new Error(`Opecação com ID ${idoperacao} não encontrado.`);
        }

        const operacao = await response.json();

        console.table(operacao);



        

        // Preenche o formulário com os dados do fornedor
        document.getElementById('id').value = operacao.id;
        document.getElementById('id').setAttribute('disabled', ''); // Desabilita o campo ID
        document.getElementById('local_estoque_entrada').value = operacao.localEstoqueId;
        document.getElementById('local_estoque_Saida').value = operacao.localEstoqueSaidaId;
        document.getElementById('idfornecedor').value = operacao.fornecedorId;
        
        const idfornecedor = document.getElementById("idfornecedor").value;
       //Rotas para o modulo de fornecedor para trazer o nome do fornecedor
       const rotafornecedor = `http://127.0.0.1:8080/fornecedor/pesquisar/${idfornecedor}`;
       const responsefonecedor = await fetch(rotafornecedor);
       if (!responsefonecedor.ok) {
           throw new Error(`Fornecedor com ID ${idfornecedor} não encontrado.`);
       }
       const fornecedor = await responsefonecedor.json();

       document.getElementById('nomefornecedor').value = fornecedor.nome;
       document.getElementById('valoroperacao').value = operacao.valorOperacao;
       document.querySelector(`input[name="statusoperacao"][value="${operacao.statusOperacao}"]`).checked = true;
       document.querySelector(`input[name="tipooperacao"][value="${operacao.tipoOperacao}"]`).checked = true;
       carregarDados();


 
  

        // Atualiza o estado para edição
        isEditing = true;
        editingId = operacao.id;
        

        
    } catch (error) {
        console.error("Erro ao buscar Funcionario:", error);
        alert(error.message);
    }
});


// Botão incluir
document.querySelector('#incluir').addEventListener('click', async (event) => {
    event.preventDefault();
    try {
        const rota = `http://127.0.0.1:8080/movimento-estoque/next-id`;
        const response = await fetch(rota);
        const nextIdmovi = parseInt(await response.json(), 10);
        
        document.getElementById('id').value = nextIdmovi;
        document.getElementById('id').setAttribute('disabled', '');
        } catch (error) {
            console.error("Erro ao buscar próximo ID:", error);
            alert("Erro ao obter o próximo ID do produto.");
            }
});





// Salvar (Criar ou Atualizar) 
document.querySelector('#salvar').addEventListener('click', async (event) => {
    event.preventDefault();

    const fornecedorId = document.getElementById("idfornecedor").value;
    const localEstoqueId = document.getElementById("local_estoque_entrada").value;
    const localEstoqueSaidaId = document.getElementById("local_estoque_Saida").value;
    const valorOperacao = document.getElementById("valoroperacao").value;
    const tipoOperacao = document.querySelector('input[name="tipooperacao"]:checked')?.value;
    const statusOperacao = document.querySelector('input[name="statusoperacao"]:checked')?.value;
    

    const operacao = {
        localEstoqueId: localEstoqueId,
        localEstoqueSaidaId: localEstoqueSaidaId,
        fornecedorId: fornecedorId,
        valorOperacao: valorOperacao,
        tipoOperacao: tipoOperacao,
        statusOperacao: statusOperacao,

    };

    try {
        if (isEditing && editingId) {
            // Atualizar operacao existente (PUT)

            
            const updateUrl = `http://127.0.0.1:8080/movimento-estoque/atualizar/${editingId}`;
            const response = await fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(operacao),
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar Operacao com ID ${editingId}.`);
            }

            const resultado = await response.json();
            alert("Operação atualizada com sucesso!");  
            console.log("Operação atualizada:", resultado);
        } else {
            // Criar novo (POST)
            const postProdUrl = `http://127.0.0.1:8080/movimento-estoque/adicionar`;
            const response = await fetch(postProdUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(operacao),
            });

            if (!response.ok) {
                throw new Error(`Erro ao criar funcionario: ${response.status}`);
            }

            const resultado = await response.json();
            alert("Operação criada com sucesso!");
            console.log("Operação criada:", resultado);
        }

        resetForm();
    } catch (error) {
        console.error("Erro ao salvar Operação:", error);
        alert(error.message);
    }






});


// Cancelar operação
document.querySelector('#canceloperacao').addEventListener('click', (event) => {
    event.preventDefault();
    resetForm();
});



// Função para resetar o formulário
function resetForm() {
    document.getElementById('id').value = "";
    document.getElementById('id').removeAttribute('disabled'); // habilita o campo ID
    document.getElementById('local_estoque_entrada').value = "";
    document.getElementById('local_estoque_Saida').value = "";
    document.getElementById('idfornecedor').value = "";
    document.getElementById('nomefornecedor').value = ""; 
    document.getElementById('valoroperacao').value = "";
    isEditing = false;
    editingId = null;
}



let operacaoAtiva = false; // Controla se uma operação está em andamento

// Função para carregar os dados do GET na tabela
async function carregarDados() {
  try {
    const idoperação = document.getElementById("id").value;
    const response = await fetch(`http://127.0.0.1:8080/itens-movimento/pesquisar/${idoperação}`);
    const data = await response.json();
    console.log(data);
    if (Array.isArray(data)) {
    preencherTabela(data);
    } else {
      alert("Os dados recebidos não estão no formato esperado!");
    }
  } catch (error) {
    console.error("Erro ao buscar os dados:", error);
    alert("Erro ao buscar os dados da API. Verifique o console.");
  }
}

    // Função para preencher a tabela com dados recebidos do GET
function preencherTabela(dados) {
    const tabela = document.getElementById('tabelaMovimento').querySelector('tbody');
    tabela.innerHTML = ''; // Limpar a tabela antes de popular
  
    dados.forEach(item => {
        const novaLinha = document.createElement('tr');
  
        novaLinha.innerHTML = `
            <td><input type="number" value="${item.id}" readonly></td>
            <td><input type="number" value="${item.produtoId}" readonly></td>
            <td><input type="number" value="${item.movimentoEstoqueId}" readonly></td>
            <td><input type="number" value="${item.quantidade}" readonly></td>
            <td>
              <button class="btnExcluir">Excluir</button>
            </td>
          `;
  
            tabela.appendChild(novaLinha);
        });
  
        // Os botões de excluir estarão habilitados por padrão
      }

      document.querySelector('#btnAdicionar').addEventListener('click', (event) => {
        event.preventDefault();
        adicionarLinha();
    });

    // Função para adicionar uma nova linha editável
    function adicionarLinha() {
        const tabela = document.getElementById('tabelaMovimento').querySelector('tbody');
        const novaLinha = document.createElement('tr');
  
        novaLinha.innerHTML = `
          <td><input type="number" placeholder="ITEM"></td>
          <td><input type="number" placeholder="Produto ID"></td>
          <td><input type="number" placeholder="Movimento Estoque ID"></td>
          <td><input type="number" placeholder="Quantidade"></td>
          <td>
            <button class="btnExcluir" disabled>Excluir</button>
          </td>
        `;
  
        tabela.appendChild(novaLinha);
  
        // Habilitar os botões de confirmar e cancelar
        document.getElementById('btnConfirmar').disabled = false;
        document.getElementById('btnCancelar').disabled = false;
  
        operacaoAtiva = true;
      }
  
    // Cancelar itens entrada
    document.querySelector('#btnCancelar').addEventListener('click', (event) => {
        event.preventDefault();
        cancelarOperacao();
    });


     // Função para cancelar a operação
    function cancelarOperacao() {
        if (operacaoAtiva && confirm("Deseja cancelar todas as alterações não confirmadas?")) {
            // Remove todas as linhas adicionadas manualmente
            carregarDados(); // Recarregar os dados do GET para restaurar o estado original
      
            // Desabilitar os botões de confirmar e cancelar
            document.getElementById('btnConfirmar').disabled = true;
            document.getElementById('btnCancelar').disabled = true;
      
            operacaoAtiva = false;
            }
        }


    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('btnExcluir')) {
              event.preventDefault();
              excluirRegistro(event.target); // Passa o botão clicado
        }
    });
          
        // Função para excluir uma linha (com DELETE na API)
        async function excluirRegistro(botao) {
        const linha = botao.parentElement.parentElement;
        const inputs = linha.querySelectorAll('input');
          
        if (inputs.length < 2) {
            alert("Erro ao encontrar os campos de Produto ID ou Movimento Estoque ID na linha.");
            return;
        }
          
        const produtoId = inputs[0].value;
        const movimentoEstoqueId = inputs[2].value;
          
        if (!produtoId || !movimentoEstoqueId) {
            alert("Produto ID ou Movimento Estoque ID está vazio. Verifique os dados.");
             return;
         }
          
         if (confirm(`Deseja excluir o registro do Produto ID ${produtoId}?`)) {
            try {
                const deleteUrl = `http://127.0.0.1:8080/itens-movimento/deletar/${produtoId}`; // Ajuste o endpoint conforme sua API
                const response = await fetch(deleteUrl, { method: "DELETE" });
          
                if (response.ok) {
                  alert(`Registro Produto ID ${produtoId} excluído com sucesso!`);
                  linha.remove(); // Remove a linha da tabela
                } else {
                  alert(`Erro ao excluir o registro: ${response.statusText}`);
                }
            } catch (error) {
                console.error("Erro ao excluir o registro:", error);
                alert("Erro ao excluir o registro. Verifique o console.");
              }
            }
          }



// Função para gerar JSON apenas da última linha da tabela
function gerarJSON() {
    const tabela = document.getElementById('tabelaMovimento').querySelector('tbody');
    const linhas = tabela.querySelectorAll('tr');
  
    // Verifica se há pelo menos uma linha na tabela
    if (linhas.length === 0) {
      alert("Nenhuma linha disponível na tabela!");
      return null;
    }
  
    // Seleciona a última linha
    const ultimaLinha = linhas[linhas.length - 1];
    const inputs = ultimaLinha.querySelectorAll('input');
  
    // Certifica-se de que há pelo menos 3 inputs na linha
    if (inputs.length >= 3) {
      const produtoId = inputs[1].value.trim();
      const movimentoEstoqueId = inputs[2].value.trim();
      const quantidade = inputs[3].value.trim();
  
      // Validação: Certifica-se de que todos os campos têm valores válidos
      if (produtoId && movimentoEstoqueId && quantidade && !isNaN(quantidade)) {
        return {
          produtoId: parseInt(produtoId, 10),
          MovimentoEstoqueId: parseInt(movimentoEstoqueId, 10),
          quantidade: parseFloat(quantidade),
        };
      } else {
        alert("Preencha todos os campos da última linha corretamente!");
        return null;
      }
    } else {
      alert("A última linha está incompleta!");
      return null;
    }
  }
  
  // Função para enviar os dados via POST
  async function enviarDados() {
    const jsonData = gerarJSON(); // Obtém os dados da última linha
    console.log(JSON.stringify(jsonData))
    if (!jsonData) {
      return; // Interrompe se não houver dados válidos
    }
  
    try {
      const response = await fetch(`http://localhost:8080/itens-movimento/adicionar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jsonData), // Envia apenas os dados da última linha
      });
  
      if (response.ok) {
        alert("Dados enviados com sucesso!");
        document.getElementById('btnConfirmar').disabled = true;
        document.getElementById('btnCancelar').disabled = true;
        carregarDados(); // Atualiza a tabela com os dados do GET
      } else {
        alert(`Erro ao enviar os dados: ${response.statusText}`);
      }
    } catch (error) {
      console.error("Erro ao enviar os dados:", error);
      alert("Erro ao enviar os dados. Verifique o console.");
    }
  }
  
  // Adicionar evento ao botão Confirmar
  document.querySelector('#btnConfirmar').addEventListener('click', (event) => {
    event.preventDefault();
    enviarDados();
  });
   