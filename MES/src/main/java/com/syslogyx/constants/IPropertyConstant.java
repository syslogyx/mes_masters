package com.syslogyx.constants;

import javax.persistence.metamodel.SingularAttribute;

/**
 * Constant to define all properties in Entity
 * 
 * @author Palash
 *
 */
public interface IPropertyConstant {

	// Common Constants
	String ID = "id";
	String USERNAME = "username";
	String CREATED = "created";
	String UPDATED = "updated";
	String STATUS = "status";
	String CREATED_BY = "created_by";
	String UPDATED_BY = "updated_by";

	// CodeGroup Constants
	String GROUP_CODE = "group_code";
	String GROUP_DESC = "group_desc";

	// Campaign Constants
	String CAMPAIGN_ID = "campaign_id";
	String ATTRIBUTE = "attribute";
	String AIM = "aim";
	String HOLD_UNIT = "hold_unit";
	String CAPACITY_MIN = "capacity_min";
	String CAPACITY_MAX = "capacity_max";
	String PRIORITY_LEVEL = "priority_level";
	

	// ProcessUnit Constants
	String PU_ID = "pu_id";
	String UNIT = "unit";
	

	// DPR Target Constants
	String PRODUCT = "product";
	String BUSINESS_PLAN_TARGET = "business_plan_target";
	String INTERNAL_TARGET = "internal_target";
	String YEAR = "year";
	String PRODUCT_FORM = "product_form";
	String AFTER_PROCESS_UNIT = "after_process_unit";
	String BEFORE_PROCESS_UNIT = "before_process_unit";

}
