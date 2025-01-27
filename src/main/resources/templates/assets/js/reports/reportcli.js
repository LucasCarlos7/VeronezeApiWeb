async function fetchData(apiUrl, tableId, colunaExcluida) {
  try {
    // Fazendo a requisição para a API
    const response = await fetch(apiUrl);
    const data = await response.json();

    // Verificando se os dados estão no formato esperado
    if (data && Array.isArray(data)) {
      renderTable(data, tableId, colunaExcluida);
    } else {
      console.error('Formato inesperado de dados:', data);
    }
  } catch (error) {
    console.error(`Erro ao buscar dados da API (${apiUrl}):`, error);
  }
}

function renderTable(data, tableId, colunaExcluida) {
  const table = document.getElementById(tableId);
  const thead = table.querySelector('thead tr');
  const tbody = table.querySelector('tbody');

  // Limpando a tabela antes de popular
  thead.innerHTML = '';
  tbody.innerHTML = '';

  if (data.length > 0) {
    // Criando os cabeçalhos dinamicamente, excluindo a coluna indesejada
    const headers = Object.keys(data[0]).filter(header => header !== colunaExcluida);
    headers.forEach(header => {
      const th = document.createElement('th');
      th.textContent = header;
      thead.appendChild(th);
    });

    // Criando as linhas e colunas dinamicamente
    data.forEach(item => {
      const row = document.createElement('tr');
      headers.forEach(header => {
        const cell = document.createElement('td');
        cell.textContent = item[header];
        row.appendChild(cell);
      });
      tbody.appendChild(row);
    });
  } else {
    // Caso não haja dados
    const noDataRow = document.createElement('tr');
    const noDataCell = document.createElement('td');
    noDataCell.colSpan = '100%';
    noDataCell.textContent = 'Nenhum dado disponível';
    noDataRow.appendChild(noDataCell);
    tbody.appendChild(noDataRow);
  }
}

// Exemplo de uso para diferentes tabelas
fetchData('http://127.0.0.1:8080/produtoComposto/listar', 'tabelaAPI1', 'id'); // Primeira tabela
fetchData('http://127.0.0.1:8080/estoque/saldo-produto/1', 'tabelaAPI2', 'idade'); // Segunda tabela
