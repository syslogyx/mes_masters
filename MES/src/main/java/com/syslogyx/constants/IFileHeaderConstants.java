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
	public String INCREMENTOR = "Incrementor";

	// Campaign Headers
	public String CAMPAIGN_ID = "Campaign_id";
	public String ATTRIBUTE = "Attribute";
	public String AIM = "Aim";
	public String CAPACITY_MIN = "Capacity_min (in Tons)";
	public String CAPACITY_MAX = "Capacity_max (in Tons)";
	public String PRIORITY_LEVEL = "Priority_level";
	public String HOLD_UNIT = "Hold_unit";

	// DPR Target Headers
	public String YEAR = "Year";
	public String UNIT = "Unit";
	public String PRODUCT = "Product";
	public String BUSINESS_PLAN_TARGET = "Business Plan Target";
	public String INTERNAL_TARGET = "Internal Target";

	// Lead Time Headers
	public String AFTER_PROCESS_UNIT = "After Process Unit";
	public String BEFORE_PROCESS_UNIT = "Before Process Unit";

	// Elongation Headers
	public String PROCESS_UNIT = "Process Unit";
	public String CR_GRADE = "CR Grade";

	// Process Family Headers
	public String PROCESS_FAMILY = "Process Family";
	public String PROCESS_TYPE = "Process Type";
	public String BUCKET = "Bucket";

	// Process Unit Headers
	public String COST_CENTER = "Cost Center";
	public String CAPACITY = "Capacity";
	public String CONST_SETUP_TIME = "Const. Setup Time";
	public String YEILD = "Yeild";
	public String OSP_IDENTIFIER = "OSP Identifier";

	// Lead Time Headers
	public String IDLE_TIME_MIN = "Ideal Time Min";
	public String IDLE_TIME_MAX = "Ideal Time Max";
	public String HANDLE_TIME_MIN = "Handle Time Min";
	public String HANDLE_TIME_MAX = "Handle Time Max";

	// Product Definition Headers
	public String PRODUCT_TYPE = "Product Type";
	public String PRODUCT_FORM = "Product Form";
	
	//Shelf Life Headers
	public String SHELF_LIFES = "Shelf Life (In Days)";
	
	//Trimming Headers
	public String TRIM_ALLO_MIN = "Trim Allo Min";
	public String TRIM_ALLO_MAX = "Trim Allo Max";
	public String TRIM_ALLO_AIM = "Trim Allo Aim";

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
		headers.add(INCREMENTOR);
		headers.add(LAST_UPDATED);
		headers.add(UPDATED_BY);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the List of Headers used in Campaign Excel and PDF
	 * 
	 * @return
	 */
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

		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			return getCodeGroupListHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			return getCampaignListHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			return getDPRTargetHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.LEAD_TIME))
			return getLeadTimeHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.ELONGATION))
			return getElongationHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			return getProcessFamilyHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_UNIT))
			return getProcessUnitHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.PRODUCT))
			return getProductHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.SHELF_LIFE))
			return getShelfLifeHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.SHRINKAGE))
			return getShrinkageHeaders();

		else if (master_name.equals(IConstants.MASTERS_NAME.TRIMMING))
			return getTrimmingHeaders();

		return null;
	}

	public static List<String> getTrimmingHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PROCESS_UNIT);
		headers.add(TRIM_ALLO_MIN);
		headers.add(TRIM_ALLO_MAX);
		headers.add(TRIM_ALLO_AIM);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	public static List<String> getShrinkageHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(CR_GRADE);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	public static List<String> getShelfLifeHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PRODUCT);
		headers.add(CR_GRADE);
		headers.add(SHELF_LIFES);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	public static List<String> getProductHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PRODUCT);
		headers.add(PRODUCT_TYPE);
		headers.add(PRODUCT_FORM);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the list of Headers used in Process Unit Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getProcessUnitHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PROCESS_UNIT);
		headers.add(PROCESS_FAMILY);
		headers.add(COST_CENTER);
		headers.add(CAPACITY);
		headers.add(CONST_SETUP_TIME);
		headers.add(YEILD);
		headers.add(OSP_IDENTIFIER);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the list of Headers used in ProcessFamily Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getProcessFamilyHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PROCESS_FAMILY);
		headers.add(PROCESS_TYPE);
		headers.add(PRIORITY_LEVEL);
		headers.add(BUCKET);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the List of Headers used in Elongation Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getElongationHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(PROCESS_UNIT);
		headers.add(CR_GRADE);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the List of Headers used in DPR Target Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getDPRTargetHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add(SR_NO);
		headers.add(YEAR);
		headers.add(UNIT);
		headers.add(PRODUCT);
		headers.add(BUSINESS_PLAN_TARGET);
		headers.add(INTERNAL_TARGET);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

	/**
	 * Prepare the List of Headers used in Lead Time Excel and PDF
	 * 
	 * @return
	 */
	public static List<String> getLeadTimeHeaders() {

		List<String> headers = new ArrayList<>();

		headers.add(SR_NO);
		headers.add(AFTER_PROCESS_UNIT);
		headers.add(BEFORE_PROCESS_UNIT);
		headers.add(IDLE_TIME_MIN);
		headers.add(IDLE_TIME_MAX);
		headers.add(HANDLE_TIME_MIN);
		headers.add(HANDLE_TIME_MAX);
		headers.add(UPDATED_BY);
		headers.add(LAST_UPDATED);
		headers.add(STATUS);

		return headers;
	}

}
