package br.com.biblioteca.model.entities;

import java.util.Objects;

import br.com.biblioteca.model.enums.GeneroLivro;

/**
 * Representa um livro dentro do dominío
 * 
 * @author Stefano Paulo
 */
public class Livro {

	private Integer idlivro;
	private String titulo;
	private String anoPublicacao;
	private GeneroLivro genero;
	private Autor autor;

	public Livro() {
	}

	public Livro(Integer idlivro, String titulo, String anoPublicacao, GeneroLivro genero, Autor autor) {
		this.idlivro = idlivro;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
		this.autor = autor;
	}

	public Integer getIdlivro() {
		return idlivro;
	}

	public void setIdlivro(Integer idlivro) {
		this.idlivro = idlivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public GeneroLivro getGenero() {
		return genero;
	}

	public void setGenero(GeneroLivro genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idlivro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(idlivro, other.idlivro);
	}

	@Override
	public String toString() {
		return "Livro [idlivro=" + idlivro + ", titulo=" + titulo + ", anoPublicacao=" + anoPublicacao + ", genero="
				+ genero + ", autor=" + autor + "]";
	}

}
