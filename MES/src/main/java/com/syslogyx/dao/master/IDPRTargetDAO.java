package com.syslogyx.dao.master;

import org.springframework.data.repository.CrudRepository;

import com.syslogyx.model.masters.DPRTargetDO;

/**
 * This Interface is used to communicating with db
 * 
 * @author namrata
 *
 */
public interface IDPRTargetDAO extends CrudRepository<DPRTargetDO, Integer> {

}
