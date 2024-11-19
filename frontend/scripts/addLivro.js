document.getElementById("formLivro").addEventListener("submit", function(event) {
    event.preventDefault();
    addlivro();
});

async function carregarAutoresNoCombo() {
    const url = "http://localhost:8083/autor"; // Endpoint para buscar autores
    const dados = await fetch(url, { method: "GET" });
    if (dados.status === 200) {
            document.getElementById("mensagem").innerHTML = "Olá, criatura!";
        } else {
            document.getElementById("mensagem").innerHTML = "Olá, mingming!";
        }
        const autores = await dados.json();
        let comboAutores = document.getElementById("autor");

        // Limpar opções anteriores (caso já existam)
        comboAutores.innerHTML = '<option value="" selected disabled>Selecione um autor...</option>';

        // Preencher o combobox com os autores
        autores.forEach(autor => {
            let option = document.createElement("option");
            option.value = autor.id; // O valor será o ID do autor
            option.textContent = autor.id; // O texto exibido será o nome do autor
            comboAutores.appendChild(option);
        })};

async function addlivro() {
    const formhtml = document.querySelector("#formLivro");
    const formData = new FormData(formhtml);
    const objetoLivro = Object.fromEntries(formData);

    
    const url = "http://localhost:8081/livro/add";
    const option = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(objetoLivro)
    };

        const result = await fetch(url, option);
        if (result.status === 201) {
            alert('Cadastrado com sucesso');
            document.getElementById("mensagem").innerHTML = "Olá, bombom!";
        } else {
            alert('Erro ao cadastrar');
            document.getElementById("mensagem").innerHTML = "Olá, falha!";
        }
   
}

carregarAutoresNoCombo();
