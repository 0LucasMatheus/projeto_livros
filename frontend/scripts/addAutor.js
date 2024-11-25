document.getElementById("vivo").addEventListener("change", function () {
  const dataMorteContainer = document.getElementById("dataMorteContainer");
  const morteInput = document.getElementById("morte");

  if (this.checked) {
    dataMorteContainer.style.display = "none";
    morteInput.removeAttribute("required");
  } else {
    dataMorteContainer.style.display = "block";
    morteInput.setAttribute("required", true);
  }
});

async function addAutor() {
  const formhtml = document.querySelector("#formAutor");

  if (!formhtml.checkValidity()) {
    alert("Por favor, preencha todos os campos.");
    return;
  }
  const formData = new FormData(formhtml);
  const objetoAutor = Object.fromEntries(formData);

  const url = "http://localhost:8083/autor/add";
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(objetoAutor),
  };

  const result = await fetch(url, option);
  if (result.status === 201) {
    document.getElementById("mensagem").innerHTML = "cadastrado com sucesso!";
    limpacampos();
  } else {
    document.getElementById("mensagem").innerHTML = "erro ao cadastrar!";
  }
}

function limpacampos() {
  const Cnome = document.getElementById("nome");
  const Cbio = document.getElementById("bio");
  const Cnascimento = document.getElementById("nascimento");
  const Cmorte = document.getElementById("morte");
  const Colid = document.getElementById("olid");
  Cnome.value = "";
  Cbio.value = "";
  Cnascimento.value = "";
  Cmorte.value = "";
  Colid.value = "";
}
