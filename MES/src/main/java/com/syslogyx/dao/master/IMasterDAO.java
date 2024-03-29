package com.syslogyx.dao.master;

import java.util.List;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.dao.base.IBaseDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.ElongationDO;
import com.syslogyx.model.masters.LeadTimeDO;
import com.syslogyx.model.masters.MastersDO;
import com.syslogyx.model.masters.ProcessFamilyDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.ProductDefDO;
import com.syslogyx.model.masters.ShelfLifeDO;
import com.syslogyx.model.masters.ShrinkageDO;
import com.syslogyx.model.masters.ThicknessDO;
import com.syslogyx.model.masters.TrimmingDO;
import com.syslogyx.model.user.UserDO;

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
	 * @throws ApplicationException
	 */
	List<CodeGroupDO> findMastersList(String master_name) throws ApplicationException;

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

	/**
	 * Fetch the Elongation List according to the Filter and Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ElongationDO> getElongationList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Elongation List Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getElongationListSize(RequestBO requestFilter);

	/**
	 * Fetch the ProcessFamily list according to the Filter and Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ProcessFamilyDO> getProcessFamilyList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Process Family List Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getProcessFamilyListSize(RequestBO requestFilter);

	/**
	 * Fetch the ProcessUnit list according to the Filter and Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ProcessUnitDO> getProcessUnitList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Process Unit List Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getProcessUnitListSize(RequestBO requestFilter);

	/**
	 * Fetch the Product Definition list according to the filter and Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ProductDefDO> getProductList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Product Definition List Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getProductSize(RequestBO requestFilter);

	/**
	 * Fetch the Shelf Life List according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ShelfLifeDO> getShelfLifeList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Shelf Lfe List Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getShelfLifeSize(RequestBO requestFilter);

	/**
	 * Fetch the Shrink Age list according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ShrinkageDO> getShrinkAgeList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Shrinkage list Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getShrinkageSize(RequestBO requestFilter);

	/**
	 * Fetch the Trimming list according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<TrimmingDO> getTrimmingList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Trimming list Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getTrimmingSize(RequestBO requestFilter);

	/**
	 * Fetch the Thickness list according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<ThicknessDO> getThicknessList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Thickness list Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getThicknessSize(RequestBO requestFilter);

	/**
	 * Fetch the Masters List according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<MastersDO> getMastersList(RequestBO requestFilter, int page, int limit);

	/**
	 * Fetch the Thickness list Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getMastersSize(RequestBO requestFilter);

	/**
	 * Fetch the Masters List according to the Pagination and Filter
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	List<UserDO> getUsersList(RequestBO requestFilter, int page, int limit);
	
	/**
	 * Fetch the User list Size according to the Filter
	 * 
	 * @param requestFilter
	 * @return
	 */
	long getUsersSize(RequestBO requestFilter);

}
