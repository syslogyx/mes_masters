package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.model.masters.activitylog.CodeGroupDOActivityLog;

/**
 * Fetch Data from CodeGroupActivityList in DB
 * 
 * @author Palash
 *
 */
public interface IMasterActivityDAO {

	/**
	 * Fetch the CodeGroupActivityList data in table
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<CodeGroupDOActivityLog> getCodeGroupActivityList(RequestBO requestFilter, int page, int limit);

	/**
	 * Count CodeGroupActivityList data
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getCodeGroupActivityListSize(RequestBO requestFilter);

}
