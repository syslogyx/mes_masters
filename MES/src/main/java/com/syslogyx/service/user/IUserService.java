package com.syslogyx.service.user;

import com.syslogyx.model.user.UserDO;

/**
 * User Service Interface to define all User Related Business logic methods
 * 
 * @author namrata
 *
 */
public interface IUserService {

	/**
	 * Find User by Username
	 * 
	 * @param username
	 *            : username to search in db
	 * @return
	 */
	UserDO findOne(String username);

}
