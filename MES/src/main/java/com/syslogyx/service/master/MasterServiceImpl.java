package com.syslogyx.service.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.dao.master.ICampaignDAO;
import com.syslogyx.dao.master.ICodeGroupDAO;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.dao.master.IProcessUnitDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.ProcessUnitDO;
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
	private IProcessUnitDAO iprocessUnitDAO;

	@Autowired
	private ICampaignDAO iCampaignDAO;

	@Autowired
	private IMasterDAO masterDAO;

	@Override
	public void createGroupCode(CodeGroupDO codeGroupDO) throws ApplicationException {

		int code_groupId = codeGroupDO.getId();
		String group_code = codeGroupDO.getGroup_code();
		if (code_groupId > 0) {
			CodeGroupDO findById = iCodeGroupDAO.findById(code_groupId);
			if (findById == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_GROUP_CODE_ID);
		}

		// validate the group code if already exists in database or not
		CodeGroupDO existingCodeGroup = iCodeGroupDAO.findByGroupCode(group_code);

		if (existingCodeGroup != null && existingCodeGroup.getId() != code_groupId)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_GROUP_CODE);

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
		int presentStatus = codeGroupDO.getStatus();
		if (codeGroupDO == null)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_GROUP_CODE_ID);

		// for validate status
		validateStatus(status);

		// for validate update status
		validateOldStatus(status, presentStatus);
		codeGroupDO.setStatus(status);
		iCodeGroupDAO.save(codeGroupDO);
	}

	/**
	 * For check status is already Inactive or not
	 * 
	 * @param status
	 * @param presentStatus
	 * @throws ApplicationException
	 */
	private void validateOldStatus(int status, int presentStatus) throws ApplicationException {

		if (status == presentStatus && presentStatus == IConstants.STATUS_INACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INACTIVE_STATUS);

		if (status == presentStatus && presentStatus == IConstants.STATUS_ACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_STATUS);
	}

	/**
	 * For Validate status
	 * 
	 * @param status
	 * @throws ApplicationException
	 */
	private void validateStatus(int status) throws ApplicationException {

		if (status != IConstants.STATUS_INACTIVE && status != IConstants.STATUS_ACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_STATUS, IResponseMessages.INVALID_STATUS);

	}

	@Override
	public CodeGroupDO getCodeGroupId(int code_group_id) throws ApplicationException {

		CodeGroupDO codeGroupDO = iCodeGroupDAO.findById(code_group_id);
		if (codeGroupDO == null || codeGroupDO.getStatus() == IConstants.STATUS_INACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_GROUP_CODE_ID);

		return codeGroupDO;

	}

	@Override
	public void createCampaign(CampaignDO campaignDO) throws ApplicationException {

		int camp_id = campaignDO.getId();
		int priority_level = campaignDO.getPriority_level();
		String campaign_id = campaignDO.getCampaign_id();
		int hold_unit_id = campaignDO.getHold_unit_id();

		// it's use to differentiation for save and update
		if (camp_id > IConstants.VALUE_ZERO) {
			CampaignDO findByCampaignId = iCampaignDAO.findById(camp_id);
			if (findByCampaignId == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_CAMPAIGN_ID);
		}

		// Validate Campaign id
		if (campaign_id == null || campaign_id.isEmpty())
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EMPTY_CAMPAIGN_ID);

		// validate the campaign if already exists in database or not
		CampaignDO existingCampaignId = iCampaignDAO.findByCampaignId(campaign_id);

		if (existingCampaignId != null && existingCampaignId.getId() != camp_id)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_CAMPAIGN_ID);

		// for validate processUnit id from db
		if (hold_unit_id > IConstants.VALUE_ZERO) {
			ProcessUnitDO findByProcessUnitId = iprocessUnitDAO.findById(hold_unit_id).get();
			if (findByProcessUnitId == null)
				throw new ApplicationException(IResponseCodes.INVALID_ENTITY,
						IResponseMessages.INVALID_PROCESS_UNIT_ID);
			campaignDO.setHold_unit(findByProcessUnitId);
		}

		validatePriority(priority_level);

		UserDO loggedInUser = getLoggedInUser();
		campaignDO.setCreated_by(loggedInUser);
		campaignDO.setUpdated_by(loggedInUser);
		campaignDO.setStatus(IConstants.STATUS_ACTIVE);
		iCampaignDAO.save(campaignDO);

	}

	/**
	 * For validate priority
	 * 
	 * @param priority_level
	 * @throws ApplicationException
	 */
	private void validatePriority(int priority_level) throws ApplicationException {
		if (priority_level != IConstants.IPriority.HIGH && priority_level != IConstants.IPriority.MEDIUM
				&& priority_level != IConstants.IPriority.LOW)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_PRIORITY);
	}

	@Override
	public Object getCampaignList(RequestBO requestFilter, int page, int limit) throws ApplicationException {

		List<CampaignDO> campaignDO = masterDAO.getCampaignList(requestFilter, page, limit);

		if (campaignDO != null && !campaignDO.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getCampaignListSize(requestFilter);

				return generatePaginationResponse(campaignDO, listSize, page, limit);
			}
			return campaignDO;
		}
		return null;
	}

	@Override
	public void updateCampaignStatus(int camp_id, int status) throws ApplicationException {

		CampaignDO campaignDO = iCampaignDAO.findById(camp_id);

		if (campaignDO == null)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_CAMPAIGN_ID);

		int presentStatus = campaignDO.getStatus();
		// For validate Status
		validateStatus(status);

		// for validate update status
		validateOldStatus(status, presentStatus);

		campaignDO.setStatus(status);
		iCampaignDAO.save(campaignDO);

	}

	@Override
	public CampaignDO getCampaignId(int camp_id) throws ApplicationException {

		CampaignDO campaignDO = iCampaignDAO.findById(camp_id);

		if (campaignDO == null || campaignDO.getStatus() == IConstants.STATUS_INACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_CAMPAIGN_ID);

		return campaignDO;
	}

}
