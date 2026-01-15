package br.com.biblioteca.model.entities;

import java.util.Objects;

/**
 * Representa um autor dentro do dominío
 * 
 * @author Stefano Paulo
 */
public class Autor {
	
	private Integer idautor;
	private String nome;
	
	public Autor() {
	}

	public Autor(Integer idautor, String nome) {
		this.idautor = idautor;
		this.nome = nome;
	}

	public Integer getIdautor() {
		return idautor;
	}

	public void setIdautor(Integer idautor) {
		this.idautor = idautor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idautor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(idautor, other.idautor);
	}

	@Override
	public String toString() {
		return "Autor [idautor=" + idautor + ", nome=" + nome + "]";
	}

}
