package com.syslogyx.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.model.user.UserDO;

/**
 * User DAO Repository to implement all User Table Related Operations
 * 
 * @author namrata
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface IUserDAO extends CrudRepository<UserDO, Integer> {

	/**
	 * Find User by it's Username
	 * 
	 * @param username
	 *            : value of username
	 * @return
	 */
	UserDO findByUsername(String username);

}
