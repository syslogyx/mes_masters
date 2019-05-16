package com.syslogyx.constants;

import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;

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

	// Campaign Headers
	public String CAMPAIGN_ID = "Campaign_id";
	public String ATTRIBUTE = "Attribute";
	public String AIM = "Aim";
	public String CAPACITY_MIN = "Capacity_min (in Tons)";
	public String CAPACITY_MAX = "Capacity_max (in Tons)";
	public String PRIORITY_LEVEL = "Priority_level";
	public String HOLD_UNIT = "Hold_unit";

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

	public static List<String> getCampaignListHeaders() {

		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(CAMPAIGN_ID);
		headers.add(ATTRIBUTE);
		headers.add(AIM);
		headers.add(CAPACITY_MIN);
		headers.add(CAPACITY_MAX);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(PRIORITY_LEVEL);
		headers.add(HOLD_UNIT);
		headers.add(STATUS);

		return headers;

	}

	/**
	 * Return the Headers list of Masters according to the Masters name specified
	 * 
	 * @param master_name
	 * @return
	 */
	public static List<String> getMastersHeaderList(String master_name) {

		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP)) {
			return getCodeGroupListHeaders();
		}

		if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN)) {
			return getCampaignListHeaders();
		}

		return null;
	}

}
