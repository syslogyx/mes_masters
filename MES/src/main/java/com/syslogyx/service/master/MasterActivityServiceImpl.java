package com.syslogyx.service.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.master.IMasterActivityDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.model.masters.activitylog.CodeGroupDOActivityLog;
import com.syslogyx.service.BaseService;

/**
 * This class is used for implementing business logic related to MasterActivity Module
 * 
 * @author palash
 *
 */
@Service
@Transactional(rollbackFor = { ApplicationException.class, Exception.class })
public class MasterActivityServiceImpl extends BaseService implements IMasterActivityService {

	@Autowired
	private IMasterActivityDAO iMasterActivityDAO;
	
	@Override
	public Object listCodeGroupActivity(RequestBO requestFilter, int page, int limit) {
		
		List<CodeGroupDOActivityLog> codeGroupsActivity = iMasterActivityDAO.getCodeGroupActivityList(requestFilter, page, limit);

		if (codeGroupsActivity != null && !codeGroupsActivity.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = iMasterActivityDAO.getCodeGroupActivityListSize(requestFilter);

				return generatePaginationResponse(codeGroupsActivity, listSize, page, limit);
			}
			return codeGroupsActivity;
		}

		return null;
		
	}

}
