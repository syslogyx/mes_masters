package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.model.masters.CodeGroupDO;

/**
 * This interface is used for communicating with db
 * 
 * @author Palash
 *
 */
public interface IMasterDAO {

	/**
	 * This method is used for pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<CodeGroupDO> getCodeGroupList(RequestBO requestFilter, int page, int limit);

	/**
	 * This method is used for count all Numbers of rows in CodeGroup table from db
	 * @param requestFilter
	 * @return
	 */
	long getCodeGroupListSize(RequestBO requestFilter);

}
