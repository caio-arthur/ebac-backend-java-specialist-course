package br.com.carthur.exception;

public class DAOException extends Exception {

	public DAOException(String msg) {
		this(msg, null);
	}

	public DAOException(String msg, Throwable e) {
		super(msg, e);
	}

}
