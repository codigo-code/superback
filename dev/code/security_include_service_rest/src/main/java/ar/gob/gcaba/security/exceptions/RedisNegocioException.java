package ar.gob.gcaba.security.exceptions;

public class RedisNegocioException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6277782752425562532L;

	public RedisNegocioException() {
	}

	public RedisNegocioException(String message) {
		super(message);
	}

	public RedisNegocioException(Throwable cause) {
		super(cause);
	}

	public RedisNegocioException(String message, Throwable cause) {
		super(message, cause);
	}

}
