package com.syslogyx.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This Interface includes the Excel and PDF Header constants
 * 
 * @author namrata
 *
 */
public interface IFileHeaderConstants {

	// Common Headers
	public String SR_NO = "Sr No.";
	public String LAST_UPDATED = "Last Updated";
	public String UPDATED_BY = "Updated By";
	public String STATUS = "Status";

	// Code Group Headers
	public String GROUP_CODE = "Group Code";
	public String GROUP_DESC = "Group Description";

	/**
	 * Prepare the List of Headers used in Code Group Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getCodeGroupListHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(GROUP_CODE);
		headers.add(GROUP_DESC);
		headers.add(LAST_UPDATED);
		headers.add(UPDATED_BY);
		headers.add(STATUS);

		return headers;
	}
}
