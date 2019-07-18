package com.syslogyx.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.model.masters.MastersDO;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface IMastersListDAO extends JpaRepository<MastersDO, Integer>{

	
}
