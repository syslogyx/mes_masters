package com.syslogyx.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syslogyx.model.user.UserDO;

public interface IUserDO extends JpaRepository<UserDO, Integer> {

	/**
	 * To Fetch the UserDO by Id
	 * 
	 * @param id
	 * @return
	 */
	UserDO findById(int id);

}
