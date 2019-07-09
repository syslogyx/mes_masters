package com.syslogyx.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.dao.role.IRoleDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.user.RoleDO;
import com.syslogyx.service.BaseService;


/**
 * Service Layer Implementation Class to Handle the Business Logic Related to
 * Role Module
 * 
 * @author palash
 *
 */

@Service(value = "roleService")
@Transactional(rollbackFor = { ApplicationException.class, Exception.class })
public class IRoleServiceImpl  extends BaseService implements IRoleService {

	@Autowired
	private IRoleDAO roleDao;

	
	public List<RoleDO> getRoleList() {
		return (List<RoleDO>) roleDao.findAll();
	}
}
