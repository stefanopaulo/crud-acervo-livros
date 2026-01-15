package br.com.biblioteca.exceptions;

public class LivroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LivroException(String msg) {
		super(msg);
	}
	
	public LivroException(String msg, Exception e) {
		super(msg, e);
	}

}
