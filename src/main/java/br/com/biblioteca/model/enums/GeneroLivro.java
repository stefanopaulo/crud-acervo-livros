package br.com.biblioteca.model.enums;

public enum GeneroLivro {
	AVENTURA("Aventura"), FANTASIA("Fantasia"), TERROR("Terror"), ROMANCE("Romance"), BIOGRAFIA("Biografia"),
	HISTORIA("Historia"), AUTOAJUDA("Autoajuda");

	private String descricao;

	private GeneroLivro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
