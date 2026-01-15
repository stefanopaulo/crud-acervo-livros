package br.com.biblioteca.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.biblioteca.exceptions.DBException;
import br.com.biblioteca.model.dao.AutorDAO;
import br.com.biblioteca.model.entities.Autor;
import br.com.biblioteca.util.Conexao;

/**
 * The Class AutorDAOImpl.
 * 
 * @author Stefano Paulo
 */
public class AutorDAOImpl implements AutorDAO {

	/**
	 * Inserir.
	 *
	 * @param autor the autor
	 * @return the autor
	 */
	@Override
	public Autor inserir(Autor autor) {
		String sql = "INSERT INTO autores (nome) VALUES (?)";

		try (Connection conn = Conexao.getConexao();
				PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			psmt.setString(1, autor.getNome());

			if (psmt.executeUpdate() > 0) {
				try (ResultSet res = psmt.getGeneratedKeys()) {
					if (res.next()) {
						autor.setIdautor(res.getInt(1));
					}
				}
			}

			return autor;
		} catch (SQLException e) {
			throw new DBException("Erro ao inserir autor. Causa: " + e.getMessage());
		}
	}

	/**
	 * Buscar todos.
	 *
	 * @return the list
	 */
	@Override
	public List<Autor> buscarTodos() {
		String sql = "SELECT a.idautor, a.nome FROM autores a ORDER BY a.nome";

		try (Connection conn = Conexao.getConexao(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			try (ResultSet res = psmt.executeQuery()) {
				List<Autor> lista = new ArrayList<>();

				while (res.next()) {
					Autor autor = new Autor();
					autor.setIdautor(res.getInt(1));
					autor.setNome(res.getString(2));

					lista.add(autor);
				}

				return lista;
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao buscar autores. Causa: " + e.getMessage());
		}
	}

	/**
	 * Buscarpor id.
	 *
	 * @param idautor the idautor
	 * @return the autor
	 */
	@Override
	public Autor buscarporId(int idautor) {
		String sql = """
				SELECT
					a.idautor,
					a.nome
				FROM autores a
				WHERE a.idautor = ?
				""";

		try (Connection conn = Conexao.getConexao(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setInt(1, idautor);

			try (ResultSet res = psmt.executeQuery()) {
				Autor autor = new Autor();

				if (res.next()) {
					autor.setIdautor(res.getInt(1));
					autor.setNome(res.getString(2));
				}

				return autor;
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao buscar autor. Causa: " + e.getMessage());
		}
	}

}
