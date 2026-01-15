package br.com.biblioteca.exceptions;

public class CampoObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CampoObrigatorioException(String msg) {
		super(msg);
	}
	
	public CampoObrigatorioException(String msg, Exception e) {
		super(msg, e);
	}

}
