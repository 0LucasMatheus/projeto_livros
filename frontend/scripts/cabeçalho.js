function popperfil() {
  const popUp = document.getElementById("profile-popup");
  if (popUp.style.display === "block") {
    popUp.style.display = "none";
  } else {
    popUp.style.display = "block";
  }
}

document.addEventListener("DOMContentLoaded", () => {
  const popUp = document.getElementById("profile-popup");
  const user = JSON.parse(localStorage.getItem("user"));
  const imagemprofile = document.getElementById("imagemprofile");

  if (user) {
    imagemprofile.src = "imagens/logado.png";
    if (user.role === "ADM") {
      // Caso o usuário seja administrador
      popUp.innerHTML = `
      <h5>${user.nome}</h5>
          <a href="lista.html">
            <button class="popup-btn">Minha lista</button>
          </a>
          <a href="adicionarlivro.html">
          <button>adicionar novo livro</button>
        </a>
          
        <a href="adicionarautor.html">
           <button>adicionar novo autor</button>
         </a>

          <button class="popup-btn" onclick="logout()">Sair</button>
          <button class="popup-btn" onclick="apagarConta()">APAGAR CONTA</button>
      `;
    } else {
      // Caso o usuário seja normal
      popUp.innerHTML = `

      <h5>${user.nome}</h5>
          <a href="lista.html">
            <button class="popup-btn">Minha lista</button>
          </a>
          <button class="popup-btn" onclick="logout()">Sair</button>
          <button class="popup-btn" onclick="apagarConta()">APAGAR CONTA</button>
      `;
    }
  } else {
    // Caso não haja usuário logado
    popUp.innerHTML = `
        <a href="cadastro.html">
          <button class="popup-btn">Cadastrar</button>
        </a>
        <a href="login.html">
          <button class="popup-btn">Logar</button>
        </a>`;
  }
});


document.addEventListener("click", (event) => {
  const popUp = document.getElementById("profile-popup");
  const profileBtn = document.querySelector(".profile-btn");

  if (!popUp.contains(event.target) && !profileBtn.contains(event.target)) {
    popUp.style.display = "none";
  }
});

async function addUser() {
  const formhtml = document.querySelector("#formUsuario");


  if (!formhtml.checkValidity()) {
    alert("Por favor, preencha todos os campos.");
    return;
  }

  const formData = new FormData(formhtml);
  const objetoUser = Object.fromEntries(formData);

  const url = "http://localhost:8082/usuario/add";
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(objetoUser),
  };

  try {
    const result = await fetch(url, option);

    if (result.status === 201) {
      const { id } = await result.json();
      await autoLogin(id);
    } else {
      alert("Erro no cadastro. Tente novamente.");
    }
  } catch (error) {
    console.error("Erro ao cadastrar:", error);
  }
}

async function autoLogin(id) {
  const url = `http://localhost:8082/usuario/${id}`; // Endpoint para buscar o usuário
  const option = {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  };

  try {
    const result = await fetch(url, option);

    if (result.status === 200) {
      const userData = await result.json(); // Dados do usuário retornados
      localStorage.setItem("user", JSON.stringify(userData)); // Salva no localStorage
      alert("Usuário cadastrado e logado com sucesso!");
      window.location.href = "index.html"; // Redireciona para a página inicial
    } else {
      alert("Erro ao logar automaticamente. Faça login manualmente.");
      window.location.href = "login.html"; // Redireciona para a página de login
    }
  } catch (error) {
    console.error("Erro ao realizar login automático:", error);
  }
}


async function login() {
  const formhtml = document.querySelector("#formUsuario");


  if (!formhtml.checkValidity()) {
    alert("Por favor, preencha todos os campos.");
    return;
  }

  const formData = new FormData(formhtml);
  const objetoUser = Object.fromEntries(formData);

  const url = "http://localhost:8082/usuario/login"; // Endpoint para login
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(objetoUser),
  };

  try {
    const result = await fetch(url, option);

    if (result.status === 200) {
      const userData = await result.json(); 

      localStorage.setItem("user", JSON.stringify(userData)); 
      window.location.href = document.referrer; 
    } else if (result.status === 403) {
      alert("Senha ou nickname incorretos.");
    } else if (result.status === 404) {
      alert("Usuário não encontrado.");
    } else {
      alert("Erro no login. Tente novamente.");
    }
  } catch (error) {
    console.error("Erro ao tentar fazer login:", error);
  }
}


function logout() {
  localStorage.removeItem("user");
  window.location.reload(); // Recarrega a página para atualizar o cabeçalho
}

async function apagarConta() {
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user || !user.id) {
    alert("Usuário não encontrado. Por favor, faça login novamente.");
    return;
  }

  // Exibe confirmação
  const confirmacao = confirm(
    "Tem certeza de que deseja apagar sua conta? Essa ação não pode ser desfeita."
  );
  if (!confirmacao) {
    alert("Ação cancelada.");
    return;
  }

  const url = `http://localhost:8082/usuario/delete/${user.id}`; // Substitui o ? pelo ID do usuário

  const option = {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
  };

  try {
    const result = await fetch(url, option);

    if (result.status === 204) {
      alert("Sua conta foi deletada com sucesso.");
      localStorage.removeItem("user"); // Remove dados do usuário do localStorage
      window.location.href = "index.html"; // Redireciona para a página inicial
    } else {
      alert("Erro ao deletar a conta. Tente novamente.");
    }
  } catch (error) {
    console.error("Erro ao tentar deletar a conta:", error);
    alert("Ocorreu um erro inesperado. Tente novamente mais tarde.");
  }
}
