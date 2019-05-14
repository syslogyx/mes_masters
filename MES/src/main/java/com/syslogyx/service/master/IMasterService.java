package com.syslogyx.service.master;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.ProcessUnitDO;

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
	 */
	void createGroupCode(CodeGroupDO codeGroupDO) throws ApplicationException;

	/**
	 * This method used for retrieving list data of CodeGroup table from database
	 * 
	 * @param requestFilter
	 * @param page
	 * @param limit
	 * @return
	 * @throws ApplicationException
	 */
	Object listCodeGroup(RequestBO requestFilter, int page, int limit) throws ApplicationException;

	/**
	 * This method used for retrieving CodeGroup by Id and update status
	 * 
	 * @param id
	 *            : Retrieving CodeGroup id
	 * @param status
	 *            : Update status
	 * @throws ApplicationException
	 */
	void updateStatus(int code_group_id, int status) throws ApplicationException;

	/**
	 * This method used for retrieving CodeGroup by Id
	 * 
	 * @param code_group_id
	 * @return
	 * @throws ApplicationException
	 */
	CodeGroupDO getCodeGroupId(int code_group_id) throws ApplicationException;

	/**
	 * This Method is used to validation on Campaign and Save Campaign Data in db
	 * 
	 * @param campaignDO
	 *            : contains campaign Data provided by users
	 * @throws ApplicationException
	 */
	void createCampaign(CampaignDO campaignDO) throws ApplicationException;

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
	 * For retrieving Campaign by id and update status
	 * 
	 * @param camp_id
	 * @param status
	 */
	void updateCampaignStatus(int camp_id, int status) throws ApplicationException;

	/**
	 * For Retrieving Campaign by Id
	 * 
	 * @param camp_id
	 * @return
	 */
	CampaignDO getCampaignId(int camp_id) throws ApplicationException;

}
