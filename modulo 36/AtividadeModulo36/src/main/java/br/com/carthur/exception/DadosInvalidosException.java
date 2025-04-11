package br.com.carthur.exception;

/**
 * @author caio.arthur
 */
public class DadosInvalidosException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6026522664163173463L;

	public DadosInvalidosException(String msg) {
        this(msg, null);
    }

    public DadosInvalidosException(String msg, Throwable e) {
        super(msg, e);
    }
}