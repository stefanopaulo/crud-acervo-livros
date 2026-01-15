package br.com.biblioteca.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.biblioteca.exceptions.DBException;
import br.com.biblioteca.model.dao.LivroDAO;
import br.com.biblioteca.model.entities.Autor;
import br.com.biblioteca.model.entities.Livro;
import br.com.biblioteca.model.enums.GeneroLivro;
import br.com.biblioteca.util.Conexao;

/**
 * The Class LivroDAOImpl.
 * 
 * @author Stefano Paulo
 */
public class LivroDAOImpl implements LivroDAO {

	/**
	 * Inserir.
	 *
	 * @param livro the livro
	 * @return the livro
	 */
	@Override
	public Livro inserir(Livro livro) {
		String sql = """
				INSERT INTO livros
				(titulo, ano_publicacao, genero, id_autor)
				VALUES
				(?, ?, ?, ?)
				""";

		try (Connection conn = Conexao.getConexao();
				PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			psmt.setString(1, livro.getTitulo());
			psmt.setString(2, livro.getAnoPublicacao());
			psmt.setString(3, livro.getGenero().toString());
			psmt.setInt(4, livro.getAutor().getIdautor());

			if (psmt.executeUpdate() > 0) {
				try (ResultSet res = psmt.getGeneratedKeys()) {
					if (res.next()) {
						livro.setIdlivro(res.getInt(1));
					}
				}
			}

			return livro;

		} catch (SQLException e) {
			throw new DBException("Erro ao inserir livro. Causa: " + e.getMessage());
		}
	}

	/**
	 * Atualizar.
	 *
	 * @param livro the livro
	 * @return true, if successful
	 */
	@Override
	public boolean atualizar(Livro livro) {
		String sql = """
				UPDATE livros
				SET titulo = ?,
					ano_publicacao = ?,
					genero = ?,
					id_autor = ?
				WHERE idlivro = ?
				""";

		try (Connection conn = Conexao.getConexao(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setString(1, livro.getTitulo());
			psmt.setString(2, livro.getAnoPublicacao());
			psmt.setString(3, livro.getGenero().toString());
			psmt.setInt(4, livro.getAutor().getIdautor());
			psmt.setInt(5, livro.getIdlivro());
			
			return psmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			throw new DBException("Erro ao atualizar livro. Causa: " + e.getMessage());
		}
	}

	/**
	 * Deletar.
	 *
	 * @param idlivro the idlivro
	 * @return true, if successful
	 */
	@Override
	public boolean deletar(int idlivro) {
		String sql = "DELETE FROM livros WHERE idlivro = ?";
		
		try (Connection conn = Conexao.getConexao(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setInt(1, idlivro);
			
			return psmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			throw new DBException("Erro ao deletar livro. Causa: " + e.getMessage());
		}
	}

	/**
	 * Buscar todos.
	 *
	 * @return the list
	 */
	@Override
	public List<Livro> buscarTodos() {
		String sql = """
				SELECT
					l.idlivro,
					l.titulo,
					l.ano_publicacao,
					l.genero,
					l.id_autor,
					a.nome AS nome_autor
				FROM livros l
				INNER JOIN autores a ON l.id_autor = a.idautor
				ORDER BY l.idlivro
				""";

		List<Livro> livros = new ArrayList<>();

		try (Connection conn = Conexao.getConexao();
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet res = psmt.executeQuery()) {

			while (res.next()) {
				Autor autor = new Autor(res.getInt("id_autor"), res.getString("nome_autor"));

				Livro livro = new Livro();
				livro.setIdlivro(res.getInt("idlivro"));
				livro.setTitulo(res.getString("titulo"));
				livro.setAnoPublicacao(res.getString("ano_publicacao"));
				livro.setGenero(GeneroLivro.valueOf(res.getString("genero")));
				livro.setAutor(autor);

				livros.add(livro);
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao listar livros. Causa: " + e.getMessage());
		}

		return livros;
	}

	/**
	 * Buscar por id.
	 *
	 * @param idlivro the idlivro
	 * @return the livro
	 */
	@Override
	public Livro buscarPorId(int idlivro) {
		String sql = """
				SELECT
					l.idlivro,
					l.titulo,
					l.ano_publicacao,
					l.genero,
					l.id_autor,
					a.nome AS nome_autor
				FROM livros l
				INNER JOIN autores a ON l.id_autor = a.idautor
				WHERE l.idlivro = ?
				""";
		
		Livro livro = new Livro();

		try (Connection conn = Conexao.getConexao();
				PreparedStatement psmt = conn.prepareStatement(sql)) {
			
			psmt.setInt(1, idlivro);
			
			try (ResultSet res = psmt.executeQuery()) {				
				if (res.next()) {
					Autor autor = new Autor(res.getInt("id_autor"), res.getString("nome_autor"));
					
					livro.setIdlivro(res.getInt("idlivro"));
					livro.setTitulo(res.getString("titulo"));
					livro.setAnoPublicacao(res.getString("ano_publicacao"));
					livro.setGenero(GeneroLivro.valueOf(res.getString("genero")));
					livro.setAutor(autor);
				}
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao buscar livro. Causa: " + e.getMessage());
		}

		return livro;
	}

}
