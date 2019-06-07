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
	String CR_GRADE = "cr_grade";
	String UNIT = "unit";
	String NAME = "name";

	// CodeGroup Constants
	String GROUP_CODE = "group_code";
	String GROUP_DESC = "group_desc";
	String INCREMENTOR = "incrementor";

	// Campaign Constants
	String CAMPAIGN_ID = "campaign_id";
	String ATTRIBUTE = "attribute";
	String AIM = "aim";
	String HOLD_UNIT = "hold_unit";
	String CAPACITY_MIN = "capacity_min";
	String CAPACITY_MAX = "capacity_max";
	String PRIORITY_LEVEL = "priority_level";

	// DPR Target Constants
	String PRODUCT = "product";
	String BUSINESS_PLAN_TARGET = "business_plan_target";
	String INTERNAL_TARGET = "internal_target";
	String YEAR = "year";
	String PRODUCT_FORM = "product_form";
	String AFTER_PROCESS_UNIT = "after_process_unit";
	String BEFORE_PROCESS_UNIT = "before_process_unit";

	// Process Type Constants
	String PT_ID = "id";
	

	// Process Family Constants
	String PROCESS_FAMILY = "process_family";
	String PROCESS_TYPE = "process_type";
	String BUCKET = "bucket";
	String PRIORITY = "priority";

	// Process Unit Constants
	String COST_CENTER = "cost_center";
	String CAPACITY = "capacity";
	String YIELD = "yield";
	String CONST_SETUP_TIME = "const_setup_time";
	String OSP_IDENTIFIER = "osp_identifier";
	
	//Lead Time Constants
	String IDLE_TIME_MIN = "idle_time_min";
	String HANDLE_TIME_MIN = "handle_time_min";
	String IDLE_TIME_MAX = "idle_time_max";
	String HANDLE_TIME_MAX = "handle_time_max";
	
	//Product Definition Constants
	String PRODUCT_TYPE = "product_type";
	
	//Shelf Life Constants
	String SHELF_LIFE = "shelf_life";
	
	//Trimming Constants
	String TRIM_ALLO_MIN = "trim_allo_min";
	String TRIM_ALLO_MAX = "trim_allo_max";
	String TRIM_ALLO_AIM = "trim_allo_aim";
	
	

}
