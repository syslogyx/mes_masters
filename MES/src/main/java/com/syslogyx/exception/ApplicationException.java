package com.syslogyx.exception;

import com.syslogyx.model.user.UserDO;

/**
 * Customised Exception class which holds error code and error message
 * 
 * @author namrata
 *
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645612481531783857L;

	private int code;

	private String message = null;
	
	private Object msg;

	public ApplicationException(int code, String message) {
		super(message);
		this.code = code;
		this.message=message;
	}

	public ApplicationException(UserDO loggedInUser) {
		
		this.msg = loggedInUser;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}


	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
