package com.syslogyx.exception;

/**
 * Customised Exception class which holds error code and error message
 * 
 * @author namrata
 *
 */
public class ApplicationException extends Exception {

	private int code;

	private String message = null;

	public ApplicationException(int code, String message) {
		super(message);
		this.code = code;
		this.message=message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
