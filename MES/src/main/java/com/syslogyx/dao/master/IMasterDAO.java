package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.dao.base.IBaseDAO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.LeadTimeDO;

/**
 * This interface is used for communicating with db
 * 
 * @author Palash
 *
 */
public interface IMasterDAO extends IBaseDAO {

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
	 * Fetch the List of Masters from DB table according to the Master name
	 * specified in the parameter
	 * 
	 * @param master_name
	 *            : identifier for returning the list accordingly
	 * @param class1
	 * @return
	 */
	List<CodeGroupDO> findMastersList(String master_name);

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
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getCampaignListSize(RequestBO requestFilter);

	/**
	 * Fetch the list of DPR Targets according to the filter and Pagination provided
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<DPRTargetDO> getDPRTargetList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the list size of DPR Targets according to the filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getDPRTargetListSize(RequestBO requestFilter);

//	/**
//	 * for fetch LeadTime list from db
//	 * 
//	 * @param requestFilter
//	 * @param page
//	 * @param limit
//	 * @return
//	 */
//	List<LeadTimeDO> getLeadTimeList(RequestBO requestFilter, int page, int limit);

	/**
	 * for count number of rows in LeadTime table from db
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getLeadTimeListSize(RequestBO requestFilter);

	/**
	 * Fetch the list of Lead Time according to the filter and Pagination provided
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<LeadTimeDO> getLeadTimeList(RequestBO requestFilter, int page, int limit);

}
