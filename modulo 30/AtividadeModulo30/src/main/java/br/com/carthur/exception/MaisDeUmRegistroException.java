package br.com.carthur.exception;

public class MaisDeUmRegistroException extends Exception {

	public MaisDeUmRegistroException(String msg) {
		this(msg, null);
	}

	public MaisDeUmRegistroException(String msg, Throwable e) {
		super(msg, e);
	}

}
