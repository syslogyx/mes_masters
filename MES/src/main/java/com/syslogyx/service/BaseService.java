package com.syslogyx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.syslogyx.dao.user.IUserDAO;
import com.syslogyx.model.user.UserDO;

/**
 * This class is used for common method of service class
 * 
 * @author namrata
 *
 */
public class BaseService {

	@Autowired
	private IUserDAO iUserDAO;

	/**
	 * Get the instance of Logged in user from Current Authentication details
	 * 
	 * @return
	 */
	public UserDO getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			return iUserDAO.findByUsername(username);
		}
		return null;

	}
}
