// Código para carregar os dados da última venda
const ultimaVenda = JSON.parse(localStorage.getItem('ultimaVenda'));

const idvenda = document.getElementById('idVenda').value = ultimaVenda.id;

async function venda() {
  const rota = `http://127.0.0.1:8080/venda/pesquisar/${idvenda}`;
  const response = await fetch(rota);
  const cab_venda = await response.json();
  console.table(cab_venda);
  document.getElementById('nomeCliente').value = cab_venda.nomeCliente;
  document.getElementById('cpfCnpj').value = cab_venda.cpf_cnpj;
  document.getElementById('statusVenda').textContent = cab_venda.statusVenda;
  document.getElementById('dataVenda').textContent = cab_venda.dataVenda;
  document.getElementById('precoini').value = (cab_venda.totalOrcamentoInicial).toFixed(2);
  document.getElementById('desconto').value = cab_venda.desconto;
  document.getElementById('precofinal').value = (cab_venda.totalOrcamentoFinal).toFixed(2);
}
venda()



let operacaoAtiva = false; // Controla se uma operação está em andamento

// Função para carregar os dados do GET na tabela
async function carregarDados() {
  try {
    const idvenda = document.getElementById("idVenda").value;
    const response = await fetch(`http://127.0.0.1:8080/itens-venda/pesquisar/${idvenda}`);
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

carregarDados()


// Função para preencher a tabela com dados recebidos do GET
function preencherTabela(dados) {
  const tabela = document.getElementById('tabelaMovimento').querySelector('tbody');
  tabela.innerHTML = ''; // Limpar a tabela antes de popular

  dados.forEach(item => {
    const novaLinha = document.createElement('tr');

    novaLinha.innerHTML = `
                <td><input type="text" value="${item.item}" class="itemvenda" readonly></td>
                <td><input type="text" value="${item.produtoId}" readonly></td>
                <td><input type="text" value="${item.quantidade}" readonly></td>
                <td><input type="text" value="${item.valorUnitarioProduto}" readonly></td>
                <td><input type="text" value="${item.valorTotalProduto}" readonly></td>
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
          <td><input type="text" placeholder="ITEM"></td>
          <td><input type="text" placeholder="Produto ID"></td>
          <td><input type="text" placeholder="Quantidade"></td>
          <td><input type="text" placeholder="Valor Uni."></td>
          <td><input type="text" placeholder="Valor Tot."></td>
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


/*
// Função para excluir uma linha (com DELETE na API)
async function excluirRegistro(botao) {
    const linha = botao.parentElement.parentElement;
    const inputs = linha.querySelectorAll('input');

    if (inputs.length < 2) {
        alert("Erro ao encontrar os campos de Produto ID na linha.");
        return;
    }

    const produtoId = inputs[0].value;
    const movimentoEstoqueId = inputs[2].value;

    if (!produtoId || !movimentoEstoqueId) {
        alert("Produto ID está vazio. Verifique os dados.");
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
*/



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
    const vendaId = idvenda;
    const produtoId = inputs[1].value.trim();
    const quantidade = inputs[2].value.trim();

    // Validação: Certifica-se de que todos os campos têm valores válidos
    if (produtoId && quantidade && !isNaN(quantidade)) {
      return {
        vendaId: parseInt(vendaId, 10),
        produtoId: parseInt(produtoId, 10),
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
    const response = await fetch(`http://localhost:8080/itens-venda/adicionar`, {
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

// Salvar (Criar ou Atualizar) cliente
document.querySelector('#statusvenda').addEventListener('click', async (event) => {
  event.preventDefault();


  const rota = `http://127.0.0.1:8080/venda/pesquisar/${idvenda}`;
  const response = await fetch(rota);
  const cab_venda = await response.json();
  console.log(cab_venda)

  const idvendaput = document.getElementById("idVenda").value;
  const clienteId = cab_venda.clienteId;
  const localestoqueId = cab_venda.localEstoqueId;
  const desconto = cab_venda.desconto;


  const venda = {
    clienteId: clienteId,
    localestoqueId: localestoqueId,
    desconto: desconto,
  };

  try {
    const updateUrl = `http://127.0.0.1:8080/venda/finalizar-cancelar-venda/${idvendaput}`;
    const response = await fetch(updateUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(venda),
    });
    const resultado = await response.json();
    console.log("Venda atualizado:", resultado);

  } catch (error) {
    console.error("Erro ao Mudar status da venda:", error);
    alert(error.message);
  }

  recarregarPagina()
});

function recarregarPagina() {
  location.reload();
}
