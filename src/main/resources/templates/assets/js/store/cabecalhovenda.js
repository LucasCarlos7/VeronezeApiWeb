

const clienteIdInput = document.getElementById("idcliente");
const clienteNomeInput = document.getElementById("nomecliente");

// Adicionar evento para detectar mudanças no campo de ID do cliente
clienteIdInput.addEventListener("input", async function () {
  const clienteId = this.value.trim(); // Obtém o valor digitado no campo

  // Validar se o ID não está vazio
  if (clienteId) {
    try {
      // Faz a requisição para a API
      const response = await fetch(`http://127.0.0.1:8080/cliente/pesquisar/${clienteId}`); // Substitua pela URL da sua API

      if (response.ok) {
        const data = await response.json();
        clienteNomeInput.value = data.nome || "Nome não encontrado";
        console.log(clienteNomeInput.value)
      } else {
        clienteNomeInput.value = "Cliente não encontrado";
      }
    } catch (error) {
      console.error("Erro ao buscar o cliente:", error);
      clienteNomeInput.value = "Erro ao buscar cliente";
    }
  } else {
    clienteNomeInput.value = ""; // Limpa o campo se o ID for apagado
  }
});

const EstoqueIdInput = document.getElementById("idlocalestoque");
const EstoqueNomeInput = document.getElementById("nomelocalestoque");

// Adicionar evento para detectar mudanças no campo de ID do cliente
EstoqueIdInput.addEventListener("input", async function () {
  const estoqueId = this.value.trim(); // Obtém o valor digitado no campo

  // Validar se o ID não está vazio
  if (estoqueId) {
    try {
      // Faz a requisição para a API
      const response = await fetch(`http://127.0.0.1:8080/local-estoque/pesquisar/${estoqueId}`); // Substitua pela URL da sua API

      if (response.ok) {
        const data = await response.json();
        EstoqueNomeInput.value = data.nome || "Nome não encontrado";
        console.log(EstoqueNomeInput.value)
      } else {
        EstoqueNomeInput.value = "Estoque não encontrado";
      }
    } catch (error) {
      console.error("Erro ao buscar o Estoque:", error);
      EstoqueNomeInput.value = "Erro ao buscar Estoque";
    }
  } else {
    EstoqueNomeInput.value = ""; // Limpa o campo se o ID for apagado
  }
});

// Salvar cabeçalho da venda

document.querySelector('#confirmar').addEventListener('click', async (event) => {
  event.preventDefault();

  const clienteId = document.getElementById("idcliente").value;
  const localestoqueId = document.getElementById("idlocalestoque").value;
  const desc_ini = document.getElementById("desc").value;

  // Validação de campos obrigatórios
  if (!clienteId || !localestoqueId) {
    alert("Por favor, preencha todos os campos antes de continuar a venda.");
    return;
  }

  // Objeto do cabeçalho da venda
  const cabecalhovenda = {
    clienteId: clienteId,
    localestoqueId: localestoqueId,
    desconto: desc_ini,
  };

  try {
    // Enviar a requisição para criar a venda (POST)
    const postProdUrl = `http://127.0.0.1:8080/venda/adicionar`;
    const response = await fetch(postProdUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(cabecalhovenda),
    });

    if (!response.ok) {
      throw new Error(`Erro ao criar venda: ${response.status}`);
    }

    const resultado = await response.json();

    // Exibir o resultado no console
    console.log("Venda criada:", resultado);

    // Salvar os dados no LocalStorage para usar em outra página
    localStorage.setItem('ultimaVenda', JSON.stringify(resultado));
    alert("Venda criada com sucesso!");

    // Limpar o formulário após salvar
    resetForm();



  } catch (error) {
    console.error("Erro ao salvar Venda:", error);
    alert(error.message);
  }


});

// Função para resetar o formulário
function resetForm() {
  document.getElementById('idcliente').value = "";
  document.getElementById('idcliente').removeAttribute('disabled'); // Reativa o campo ID
  document.getElementById('nomecliente').value = "";
  document.getElementById('idlocalestoque').value = "";
  document.getElementById('nomelocalestoque').value = "";
  document.getElementById('desc').value = "";
}

document.querySelector('#mudarpagina').addEventListener('click', async (event) => {
  event.preventDefault();
  window.location.replace("../../../pages/modules/store/balcao.html");
});