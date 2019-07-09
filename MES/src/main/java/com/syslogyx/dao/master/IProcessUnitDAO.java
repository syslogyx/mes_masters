package com.syslogyx.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.model.masters.ProcessUnitDO;

/**
 * ProcessUnit DAO Repository to implement all ProcessUnit Table Related
 * Operations
 * 
 * @author palash
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface IProcessUnitDAO extends JpaRepository<ProcessUnitDO, Integer> {

}
