package br.com.carthur.exception;

public class TableException extends Exception {

	public TableException(String msg) {
		this(msg, null);
	}

	public TableException(String msg, Throwable e) {
		super(msg, e);
	}

}
