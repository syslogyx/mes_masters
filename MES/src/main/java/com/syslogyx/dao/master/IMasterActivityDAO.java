package com.syslogyx.dao.master;

import java.util.List;

/**
 * Fetch Data from CodeGroupActivityList in DB
 * 
 * @author Palash
 *
 */
public interface IMasterActivityDAO {

	/**
	 * For Count the Total number of data in Masters Activity Log Table
	 * 
	 * @return
	 */
	long getMastersSize(int master_id);

	/**
	 * Validate Master Id and Fetch the Respected Master Activity Log list with
	 * pagination
	 * 
	 * @param master_id
	 * @param page
	 * @param limit
	 * @param invalidMasterId
	 * @return
	 */
	List<?> getMastersActivitylogList(int master_id, int page, int limit, String invalidMasterId);

}
