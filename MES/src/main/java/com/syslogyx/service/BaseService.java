package com.syslogyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.syslogyx.bo.Pagination;
import com.syslogyx.bo.ResponseBO;
import com.syslogyx.dao.master.IProcessUnitDAO;
import com.syslogyx.dao.user.IUserDAO;

import com.syslogyx.model.masters.ProcessUnitDO;
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
	 * 
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

	/**
	 * Prepare the Object of Response with Pagination Details
	 * 
	 * @param list
	 * @param listSize
	 * @param current_page
	 * @param limit
	 * @return
	 */
	public ResponseBO generatePaginationResponse(List list, long listSize, int current_page, int limit) {
		ResponseBO responseBO = new ResponseBO();
		responseBO.setList(list);
		responseBO.setPagination(new Pagination(listSize, current_page, limit));
		return responseBO;
	}
}
