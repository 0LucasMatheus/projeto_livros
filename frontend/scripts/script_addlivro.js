document.getElementById("formlivro").addEventListener("submit", function(event) {
    event.preventDefault();
    addlivro();
});


async function addlivro() {
    const formhtml = document.querySelector("#formlivro");
    const formData = new FormData(formhtml);
    const objetolivro = Object.fromEntries(formData);

    
    const url = "http://localhost:8081/livro/add";
    const option = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(objetolivro)
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
