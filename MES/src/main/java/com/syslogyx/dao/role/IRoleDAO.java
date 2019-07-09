package com.syslogyx.dao.role;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syslogyx.model.user.RoleDO;


/**
 * User DAO Interface for all Database Operations related to Role Module
 * 
 * @author namrata
 *
 */
@Repository
@Transactional
public interface IRoleDAO extends CrudRepository<RoleDO, Long> {

}

