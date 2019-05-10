package com.syslogyx.message;

/**
 * Constant file to define all Response Messages used for API response
 * 
 * @author namrata
 *
 */
public interface IResponseMessages {

	String SUCCESS = "Success";
	String UNAUTHORIZED_USER = "User is unauthorized.";
	String SERVER_ERROR = "Server Error.";
	String EXISTING_GROUP_CODE = "This Group Code already exists.";
	String INVALID_GROUP_CODE = "Invalid Group Code Id.";
	String INVALID_LIST_MESSAGE = "Data is not Present";
	String DATA_NOT_FOUND = "Data not found.";
	String INVALID_STATUS = "Invalid Status.";

}
