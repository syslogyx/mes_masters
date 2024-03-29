package com.syslogyx.constants;

/**
 * Constants file to define all API Endpoint's constants along with the Headers,
 * Path Variable and Request Params
 * 
 * @author namrata
 *
 */
public interface INetworkConstants {

	/**
	 * API End-points constants
	 * 
	 * @author namrata
	 *
	 */
	public interface IURLConstants {

		public String API = "/api";
		public String APP = "/app";
		public String LOGIN = "/login";
		public String ROLE = "/role";
		public String MASTERS = "/masters";
		public String SAVE = "/save";
		public String UPDATE = "/update";
		public String CODE_GROUP = "/code_group";
		public String LIST = "/list";
		public String STATUS = "/status";
		public String EXCEL = "/excel";
		public String PDF = "/pdf";
		public String VIEW = "/view";
		public String CAMPAIGN = "/campaign";
		public String DPR_TARGET = "dpr_target";
		public String LOGOUT = "/logout";
		public String LEAD_TIME = "/lead_time";
		public String ELONGATION = "/elongation";
		public String PROCESS_FAMILY = "/process_family";
		public String PROCESS_TYPE = "/process_type";
		public String PROCESS_UNIT = "/process_unit";
		public String PRODUCT = "/product";
		public String SHELF_LIFE = "/shelf_life";
		public String SHRINKAGE = "/shrinkage";
		public String TRIMMING = "/trimming";
		public String THICKNESS = "/thickness";
		public String USERS = "/user";

		// Master Activity Log URL Constants

		public String ACTIVITY = "/activity";
		public String MASTER_ACTIVITY_LOG = "/master_activity_log";
		public String CAMPAIGN_ACTIVITY = "/campaign_activity";
		public String DPR_TARGET_ACTIVITY_LOG = "/dprTarget_activity_log";
		public String ELONGATION_ACTIVITY_LOG = null;
		
	}

	/**
	 * API Headers constants
	 * 
	 * @author namrata
	 *
	 */
	public interface IHeaderConstants {
		
		String X_AUTH_TOKEN = "X-AUTH-TOKEN";

	}

	/**
	 * API Request Params constants
	 * 
	 * @author namrata
	 *
	 */
	public interface IRequestParamConstants {

		String PAGE = "page";
		String LIMIT = "limit";
		String STATUS = "status";
		String PROCESS_UNIT_ID = "process_unit_id";

	}

	/**
	 * API Path Variables constants
	 * 
	 * @author namrata
	 *
	 */
	public interface IPathVariableConstants {

		String CODE_GROUP_ID = "code_group_id";
		String MASTER_NAME = "master_name";
		String CAMP_ID = "cam_id";
		String MASTER_ID = "master_id";

	}

	public interface ITables {

		String USER_ROLES = "user_roles";
		String ROLE_AUTHORITIES = "role_authorities";
		String USER_DEPARTMENTS = "user_departments";

	}
}
