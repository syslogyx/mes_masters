package com.syslogyx.service.master;

import com.syslogyx.exception.ApplicationException;

/**
 * For Fetch the MasterActivityLog data in db
 * 
 * @author Palash
 *
 */
public interface IMasterActivityService {

	Object getMasterById(int master_id, int page, int limit) throws ApplicationException;

}
