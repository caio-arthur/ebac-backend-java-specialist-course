package br.com.carthur.exception;

public class TipoElementoNaoConhecidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5198275898387666441L;

	public TipoElementoNaoConhecidoException(String msg) {
		this(msg, null);
	}

	public TipoElementoNaoConhecidoException(String msg, Throwable e) {
		super(msg, e);
	}

}
