document.addEventListener("DOMContentLoaded", async () => {
  // Pega o parâmetro 'id' da URL
  const urlParams = new URLSearchParams(window.location.search);
  const livroId = urlParams.get("id"); // Pega o ID do livro da URL

  // Função para carregar os detalhes do livro
  async function carregarDetalhesDoLivro(id) {
    try {
      const response = await fetch(`http://localhost:8081/livro/${id}`);
      const livro = await response.json();

      // Preenche os dados do livro na página
      document.getElementById("capa-livro").src = `https://covers.openlibrary.org/b/isbn/${livro.isbn}-M.jpg`;
document.getElementById("capa-livro").onerror = () => {
  document.getElementById("capa-livro").src = "imagens/livropadrao.png";
      document.getElementById(
        "genero-livro"
      ).innerText = `Gênero: ${livro.genero}`;
      document.getElementById(
        "capitulos-livro"
      ).innerText = `Capítulos: ${livro.capitulos}`;
      document.getElementById(
        "sinopse-livro"
      ).innerText = `Sinopse: ${livro.sinopse}`;

      // Preenche as informações do autor
      document.getElementById("foto-autor").src = livro.autor.foto; // Assumindo que o livro tem uma propriedade 'autor' com a foto
      document.getElementById("nome-autor").innerText = livro.autor.nome; // Assumindo que o livro tem uma propriedade 'autor' com o nome

      // Verifica se o usuário é administrador
      const user = JSON.parse(localStorage.getItem("user"));
      if (user && user.role === "ADM") {
        // Mostra os botões de editar e excluir se for ADM
        const actionsDiv = document.querySelector(".actions");
        actionsDiv.innerHTML += `
          <button id="editar-livro" class="btn" onclick="editarLivro(${livro.id})">Editar Livro</button>
          <button id="excluir-livro" class="btn" onclick="excluirLivro(${livro.id})">Excluir Livro</button>
        `;
      }
    } catch (error) {
      console.error("Erro ao carregar os detalhes do livro:", error);
    }
  }

  // Carrega os detalhes do livro com o ID da URL
  carregarDetalhesDoLivro(livroId);

  // Função para editar o livro
  function editarLivro(id) {
    window.location.href = `editarLivro.html?id=${id}`; // Redireciona para a página de edição
  }

  // Função para excluir o livro
  async function excluirLivro(id) {
    const confirmDelete = confirm("Tem certeza que deseja excluir este livro?");
    if (confirmDelete) {
      try {
        const response = await fetch(`http://localhost:8081/livro/${id}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.ok) {
          alert("Livro excluído com sucesso!");
          window.location.href = "index.html"; // Redireciona para a página inicial
        } else {
          alert("Erro ao excluir o livro.");
        }
      } catch (error) {
        console.error("Erro ao excluir o livro:", error);
      }
    }
  }
});
