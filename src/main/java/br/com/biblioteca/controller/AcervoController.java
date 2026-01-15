package br.com.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.biblioteca.exceptions.AutorException;
import br.com.biblioteca.exceptions.GerarRelatorioException;
import br.com.biblioteca.model.dao.AutorDAO;
import br.com.biblioteca.model.dao.LivroDAO;
import br.com.biblioteca.model.dao.impl.AutorDAOImpl;
import br.com.biblioteca.model.dao.impl.LivroDAOImpl;
import br.com.biblioteca.model.entities.Autor;
import br.com.biblioteca.model.entities.Livro;
import br.com.biblioteca.model.enums.GeneroLivro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class AcervoController.
 * 
 * @author Stefano Paulo
 */
@WebServlet(urlPatterns = { "/controller", "/novoAutor", "/cadastrarAutores", "/listarLivros", "/novoLivro",
		"/cadastrarLivros", "/editarLivro", "/atualizarLivro", "/deletarLivro", "/relatorioLivros" })
public class AcervoController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The autor DAO. */
	private AutorDAO autorDAO = new AutorDAOImpl();
	
	/** The livro DAO. */
	private LivroDAO livroDAO = new LivroDAOImpl();

	/**
	 * Instantiates a new acervo controller.
	 */
	public AcervoController() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/novoAutor":
			req.getRequestDispatcher("novo-autor.jsp").forward(req, resp);
			break;
		case "/listarLivros":
			listarLivros(req, resp);
			break;
		case "/novoLivro":
			novoLivro(req, resp);
			break;
		case "/editarLivro":
			editarLivro(req, resp);
			break;
		case "/relatorioLivros":
			gerarRelatorio(req, resp);
			break;
		default:
			resp.sendRedirect("index.jsp");
		}
	}

	/**
	 * Do post.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/cadastrarAutores":
			cadastrarAutores(req, resp);
			break;
		case "/cadastrarLivros":
			cadastrarLivros(req, resp);
			break;
		case "/atualizarLivro":
			atualizarLivro(req, resp);
			break;
		case "/deletarLivro":
			deletarLivro(req, resp);
			break;

		default:
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}

	/**
	 * Novo livro.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void novoLivro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Autor> autores = autorDAO.buscarTodos();

		req.setAttribute("autores", autores);

		req.getRequestDispatcher("novo-livro.jsp").forward(req, resp);

	}

	/**
	 * Cadastrar autores.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void cadastrarAutores(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Autor autor = new Autor();
		autor.setNome(req.getParameter("nome"));

		autorDAO.inserir(autor);

		resp.sendRedirect("novoLivro?msg=autorSalvo");
	}

	/**
	 * Listar livros.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarLivros(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Livro> livros = livroDAO.buscarTodos();

		req.setAttribute("livros", livros);

		req.getRequestDispatcher("listar-livros.jsp").forward(req, resp);

	}

	/**
	 * Cadastrar livros.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void cadastrarLivros(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Autor autor = autorDAO.buscarporId(Integer.valueOf(req.getParameter("idAutor")));

		if (autor == null) {
			throw new AutorException("Erro ao tentar cadastrar o livro. Não foi possível localizar o autor.");
		}

		Livro livro = new Livro();
		livro.setTitulo(req.getParameter("titulo"));
		livro.setAnoPublicacao(req.getParameter("ano_publicacao"));
		livro.setGenero(GeneroLivro.valueOf(req.getParameter("genero")));
		livro.setAutor(autor);

		livroDAO.inserir(livro);

		resp.sendRedirect("listarLivros?msg=livroSalvo");
	}

	/**
	 * Editar livro.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarLivro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idlivro = Integer.parseInt(req.getParameter("idlivro"));

		Livro livro = livroDAO.buscarPorId(idlivro);

		List<Autor> autores = autorDAO.buscarTodos();

		req.setAttribute("livro", livro);
		req.setAttribute("autores", autores);

		req.getRequestDispatcher("editar-livro.jsp").forward(req, resp);
	}

	/**
	 * Atualizar livro.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void atualizarLivro(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Autor autor = autorDAO.buscarporId(Integer.valueOf(req.getParameter("idAutor")));

		if (autor == null) {
			throw new AutorException("Erro ao tentar cadastrar o livro. Não foi possível localizar o autor.");
		}

		Livro livro = new Livro();
		livro.setIdlivro(Integer.parseInt(req.getParameter("idlivro")));
		livro.setTitulo(req.getParameter("titulo"));
		livro.setAnoPublicacao(req.getParameter("ano_publicacao"));
		livro.setGenero(GeneroLivro.valueOf(req.getParameter("genero")));
		livro.setAutor(autor);

		livroDAO.atualizar(livro);

		resp.sendRedirect("listarLivros?msg=livroEditado");
	}

	/**
	 * Deletar livro.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deletarLivro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idlivro = Integer.parseInt(req.getParameter("idlivro"));

		livroDAO.deletar(idlivro);

		resp.sendRedirect("listarLivros");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Document documento = new Document();

		try {
			resp.setContentType("application/pdf");
			resp.addHeader("Content-Disposition", "inline; filename=" + "rel_livros.pdf");

			PdfWriter.getInstance(documento, resp.getOutputStream());

			documento.open();
			documento.add(new Paragraph("Lista de livros"));
			documento.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(4);

			PdfPCell col1 = new PdfPCell(new Paragraph("Título"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Ano publicação"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Gênero"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Autor"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			List<Livro> livros = livroDAO.buscarTodos();

			for (Livro livro : livros) {
				tabela.addCell(livro.getTitulo());
				tabela.addCell(livro.getAnoPublicacao());
				tabela.addCell(livro.getGenero().getDescricao());
				tabela.addCell(livro.getAutor().getNome());
			}

			documento.add(tabela);
		} catch (Exception e) {
			throw new GerarRelatorioException("Erro ao gerar relatório. Causa: " + e.getMessage());
		} finally {
			documento.close();
		}

	}

}
