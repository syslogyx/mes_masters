	package com.syslogyx.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.syslogyx.exception.ApplicationException;
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

	/**
	 * Fetch the UserDO list from db
	 * 
	 * @return
	 */
	List<UserDO> findAll();

	void delete(long id);

	UserDO findById(Long id);

	/**
	 * Validate and Save UserDO data to db
	 * 
	 * @param userDO
	 * @throws Exception 
	 */
	void saveUser(UserDO userDO) throws ApplicationException, Exception;

	/**
	 * for fetch the current user name
	 * @param username
	 * @return
	 */
	UserDetails loadUserByUsername(String username);

}
