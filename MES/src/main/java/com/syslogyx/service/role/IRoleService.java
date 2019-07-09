package com.syslogyx.service.role;

import java.util.List;

import com.syslogyx.model.user.RoleDO;

/**
 * Service Layer to Handle the Business Logic Related to Role Module
 * 
 * @author palash
 *
 */
public interface IRoleService {

	/**
	 * Fetch the list of Role
	 * 
	 * @return: list of RoleDO
	 */
	public List<RoleDO> getRoleList();

}
