package br.com.biblioteca.exceptions;

public class DTOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DTOException(String msg) {
		super(msg);
	}
	
	public DTOException(String msg, Exception e) {
		super(msg, e);
	}

}
