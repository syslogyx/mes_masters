package com.syslogyx.service.master;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;

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

}
