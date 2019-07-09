package com.syslogyx.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This will be the common response format
 * 
 * @author namrata
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseBO{

	@JsonProperty("status")
	public int status;

	@JsonProperty("message")
	public String message;

	@JsonProperty("response")
	public Object response;

	@JsonProperty("timestamp")
	public Date timestamp;

	
	public BaseResponseBO() {
		super();
	}

	public BaseResponseBO(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
