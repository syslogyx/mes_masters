package com.syslogyx.dao.master;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syslogyx.model.masters.EmployeeDO;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeDO, Integer> {
	
	
}
