package br.com.carthur.exception;

public class TableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5895385448194335641L;

	public TableException(String msg) {
		this(msg, null);
	}

	public TableException(String msg, Throwable e) {
		super(msg, e);
	}

}
