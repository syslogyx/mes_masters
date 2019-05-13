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
	public String EXCEL_BASE_PATH = "/home/namrata/Desktop/excel/";
	public String PDF_BASE_PATH = "/home/namrata/Desktop/pdf/";
	public String EXTENSION_EXCEL = ".xls";
	public String EXTENSION_PDF = ".pdf";

	/**
	 * Interface to define constants of Date time format
	 * 
	 * @author namrata
	 *
	 */
	public interface DATE_TIME_FORMAT {

		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	}

	/**
	 * Interface to define constants of Masters Name
	 * 
	 * @author namrata
	 *
	 */
	public interface MASTERS_NAME {
		public String CODE_GROUP = "code_group";
	}
}
