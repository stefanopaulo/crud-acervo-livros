package br.com.biblioteca.model.dao;

import java.util.List;

import br.com.biblioteca.model.entities.Autor;

public interface AutorDAO {
	Autor inserir(Autor autor);
	List<Autor> buscarTodos();
	Autor buscarporId(int idautor);
}
