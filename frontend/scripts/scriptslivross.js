async function colocarNoCombo(autores) {
    let combobox = ""; // Inicializa com uma string vazia

    for (let autor of autores) {
        combobox += `<option value="${autor.id}">${autor.nome}</option>`; // Concatena as opções
    }

    document.getElementById("id_autor").innerHTML = combobox; // Define o HTML do combobox
}

async function listAutores() {
    const url = "http://localhost:8083/autor";
    const dados = await fetch(url, { method: "GET" });

    if (dados.status === 200) {
        const autoresArray = await dados.json();

        document.getElementById("mensagem").innerHTML = "Olá, bombom!";
        colocarNoCombo(autoresArray); // Passa o array diretamente para `colocarNoCombo`
    } else {
        document.getElementById("mensagem").innerHTML = "Olá, falha!";
    }
}

async function addLivro(event) {
    event.preventDefault()
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
            document.getElementById("mensagem").innerHTML = "Olá, bombom2!";
        } else {
            alert('Erro ao cadastrar');
            document.getElementById("mensagem").innerHTML = "Olá, falha mortal!";
        }
   
}


listAutores();