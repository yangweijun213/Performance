package com.lombardrisk.colline.supportutil.exception;

/**
 * The SUException that encapsulate all exceptions
 * Created by Bond Chen on 3/9/2016.
 */
public class SUException extends Exception {


	private static final long serialVersionUID = 1L;
	private String errorMessage = null;
	private Throwable rootCase = null;

	/**
	 * Constructs a new SUException with the specified detail message.
	 *
	 * @param errorMsg The detail message.
	 *
	 */
	public SUException(String errorMsg, Throwable throwable) {
		this.errorMessage = errorMsg;
		this.rootCase = throwable;
	}


	/**
	 * Override the function getMessage() of class Throwable.
	 *
	 * @return Detailed message.
	 */
	public String getMessage() {

		if (this.errorMessage != null) {
			return this.errorMessage;
		}else {
			return super.toString();
		}

	}

	/**
	 * Get the root cause exception
	 * @return the root cause exception
	 */
	public Throwable getRootCause(){
		return rootCase;
	}
}
