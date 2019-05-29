package com.syslogyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.syslogyx.bo.ResponseBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.user.IUserDAO;

import com.syslogyx.model.user.UserDO;
import com.syslogyx.utility.Utils;

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
		responseBO.setPagination(Utils.getPagination(current_page, listSize, limit));
		return responseBO;
	}

	/**
	 * Return the Text of Status according to it's value
	 * 
	 * @param status
	 *            : status value in integer
	 * @return
	 */
	public String getStatusString(int status) {
		switch (status) {
		case IConstants.STATUS_ACTIVE:
			return "Active";

		case IConstants.STATUS_INACTIVE:
			return "Inactive";

		default:
			break;
		}
		return null;
	}

	/**
	 * Return the Text of priority according to it's value
	 * 
	 * @param priority
	 *            : priority value in integer
	 * @return
	 */
	public String getPriorityString(int priority) {
		switch (priority) {
		case IConstants.IPriority.LOW:
			return "Low";

		case IConstants.IPriority.MEDIUM:
			return "Medium";

		case IConstants.IPriority.HIGH:
			return "High";

		default:
			break;
		}
		return null;
	}
	
	/**
	 * Return the Text of OSP according to it's value
	 * 
	 * @param osp
	 *            : osp value in integer
	 * @return
	 */
	public String getOSPString(int osp) {
		switch (osp) {
		case IConstants.OSP_IDENTIFIER.YES:
			return "YES";

		case IConstants.OSP_IDENTIFIER.NO:
			return "NO";

		default:
			break;
		}
		return null;
	}

}
