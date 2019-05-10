package com.syslogyx.service.master;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IFileHeaderConstants;
import com.syslogyx.dao.master.ICodeGroupDAO;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.user.UserDO;
import com.syslogyx.service.BaseService;
import com.syslogyx.utility.Utils;

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

	@Override
	public String exportListToExcel(String master_name) throws ApplicationException {
		List<CodeGroupDO> codeGroupList = masterDAO.findMastersList(master_name);

		if (codeGroupList != null && !codeGroupList.isEmpty()) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(master_name);

			// method call to set the Header Row with Cell style and font
			Utils.writeToHeaderRow(sheet, IFileHeaderConstants.getCodeGroupListHeaders(), workbook.createCellStyle(),
					workbook.createFont());

			// method call to set data rows
			processExportingCodeGroupList(sheet, codeGroupList);

			String filename = Utils.getFilePath(IConstants.EXCEL_BASE_PATH, master_name);
			return Utils.writeDataToWorkbook(workbook, filename);
		} else {
			throw new ApplicationException(IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		}
	}

	/**
	 * Set the Field value from Code Group Object to it's respective index number in
	 * the excel sheet
	 * 
	 * @param sheet
	 * @param codeGroupList
	 */
	private void processExportingCodeGroupList(HSSFSheet sheet, List<CodeGroupDO> codeGroupList) {
		for (int index = 0; index < codeGroupList.size(); index++) {
			CodeGroupDO codeGroupDO = codeGroupList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(codeGroupDO.getGroup_code());
			row.createCell(2).setCellValue(codeGroupDO.getGroup_desc());

			row.createCell(3).setCellValue(
					Utils.getFormatedDate(codeGroupDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			row.createCell(4).setCellValue(codeGroupDO.getUpdated_by_name());
			row.createCell(5).setCellValue(getStatusString(codeGroupDO.getStatus()));
		}
	}

}
