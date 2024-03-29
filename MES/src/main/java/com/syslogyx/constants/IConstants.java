package com.syslogyx.constants;

/**
 * Interface for Declaring all the Constants
 * 
 * @author namrata
 *
 */
public interface IConstants {

	public long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
	public String SIGNING_KEY = "devglan123r";
	public String TOKEN_PREFIX = "Bearer ";
	public String HEADER_STRING = "Authorization";
	public String AUTHORITIES_KEY = "scopes";
	public int STATUS_ACTIVE = 1;
	public int STATUS_INACTIVE = 0;
	public int DEFAULT = -1;
	public int VALUE_ZERO = 0;
	public String EXCEL_BASE_PATH = "/home/alok/Desktop/excel/";
	public String PDF_BASE_PATH = "/home/alok/Desktop/pdf/";
	public String EXTENSION_EXCEL = ".xls";
	public String EXTENSION_PDF = ".pdf";
	public int VALUE_ONE = 1;
	public String AUTH_LOGIN_URL = "/api/app/login";

	/**
	 * Interface to define constants of Date time format
	 * 
	 * @author namrata
	 *
	 */
	public interface DATE_TIME_FORMAT {

		String YYYY_MM_DD_HH_MM_SS_A = "yyyy-MM-dd HH:mm:ss a";
		String HH_MM_SS = "HH:mm:ss";

	}

	/**
	 * Interface to define constants of Masters Name
	 * 
	 * @author namrata
	 *
	 */
	public interface MASTERS_NAME {
		public String CODE_GROUP = "code_group";
		public String CAMPAIGN = "campaign";
		public String DPR_TARGET = "dpr_target";
		public String LEAD_TIME = "lead_time";
		public String ELONGATION = "elongation";
		public String PROCESS_FAMILY = "process_family";
		public String PROCESS_UNIT = "process_unit";
		public String PRODUCT = "product";
		public String SHELF_LIFE = "shelf_life";
		public String SHRINKAGE = "shrinkage";
		public String TRIMMING = "trimming";
		public String THICKNESS = "thickness";
		public String MASTERS = "masters";
		public String USER = "user";
		
		//Masters Activity log Constant
		public String CODE_GROUP_ACTIVITY_LOG = "code_group_activity_log";
	}

	/**
	 * Priority constants
	 * 
	 * @author palash
	 *
	 */
	public interface IPriority {

		public int LOW = 1;
		public int MEDIUM = 2;
		public int HIGH = 3;
	}

	/**
	 * Interface to define constants of OSP identifier
	 * 
	 * @author namrata
	 *
	 */
	public interface OSP_IDENTIFIER {
		public int YES = 1;
		public int NO = 2;
	}
}
