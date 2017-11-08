package io.spring4mvc.spring4mvc.error;

public class EntityNotFoundException extends Exception {
	private static final long serialVersionUID = 5479223091781450630L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}
	
	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
