package com.syslogyx.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface IUserDAO extends JpaRepository<UserDO, Integer> {

	/**
	 * Find User by it's Username
	 * 
	 * @return
	 */
	UserDO findByUsername(String username);


}
