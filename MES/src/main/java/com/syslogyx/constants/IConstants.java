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

}
