<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="includes/header.jsp" />

<%@ page import="br.com.biblioteca.model.entities.Autor"%>
<%@ page import="br.com.biblioteca.model.enums.GeneroLivro"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<Autor> autores = (ArrayList<Autor>) request.getAttribute("autores");
%>

<main>
  <div class="formCadAutor">
    <h2>Cadastrar novo livro</h2>

    <form action="cadastrarLivros" method="post" onsubmit="return validarFormLivro();">
			<label for="titulo">Título</label>
      <input type="text" name="titulo" id="titulo" placeholder="Título..." />
			<span id="erro-titulo" class="msg-erro"></span>

			<label for="ano_publicacao">Ano de publicação</label>
			<input type="text" name="ano_publicacao" id="ano_publicacao" placeholder="Ano de publicação..." maxlength="4">
			<span id="erro-ano" class="msg-erro"></span>

			<label for="genero">Gênero</label>
			<select name="genero" id="genero">
				<option value="" selected disabled>Selecione o gênero...</option>
			    <% for (GeneroLivro genero : GeneroLivro.values()) { %>
			        <option value="<%= genero.name() %>">
			            <%= genero %>
			        </option>
			    <% } %>
			</select>
			<span id="erro-genero" class="msg-erro"></span>

			<label for="idAutor">Autor</label>
			<select name="idAutor" id="idAutor">
				<option value="" selected disabled>Selecione um autor...</option>
				<% for (Autor autor : autores) { %>

					<option value="<%= autor.getIdautor() %>">
						<%= autor.getNome() %>
					</option>

				<%}%>
			</select>
			<span id="erro-autor" class="msg-erro"></span>

			<a href="novoAutor" id="linkNovoAutor">Cadastrar novo autor</a>

			<input type="submit" value="Salvar" class="botao1" />
    </form>
  </div>
</main>

<div id="toast"></div>

<script src="js/validador.js"></script>
<script src="js/mensagens.js"></script>

<jsp:include page="includes/footer.jsp" />
