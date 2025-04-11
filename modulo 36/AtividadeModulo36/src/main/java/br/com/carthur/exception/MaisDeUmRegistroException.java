package br.com.carthur.exception;

public class MaisDeUmRegistroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 927431809794271449L;

	public MaisDeUmRegistroException(String msg) {
		this(msg, null);
	}

	public MaisDeUmRegistroException(String msg, Throwable e) {
		super(msg, e);
	}

}
