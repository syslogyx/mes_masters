package com.syslogyx.message;

/**
 * Constant file to define all Response Messages used for API response
 * 
 * @author namrata
 *
 */
public interface IResponseMessages {

	String SUCCESS = "Success.";
	String UNAUTHORIZED_USER = "User is unauthorized.";
	String SERVER_ERROR = "Server Error.";
	String EXISTING_GROUP_CODE = "This Group Code already exists.";
	String INVALID_GROUP_CODE_ID = "Invalid Group Code Id.";
	String INVALID_LIST_MESSAGE = "Data is not Present.";
	String DATA_NOT_FOUND = "Data not found.";
	String INVALID_STATUS = "Invalid Status.";
	String EXISTING_CAMPAIGN_ID = "This Campaign Id already exists.";
	String INVALID_CAMPAIGN_ID = "Invalid Campaign Id.";
	String INVALID_PRIORITY = "Invalid Priority level.";
	String INVALID_PROCESS_UNIT_ID = "Invalid Process Unit Id.";
	String EMPTY_CAMPAIGN_ID = "Campaign Id is can't be empty.";
	String DATA_STORED_SUCCESSFULLY = "Data Stored Succesfully.";
	String STATUS_UPDATE = "Status Update Successfully.";
	String INACTIVE_STATUS = "Status Already Inactive.";

}
