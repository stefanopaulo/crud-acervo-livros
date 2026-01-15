package br.com.biblioteca.exceptions;

public class GerarRelatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GerarRelatorioException(String msg) {
		super(msg);
	}

	public GerarRelatorioException(String msg, Exception e) {
		super(msg, e);
	}

}
