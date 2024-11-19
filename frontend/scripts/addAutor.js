document.getElementById("formAutor").addEventListener("submit", function(event) {
    event.preventDefault();
    addAutor();
});



document.getElementById("vivo").addEventListener("change", function() {
    const dataMorteContainer = document.getElementById("dataMorteContainer");
    const dataMorteInput = document.getElementById("dataMorte");

    // Se o checkbox está marcado, esconder campo "Data de Morte" e remover obrigatoriedade
    if (this.checked) {
        dataMorteContainer.style.display = "none";
        dataMorteInput.removeAttribute("required");
    } else {
        // Se o checkbox está desmarcado, mostrar campo "Data de Morte" e torná-lo obrigatório
        dataMorteContainer.style.display = "block";
        dataMorteInput.setAttribute("required", "required");
    }
});

async function addAutor() {
    const formhtml = document.querySelector("#formAutor");
    const formData = new FormData(formhtml);
    const objetoAutor = Object.fromEntries(formData);

    
    const url = "http://localhost:8083/autor/add";
    const option = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(objetoAutor)
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
