package com.syslogyx.message;

/**
 * Constant file to define all Response Codes used for API response
 * 
 * @author namrata
 *
 */
public interface IResponseCodes {

	/**
	 * HTTP Status Codes
	 */
	int SUCCESS = 200;
	int DATA_NOT_FOUND = 204;
	int UNAUTHORIZED = 401;
	int SERVER_ERROR = 501;

	/**
	 * Custom Status Codes
	 */
	int EXISTING_ENTITY = 1001;
	int INVALID_ENTITY = 1002;
	int INVALID_STATUS = 1003;

}
