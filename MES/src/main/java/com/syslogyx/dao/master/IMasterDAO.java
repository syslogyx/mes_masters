package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;

/**
 * This interface is used for communicating with db
 * 
 * @author Palash
 *
 */
public interface IMasterDAO {

	/**
	 * This method is used for retrieving CodeGroup table data for pagination and
	 * quick finder
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<CodeGroupDO> getCodeGroupList(RequestBO requestFilter, int page, int limit);

	/**
	 * This method is used for count all Numbers of rows in CodeGroup table from db
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getCodeGroupListSize(RequestBO requestFilter);

	/**
	 * For Retrieving Campaign table data for Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<CampaignDO> getCampaignList(RequestBO requestFilter, int page, int limit);

	/**
	 * For count number of rows in campaign table for pagination
	 * @param requestFilter
	 * @return
	 */
	long getCampaignListSize(RequestBO requestFilter);

}
