package com.syslogyx.service.master;

import java.util.List;

import com.syslogyx.exception.ApplicationException;
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

	

	

}
