async function clearLoading(){
    document.getElementById("loading").style.display = "none";
}

function ListarLivros(livros, autores){
    let tab = ` <thead>     
        <th>Titulo</th>
        <th>Autor</th>
        <th>Capa</th>
        <th>Foto do Autor</th>
    </thead>`;

    for(let livro of livros){
        let autor = autores[livro.id_autor];  // Obter o autor pelo id_autor
        tab += `
            <tr>
                <td>${livro.titulo}</td>
                <td>${autor.nome}</td>
                <td><img src="https://covers.openlibrary.org/b/isbn/${livro.isbn}-M.jpg"></td>
                <td><img src="https://covers.openlibrary.org/a/olid/${autor.olid}-M.jpg"</td>
            </tr>
        `;
    }
    document.getElementById("livros").innerHTML = tab;
}

async function listAllProducts(autores){
    const url = "http://localhost:8081/livro";
    const dados = await fetch(url, {method: "GET"});
    if(dados.status === 200){
        const livros = await dados.json();
        if(livros){
            clearLoading();
            ListarLivros(livros, autores);
        }
    }
}

async function listAutores(){
    const url = "http://localhost:8083/autor";
    const dados = await fetch(url, {method: "GET"});
    if(dados.status === 200){
        const autoresArray = await dados.json();
        let autores = {};
        for(let autor of autoresArray){
            autores[autor.id] = autor;  // Armazenar o autor pelo id para acesso rápido
        }
        document.getElementById("mensagem").innerHTML = "Olá, bombom!";
        listAllProducts(autores);  // Passar o mapa de autores para listAllProducts
    } else {
        document.getElementById("mensagem").innerHTML = "Olá, falha!";
    }
}

// Iniciar carregamento dos autores e produtos
listAutores();
