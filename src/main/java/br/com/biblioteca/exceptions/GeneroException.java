package br.com.biblioteca.exceptions;

public class GeneroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public GeneroException(String msg) {
		super(msg);
	}
	
	public GeneroException(String msg, Exception e) {
		super(msg, e);
	}

}
