package com.syslogyx.service.master;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;

/**
 * This class is used for MasterService business logic related to Master module
 * 
 * @author palash
 *
 */
public interface IMasterService {

	/**
	 * This Method is used to validation on CodeGroup and Save CodeGroup Data
	 * 
	 * @param codeGroupDO
	 *            : contains codeGroup Data provided by users
	 * @throws ApplicationException
	 * @throws Exception
	 */
	void createGroupCode(CodeGroupDO codeGroupDO) throws ApplicationException, Exception;

	/**
	 * This method used for retrieving list data of CodeGroup table from database
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 * @throws ApplicationException
	 */
	Object listCodeGroup(RequestBO requestFilter, int page, int limit);

	/**
	 * This Method is used to validation on DPRTargetDO and Save CodeGroup Data
	 * 
	 * @param dprTargetDO
	 * @throws ApplicationException
	 * @throws Exception
	 */
	void createDPRTarget(DPRTargetDO dprTargetDO) throws ApplicationException, Exception;

	/**
	 * Fetch the DPT Target list according to the provided filters along with the
	 * Pagination
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	Object getDPRTargetList(RequestBO requestFilter, int page, int limit);

	/**
	 * This Method is used to validation on Campaign and Save Campaign Data in db
	 * 
	 * @param campaignDO
	 *            : contains campaign Data provided by users
	 * @throws ApplicationException
	 * @throws Exception
	 */
	void createCampaign(CampaignDO campaignDO) throws ApplicationException, Exception;

	/**
	 * For retrieving list data of Campaign table from database
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 */
	Object getCampaignList(RequestBO requestFilter, int page, int limit) throws ApplicationException;

	/**
	 * Get the Master Entity by provided Id
	 * 
	 * @param master_name
	 * @param master_id
	 * @return
	 * @throws ApplicationException
	 * @throws Exception
	 */
	Object getMasterById(String master_name, int master_id) throws ApplicationException, Exception;

	/**
	 * This method used for retrieving Master Entity by Id and update status
	 * 
	 * @param master_name
	 *            : identifier for master entity
	 * @param master_id
	 *            : Id of the respective master
	 * @param status
	 *            : Update status
	 * @throws ApplicationException
	 * @throws Exception
	 */
	void updateMastersStatus(String master_name, int master_id, int status) throws ApplicationException, Exception;

	/**
	 * export the Master's list data to Excel sheet and return the Path of Excel
	 * 
	 * @param master_name
	 * @return
	 * @throws ApplicationException
	 */
	String exportListToExcel(String master_name) throws ApplicationException;

	/**
	 * export the Master's list data to PDF sheet and return the Path of PDF
	 * 
	 * @param master_name
	 * @return
	 * @throws ApplicationException
	 */
	String exportListToPDF(String master_name) throws ApplicationException;

	/**
	 * This method used for retrieving CodeGroup by Id
	 * 
	 * @param code_group_id
	 * @return
	 * @throws ApplicationException
	 */
	// CodeGroupDO getCodeGroupId(int code_group_id) throws ApplicationException;

	/**
	 * For retrieving Campaign by id and update status
	 * 
	 * @param camp_id
	 * @param status
	 */
	// void updateCampaignStatus(int camp_id, int status) throws
	// ApplicationException;

	/**
	 * For Retrieving Campaign by Id
	 * 
	 * @param camp_id
	 * @return
	 */
	// CampaignDO getCampaignId(int camp_id) throws ApplicationException;

}
