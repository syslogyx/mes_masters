package com.syslogyx.service.master;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.exception.ApplicationException;
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
	 * This Method is used to validation on DPRTargetDO and Save CodeGroup Data
	 * 
	 * @param dprTargetDO
	 * @throws ApplicationException
	 */
	void createDPRTarget(DPRTargetDO dprTargetDO) throws ApplicationException;

}
