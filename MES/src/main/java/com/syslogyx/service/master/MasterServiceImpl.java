package com.syslogyx.service.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.master.ICodeGroupDAO;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.user.UserDO;
import com.syslogyx.service.BaseService;

/**
 * This class is used for implementing business logic related to Master Module
 * 
 * @author palash
 *
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class MasterServiceImpl extends BaseService implements IMasterService {

	@Autowired
	private ICodeGroupDAO iCodeGroupDAO;

	@Autowired
	private IMasterDAO masterDAO;

	@Override
	public void createGroupCode(CodeGroupDO codeGroupDO) throws ApplicationException {

		int code_groupId = codeGroupDO.getId();
		String group_code = codeGroupDO.getGroup_code();
		if (code_groupId > 0) {
			CodeGroupDO findById = iCodeGroupDAO.findById(code_groupId);
			if (findById == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_GROUP_CODE);
		}

		// validate the group code if already exists in database or not
		CodeGroupDO existingCodeGroup = iCodeGroupDAO.findByGroupCode(group_code);

		if (existingCodeGroup != null && existingCodeGroup.getId() != code_groupId)
			throw new ApplicationException(IResponseCodes.EXISTING_ENTITY, IResponseMessages.EXISTING_GROUP_CODE);

		UserDO loggedInUser = getLoggedInUser();
		codeGroupDO.setCreated_by(loggedInUser);
		codeGroupDO.setUpdated_by(loggedInUser);
		codeGroupDO.setStatus(IConstants.STATUS_ACTIVE);
		iCodeGroupDAO.save(codeGroupDO);

	}

	@Override
	public Object listCodeGroup(RequestBO requestFilter, int page, int limit) throws ApplicationException {

		List<CodeGroupDO> codeGroups = masterDAO.getCodeGroupList(requestFilter, page, limit);

		if (codeGroups != null && !codeGroups.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getCodeGroupListSize(requestFilter);

				return generatePaginationResponse(codeGroups, listSize, page, limit);
			}
			return codeGroups;
		}

		return null;
	}

	@Override
	public void updateStatus(int code_group_id, int status) throws ApplicationException {

		CodeGroupDO codeGroupDO = iCodeGroupDAO.findById(code_group_id);
		if (codeGroupDO == null)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_GROUP_CODE);

		if (status == IConstants.STATUS_INACTIVE || status == IConstants.STATUS_ACTIVE) {
			codeGroupDO.setStatus(status);
			iCodeGroupDAO.save(codeGroupDO);
		} else {
			throw new ApplicationException(IResponseCodes.INVALID_STATUS, IResponseMessages.INVALID_STATUS);
		}
	}

}
