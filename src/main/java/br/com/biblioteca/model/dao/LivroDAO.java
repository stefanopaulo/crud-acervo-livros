package br.com.biblioteca.model.dao;

import java.util.List;

import br.com.biblioteca.model.entities.Livro;

public interface LivroDAO {

	Livro inserir(Livro livro);
	boolean atualizar(Livro livro);
	boolean deletar(int idlivro);
	List<Livro> buscarTodos();
	Livro buscarPorId(int idlivro);
	
}
