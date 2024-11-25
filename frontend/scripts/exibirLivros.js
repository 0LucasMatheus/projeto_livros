document.addEventListener("DOMContentLoaded", async () => {
  const bookGrid = document.getElementById("bookGrid");

  try {
    // Fazendo a requisição para o endpoint
    const response = await fetch("http://localhost:8081/livro");
    if (!response.ok) throw new Error("Erro ao buscar livros.");
    const livros = await response.json();

    // Preenchendo a tabela dinamicamente
    livros.forEach((livro) => {
      const bookItem = document.createElement("div");
      bookItem.classList.add("book-item");

      bookItem.innerHTML = `
      
        <img 
    class="book-cover" 
    src="https://covers.openlibrary.org/b/isbn/${livro.isbn}-M.jpg" 
    alt="imagens/livropadrao.png"
    onerror="this.onerror=null;this.src='imagens/livropadrao.png';"
onclick="window.location.href='livro.html?id=${livro.id}'"
  /> 
  <div class="book-title" title="${livro.titulo}">${livro.titulo}</div>
`;

      bookGrid.appendChild(bookItem);
    });
  } catch (error) {
    console.error(error);
    bookGrid.innerHTML = "<p>Erro ao carregar livros.</p>";
  }
});
