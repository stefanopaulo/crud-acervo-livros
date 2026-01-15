package br.com.biblioteca.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.biblioteca.exceptions.ConexaoException;
import br.com.biblioteca.exceptions.PropertiesException;

/**
 * The Class Conexao.
 * 
 * @author Stefano Paulo
 */
public class Conexao {

	/**
	 * Instantiates a new conexao.
	 */
	private Conexao() {
	}

	/**
	 * Gets the conexao.
	 *
	 * @return the conexao
	 */
	public static Connection getConexao() {
		try {
			Properties prop = getProperties();

			String driver = prop.getProperty("driver");

			if (driver == null || driver.isEmpty()) {
				throw new PropertiesException("Propriedade 'driver' não encontrada no db.properties");
			}

			Class.forName(driver);

			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (ClassNotFoundException e) {
			throw new ConexaoException("Erro ao carregar driver do banco", e);
		} catch (SQLException e) {
			throw new ConexaoException("Erro ao acessar banco de dados", e);
		}
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	private static Properties getProperties() {
		Properties prop = new Properties();

		try (InputStream input = Conexao.class.getResourceAsStream("/db.properties")) {
			if (input == null) {
				throw new PropertiesException("Arquivo db.properties não encontrado na pasta resources!");
			}
			prop.load(input);
		} catch (IOException e) {
			throw new PropertiesException("Erro ao carregar arquivo db.properties");
		}

		return prop;
	}

}
