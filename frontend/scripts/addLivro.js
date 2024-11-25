async function carregarAutoresNoCombo() {
    const url = "http://localhost:8083/autor"; 
    const dados = await fetch(url, { method: "GET" });
    if (dados.status === 200) {
            document.getElementById("mensagem").innerHTML = "autores carregados!";
        } else {
            document.getElementById("mensagem").innerHTML = "erro ao carregard autores!";
            return;
        }
        const autores = await dados.json();
        let comboAutores = document.getElementById("id_autor");
        let comboAutorescarregado = `
        <option value="" selected disabled>Selecione um autor...</option>
    `;
        for(let autor of autores){
            comboAutorescarregado += `
                <option value="${autor.id}">${autor.nome}</option>
            `;
        }
        comboAutores.innerHTML=comboAutorescarregado;
    
};

async function addLivro() {
    const formhtml = document.querySelector("#formLivro");


  if (!formhtml.checkValidity()) {
    alert("Por favor, preencha todos os campos.");
    return;
  }

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
            document.getElementById("mensagem").innerHTML = "cadastrado com sucesso!";
        } else {
            document.getElementById("mensagem").innerHTML = "erro ao cadastrar livro!";
        }
   
}

carregarAutoresNoCombo();
