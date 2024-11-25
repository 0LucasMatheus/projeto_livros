async function carregarLivro() {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id"); // Pega o ID do livro da URL

  if (!id) {
    "nome": "usua",
    document.getElementById("detalhesLivro").innerHTML =
      "<p>ID do livro não encontrado.</p>";
    return;
  }

  const url = `http://localhost:8081/livro/${id}`; // Ajuste a rota conforme seu backend
  const resposta = await fetch(url, { method: "GET" });

  if (resposta.status === 200) {
    const livro = await resposta.json();
    document.getElementById("capa-livro").src =`https://covers.openlibrary.org/b/isbn/${livro.isbn}-M.jpg`;
    document.getElementById("nome-autor").textContent = autor.nome;
    document.getElementById("sinopse-livro").textContent =
      "A sinopse do livro...";


    document.getElementById("detalhesLivro").innerHTML = `
                    <h1>${livro.titulo}</h1>
                    <p><strong>Autor:</strong> ${livro.autor}</p>
                    <p><strong>ISBN:</strong> ${livro.isbn}</p>
                    <p><strong>Sinopse:</strong> ${livro.sinopse}</p>
                    <img src="https://covers.openlibrary.org/b/isbn/${livro.isbn}-L.jpg" alt="Capa do livro">
                `;
  } else {
    document.getElementById("detalhesLivro").innerHTML =
      "<p>Livro não encontrado.</p>";
  }
}

carregarLivro();
