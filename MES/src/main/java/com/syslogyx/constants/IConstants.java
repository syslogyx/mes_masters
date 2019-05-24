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

	/**
	 * Interface to define constants of Date time format
	 * 
	 * @author namrata
	 *
	 */
	public interface DATE_TIME_FORMAT {

		String YYYY_MM_DD_HH_MM_SS_A = "yyyy-MM-dd HH:mm:ss a";

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
