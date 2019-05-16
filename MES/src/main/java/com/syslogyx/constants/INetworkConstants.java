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
		public String MASTERS = "/masters";
		public String SAVE = "/save";
		public String UPDATE = "/update";
		public String CODE_GROUP = "/code_group";
		public String LIST = "/list";
		public String STATUS = "/status";
		public String EXCEL = "/excel";
		public String PDF = "/pdf";
		public String DPR_TARGET = "dpr_target";
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
		String MASTER_ID = "master_id";

	}
}
