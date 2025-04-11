package br.com.carthur.exception;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4011197903253846811L;

	public DAOException(String msg) {
		this(msg, null);
	}

	public DAOException(String msg, Throwable e) {
		super(msg, e);
	}

}
