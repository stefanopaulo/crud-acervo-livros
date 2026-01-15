<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="includes/header.jsp" />

<%@ page import="br.com.biblioteca.model.entities.Livro"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<Livro> livros = (ArrayList<Livro>) request.getAttribute("livros");
%>

<main>
	<div class="container-livros">
		<h2>Listagem de livros</h2>

		<div class="botoes-acao">
			<a href="novoLivro" class="botao1">Cadastrar novo livro</a>
			<a href="relatorioLivros" class="botao5" target="_blank">Gerar relatório</a>
		</div>

		<table>
			<thead>
				<tr>
					<th>Título</th>
					<th>Ano da publicação</th>
					<th>Gênero</th>
					<th>Autor</th>
					<th>Ações</th>
				</tr>
			</thead>

			<tbody>
				<% for (Livro livro : livros) { %>
					
					<tr>
						<td><%= livro.getTitulo() %></td>
						<td><%= livro.getAnoPublicacao() %></td>
						<td><%= livro.getGenero() %></td>
						<td><%= livro.getAutor().getNome() %></td>
						<td>
							<a href="editarLivro?idlivro=<%= livro.getIdlivro() %>" class="botao3">Editar</a>
							<form action="deletarLivro" method="post" id="formBtnDelete">
      
    						<input type="hidden" name="idlivro" value="<%= livro.getIdlivro() %>">
    
    						<input type="submit" class="botao4" value="Excluir">
							</form>
						</td>
					</tr>

				<%}%>
			</tbody>
		</table>
	</div>
</main>

<div id="modalConfirmacao" class="modal">
  <div class="modal-content">
    <h3>Confirmação</h3>
    <p>Tem certeza que deseja excluir este livro?</p>
    <div class="modal-buttons">
      <button type="button" id="btnCancelar" class="botao-cancelar">Cancelar</button>
      <button type="button" id="btnConfirmar" class="botao-confirmar">Excluir</button>
    </div>
  </div>
</div>

<div id="toast"></div>

<script src="js/confirmador.js"></script>
<script src="js/mensagens.js"></script>

<jsp:include page="includes/footer.jsp" />