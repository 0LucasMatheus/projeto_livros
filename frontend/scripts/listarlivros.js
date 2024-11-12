function clearLoading(){
    document.getElementById("loading").style.display = "none";
}

function ListarLivros(livros){
    let tab = ` <thead>     
        <th>Titulo</th>
        <th>autor</th>
        <th>capa</th>
    </thead>`;

    for(let livro of livros){
        tab += `
            <tr>
                <td>${livro.titulo}</td>
                <td>${livro.id_autor}</td>
                <td><img src="https://covers.openlibrary.org/b/isbn/${livro.isbn}-M.jpg"></td>
               </tr>
        `;
    }
    document.getElementById("livros").innerHTML = tab;
}

async function listAllProducts(){
    const url = "http://localhost:8081/livro";
    const dados = await fetch(url, {method: "GET"});
    if(dados.status === 200){
        const livros = await dados.json();
        if(livros){
            clearLoading();
        }
        ListarLivros(livros);
    }
}


listAllProducts();