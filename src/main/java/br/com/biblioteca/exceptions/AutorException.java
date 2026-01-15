package br.com.biblioteca.exceptions;

public class AutorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AutorException(String msg) {
		super(msg);
	}
	
	public AutorException(String msg, Exception e) {
		super(msg, e);
	}

}
