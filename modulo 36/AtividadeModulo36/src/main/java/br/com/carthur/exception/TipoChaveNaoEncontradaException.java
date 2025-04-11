package br.com.carthur.exception;

/**
 * @author caio.arthur
 */
public class TipoChaveNaoEncontradaException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7533895711258424496L;

	public TipoChaveNaoEncontradaException(String msg) {
        this(msg, null);
    }

    public TipoChaveNaoEncontradaException(String msg, Throwable e) {
        super(msg, e);
    }
}