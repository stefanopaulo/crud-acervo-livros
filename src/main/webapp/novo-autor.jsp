<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="includes/header.jsp" />

<main>
  <div class="formCadAutor">
    <h2>Cadastrar autor</h2>

    <form
      action="cadastrarAutores"
      method="post"
      onsubmit="return validarFormAutor();"
    >
      <input
        type="text"
        name="nome"
        id="nome"
        placeholder="Informe o nome do autor..."
      />
      <span id="erro-nome" class="msg-erro"></span>
      <input type="submit" value="Salvar" class="botao1" />
    </form>
  </div>
</main>

<script src="js/validador.js"></script>

<jsp:include page="includes/footer.jsp" />
