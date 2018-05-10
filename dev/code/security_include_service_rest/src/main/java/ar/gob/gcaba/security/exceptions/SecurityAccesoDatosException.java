package ar.gob.gcaba.security.exceptions;


public class SecurityAccesoDatosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 560646199522408165L;


	public SecurityAccesoDatosException(String message) {
		super(message);
	}

	public SecurityAccesoDatosException(String message, Throwable cause) {
		super(message, cause);
	}

}
