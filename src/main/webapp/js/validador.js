function validarFormLivro() {
  // Campos
  const inputTitulo = document.getElementById("titulo");
  const inputAno = document.getElementById("ano_publicacao");
  const selectGenero = document.getElementById("genero");
  const selectAutor = document.getElementById("idAutor");

  // Spans de erro
  const erroTitulo = document.getElementById("erro-titulo");
  const erroAno = document.getElementById("erro-ano");
  const erroGenero = document.getElementById("erro-genero");
  const erroAutor = document.getElementById("erro-autor");

  // Limpar erros anteriores
  const spans = document.querySelectorAll(".msg-erro");
  spans.forEach((span) => (span.textContent = ""));

  let formularioValido = true;

  // Validação Título
  if (inputTitulo.value.trim() === "") {
    erroTitulo.textContent = "O título é obrigatório *";
    inputTitulo.classList.add("campo-erro");
    formularioValido = false;
  }

  // Validação Ano
  if (inputAno.value.trim() === "") {
    erroAno.textContent = "Informe o ano de publicação *";
    inputAno.classList.add("campo-erro");
    formularioValido = false;
  }

  // Validação Gênero
  if (selectGenero.value.trim() === "") {
    erroGenero.textContent = "Selecione um gênero *";
    selectGenero.classList.add("campo-erro");
    formularioValido = false;
  }

  // Validação Autor
  if (selectAutor.value === "0" || selectAutor.value === "") {
    erroAutor.textContent = "Selecione um autor *";
    selectAutor.classList.add("campo-erro");
    formularioValido = false;
  }

  return formularioValido;
}

function validarFormAutor() {
  const inputNome = document.getElementById("nome");
  const erroNome = document.getElementById("erro-nome");

  if (inputNome.value.trim() === "") {
    erroNome.textContent = "O nome do autor é obrigatório *";
    inputNome.classList.add("campo-erro");
    return false;
  }

  return true;
}

// Limpar erros de todos os formulários
const campos = document.querySelectorAll("input, select");
campos.forEach((campo) => {
  campo.addEventListener("input", () => {
    campo.classList.remove("campo-erro");
    const spanErro = document.getElementById(`erro-${campo.id}`);
    if (spanErro) spanErro.textContent = "";
  });
});
