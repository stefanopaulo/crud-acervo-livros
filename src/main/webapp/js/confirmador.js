// Guarda o form que disparou o modal
let formularioParaEnviar = null;

function configurarExclusao() {
  const modal = document.getElementById("modalConfirmacao");
  const btnCancelar = document.getElementById("btnCancelar");
  const btnConfirmar = document.getElementById("btnConfirmar");

  // Seleciona todos os formulários de exclusão
  const formsExcluir = document.querySelectorAll('form[action="deletarLivro"]');

  formsExcluir.forEach((form) => {
    form.onsubmit = function (event) {
      event.preventDefault(); // Impede o envio imediato
      formularioParaEnviar = form; // Guarda qual formulário é
      modal.style.display = "block"; // Abre o modal
    };
  });

  // Botão Cancelar fecha o modal
  btnCancelar.onclick = function () {
    modal.style.display = "none";
    formularioParaEnviar = null;
  };

  // Botão Confirmar envia o formulário guardado
  btnConfirmar.onclick = function () {
    if (formularioParaEnviar) {
      btnConfirmar.disabled = true;
      btnConfirmar.innerText = "Excluindo...";
      formularioParaEnviar.submit();
    }
  };
}

// Chame essa função quando a página carregar
document.addEventListener("DOMContentLoaded", configurarExclusao);
