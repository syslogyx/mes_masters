package com.syslogyx.service.master;

import com.syslogyx.bo.RequestBO;

/**
 * For Fetch the MasterActivityLog data in db
 * 
 * @author Palash
 *
 */
public interface IMasterActivityService {

	/**
	 * Fetch the CodeGroupActivityLog data in db
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	Object listCodeGroupActivity(RequestBO requestFilter, int page, int limit);

}
