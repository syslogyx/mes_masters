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
	String LIST_EXPORTED_SUCCESSFULLY = "List exported Successfully.";
	String CANT_CONVERT_HASH_CODE = "Can't Convert Hash Code.";
	String UNABLE_TO_LOCATE_FILE = "Unable to Locate File location.";
	String UNABLE_TO_EXORT_EXCEL = "Unable to Export the Excel file.";
	String UNABLE_TO_EXORT_PDF = "Unable to Export PDF.";
	String INVALID_DPR_TARGET_ID = "Invalid DPR Target Id.";
	String INVALID_PROCESS_UNIT_ID = "Invalid Process Unit Id.";
	String INVALID_PRODUCT_ID = "Invalid Product Id.";
	String INVALID_MASTER_ID = "Invalid Master Id.";
	String INVALID_MASTER_NAME = "Invalid Master name.";

}
