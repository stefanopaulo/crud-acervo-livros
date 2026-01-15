package br.com.biblioteca.exceptions;

public class DBException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public DBException(String msg) {
		super(msg);
	}
	
	public DBException(String msg, Exception e) {
		super(msg, e);
	}
}
