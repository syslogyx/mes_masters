package com.syslogyx.service.master;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IFileHeaderConstants;
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.LeadTimeDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.ProductDefDO;
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
@Transactional(rollbackFor = { ApplicationException.class, Exception.class })
public class MasterServiceImpl extends BaseService implements IMasterService {

	@Autowired
	private IMasterDAO masterDAO;

	@Override
	public void createGroupCode(CodeGroupDO codeGroupDO) throws ApplicationException, Exception {

		int code_groupId = codeGroupDO.getId();
		String group_code = codeGroupDO.getGroup_code();

		// validate code group id
		masterDAO.validateEntityById(CodeGroupDO.class, code_groupId, IResponseMessages.INVALID_GROUP_CODE_ID);

		// validate the group code if already exists in database or not
		CodeGroupDO existingCodeGroup = (CodeGroupDO) masterDAO.getEntityByPropertyName(CodeGroupDO.class,
				IPropertyConstant.GROUP_CODE, group_code);

		if (existingCodeGroup != null && existingCodeGroup.getId() != code_groupId)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_GROUP_CODE);

		UserDO loggedInUser = getLoggedInUser();
		codeGroupDO.setCreated_by(loggedInUser);
		codeGroupDO.setUpdated_by(loggedInUser);
		codeGroupDO.setStatus(IConstants.STATUS_ACTIVE);
		masterDAO.mergeEntity(codeGroupDO);

	}

	@Override
	public Object listCodeGroup(RequestBO requestFilter, int page, int limit) {

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
	public void createDPRTarget(DPRTargetDO dprTargetDO) throws ApplicationException, Exception {

		int dpr_id = dprTargetDO.getId();
		int unit_id = dprTargetDO.getUnit_id();
		int product_id = dprTargetDO.getProduct_id();

		// if the dpr id is provided, validate it in DB
		masterDAO.validateEntityById(DPRTargetDO.class, dpr_id, IResponseMessages.INVALID_DPR_TARGET_ID);

		// validate and set unit id
		ProcessUnitDO processUnitDO = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class, unit_id,
				IResponseMessages.INVALID_PROCESS_UNIT_ID);
		dprTargetDO.setUnit(processUnitDO);

		// validate and set product id
		ProductDefDO productDefDO = (ProductDefDO) masterDAO.validateEntityById(ProductDefDO.class, product_id,
				IResponseMessages.INVALID_PRODUCT_ID);
		dprTargetDO.setProduct(productDefDO);

		UserDO loggedInUser = getLoggedInUser();
		dprTargetDO.setCreated_by(loggedInUser);
		dprTargetDO.setUpdated_by(loggedInUser);
		dprTargetDO.setStatus(IConstants.STATUS_ACTIVE);
		masterDAO.mergeEntity(dprTargetDO);
	}

	@Override
	public Object getDPRTargetList(RequestBO requestFilter, int page, int limit) {
		List<DPRTargetDO> dprTargets = masterDAO.getDPRTargetList(requestFilter, page, limit);

		if (dprTargets != null && !dprTargets.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getDPRTargetListSize(requestFilter);

				return generatePaginationResponse(dprTargets, listSize, page, limit);
			}
			return dprTargets;
		}

		return null;
	}

	@Override
	public void createCampaign(CampaignDO campaignDO) throws ApplicationException, Exception {

		int camp_id = campaignDO.getId();
		int priority_level = campaignDO.getPriority_level();
		String campaign_id = campaignDO.getCampaign_id();
		int hold_unit_id = campaignDO.getHold_unit_id();

		// it's use to differentiation for save and update
		masterDAO.validateEntityById(CampaignDO.class, camp_id, IResponseMessages.INVALID_CAMPAIGN_ID);

		// Validate Campaign id
		if (campaign_id == null || campaign_id.isEmpty())
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EMPTY_CAMPAIGN_ID);

		// validate the campaign if already exists in database or not
		CampaignDO existingCampaignId = (CampaignDO) masterDAO.getEntityByPropertyName(CampaignDO.class,
				IPropertyConstant.CAMPAIGN_ID, campaign_id);

		if (existingCampaignId != null && existingCampaignId.getId() != camp_id)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_CAMPAIGN_ID);

		// for validate processUnit id from db
		ProcessUnitDO hold_unit = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class, hold_unit_id,
				IResponseMessages.INVALID_PROCESS_UNIT_ID);
		campaignDO.setHold_unit(hold_unit);

		validatePriority(priority_level);

		UserDO loggedInUser = getLoggedInUser();
		campaignDO.setCreated_by(loggedInUser);
		campaignDO.setUpdated_by(loggedInUser);
		campaignDO.setStatus(IConstants.STATUS_ACTIVE);
		masterDAO.mergeEntity(campaignDO);

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
	public String exportListToExcel(String master_name) throws ApplicationException {
		List mastersList = masterDAO.findMastersList(master_name);

		if (mastersList != null && !mastersList.isEmpty()) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(master_name);
			List<String> headerList = IFileHeaderConstants.getMastersHeaderList(master_name);

			// method call to set the Header Row with Cell style and font
			Utils.writeToExcelHeaderRow(sheet, headerList, workbook.createCellStyle(), workbook.createFont());

			// method call to set data rows
			processExcelToExportingDataRows(sheet, mastersList, master_name);

			String filename = Utils.getFilePath(IConstants.EXCEL_BASE_PATH, master_name);
			return Utils.writeDataToWorkbook(workbook, filename);
		} else {
			throw new ApplicationException(IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		}
	}

	@Override
	public void updateMastersStatus(String master_name, int master_id, int status)
			throws ApplicationException, Exception {

		// validate the status constant
		validateStatus(status);

		Class<?> masterByType = getMasterByType(master_name);

		Object master = masterDAO.getEntityById(masterByType, master_id);

		validateAndUpdateStatus(masterByType, master, status);

	}

	/**
	 * Validate the Value of Status provided from the API call
	 * 
	 * @param status
	 * @throws ApplicationException
	 */
	private void validateStatus(int status) throws ApplicationException {
		if (status != IConstants.STATUS_INACTIVE && status != IConstants.STATUS_ACTIVE) {
			throw new ApplicationException(IResponseCodes.INVALID_STATUS, IResponseMessages.INVALID_STATUS);
		}
	}

	/**
	 * Process the Excel Sheet Data Rows According to the master_name specified
	 * 
	 * @param sheet
	 * @param mastersList
	 * @param master_name
	 */
	private void processExcelToExportingDataRows(HSSFSheet sheet, List mastersList, String master_name) {

		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			processExportingCodeGroupListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			processExportingCampaignListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			processExportingDPRTargetListExcel(sheet, mastersList);

	}

	/**
	 * Set the Field value from DPRTargetDO Object to it's respective index number
	 * in the excel sheet
	 * 
	 * @param sheet
	 * @param dprTargetList
	 */
	private void processExportingDPRTargetListExcel(HSSFSheet sheet, List<DPRTargetDO> dprTargetList) {
		for (int index = 0; index < dprTargetList.size(); index++) {
			DPRTargetDO dprTargetDO = dprTargetList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(dprTargetDO.getYear());
			row.createCell(2).setCellValue(dprTargetDO.getUnit_name());
			row.createCell(3).setCellValue(dprTargetDO.getProduct_name());
			row.createCell(4).setCellValue(dprTargetDO.getBusiness_plan_target());
			row.createCell(5).setCellValue(dprTargetDO.getInternal_target());
			row.createCell(6).setCellValue(dprTargetDO.getUpdated_by_name());
			row.createCell(7).setCellValue(
					Utils.getFormatedDate(dprTargetDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			row.createCell(8).setCellValue(getStatusString(dprTargetDO.getStatus()));
		}
	}

	/**
	 * Set the Field value from Campaign Object to it's respective index number in
	 * the excel sheet
	 * 
	 * @param sheet
	 * @param campaignList
	 */
	private void processExportingCampaignListExcel(HSSFSheet sheet, List<CampaignDO> campaignList) {
		for (int index = 0; index < campaignList.size(); index++) {
			CampaignDO campaignDO = campaignList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(campaignDO.getCampaign_id());
			row.createCell(2).setCellValue(campaignDO.getAttribute());
			row.createCell(3).setCellValue(campaignDO.getAim());
			row.createCell(4).setCellValue(campaignDO.getCapacity_min());
			row.createCell(5).setCellValue(campaignDO.getCapacity_max());
			row.createCell(6).setCellValue(campaignDO.getUpdated_by_name());
			row.createCell(7).setCellValue(
					Utils.getFormatedDate(campaignDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			row.createCell(8).setCellValue(getPriorityString(campaignDO.getPriority_level()));
			row.createCell(9).setCellValue(campaignDO.getHold_unit_name());
			row.createCell(10).setCellValue(getStatusString(campaignDO.getStatus()));
		}

	}

	/**
	 * Set the Field value from Code Group Object to it's respective index number in
	 * the excel sheet
	 * 
	 * @param sheet
	 * @param codeGroupList
	 */
	private void processExportingCodeGroupListExcel(HSSFSheet sheet, List<CodeGroupDO> codeGroupList) {
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

	@Override
	public String exportListToPDF(String master_name) throws ApplicationException {
		List mastersList = masterDAO.findMastersList(master_name);

		if (mastersList != null && !mastersList.isEmpty()) {
			Document document = new Document();

			PdfPTable table = new PdfPTable(setPDFwidth(master_name));

			List<String> headerList = IFileHeaderConstants.getMastersHeaderList(master_name);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			// method call to set the PDF Header row
			Utils.writeToPDFHeaderRow(table, headerList);

			// process to set the Data Rows according to the master name
			processPDFToExportingDataRows(table, mastersList, master_name);

			String filename = Utils.getFilePath(IConstants.PDF_BASE_PATH, master_name);
			return Utils.writeDataToPDF(document, table, filename);
		} else {
			throw new ApplicationException(IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		}
	}

	/**
	 * to set PDF table width according to Master Name
	 * 
	 * @param table
	 * @param master_name
	 * @return
	 */
	private float[] setPDFwidth(String master_name) {
		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			return new float[] { 1, 1, 2, 2, 2, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			return new float[] { 1, 3, 3, 3, 3, 3, 3, 3, 2, 2, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			return new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		return null;
	}

	/**
	 * Process the PDF Sheet Data Rows According to the master_name specified
	 * 
	 * @param table
	 * @param mastersList
	 * @param master_name
	 */
	private void processPDFToExportingDataRows(PdfPTable table, List mastersList, String master_name) {
		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			processExportingCodeGroupListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			processExportingCampaignListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			processExportingDPRTargetListPDF(table, mastersList);

	}

	/**
	 * Set the Field value from DPR Target Object to it's respective index number in
	 * the PDF Table
	 * 
	 * @param table
	 * @param dprTargetList
	 */
	private void processExportingDPRTargetListPDF(PdfPTable table, List<DPRTargetDO> dprTargetList) {
		for (int index = 0; index < dprTargetList.size(); index++) {
			DPRTargetDO dprTargetDO = dprTargetList.get(index);

			table.addCell(index + 1 + "");
			table.addCell(dprTargetDO.getYear());
			table.addCell(dprTargetDO.getUnit_name());
			table.addCell(dprTargetDO.getProduct_name());
			table.addCell(dprTargetDO.getBusiness_plan_target() + "");
			table.addCell(dprTargetDO.getInternal_target() + "");
			table.addCell(dprTargetDO.getUpdated_by_name());
			table.addCell(
					Utils.getFormatedDate(dprTargetDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			table.addCell(getStatusString(dprTargetDO.getStatus()));
		}
	}

	/**
	 * Set the Field value from Campaign Object to it's respective index number in
	 * the PDF Table
	 * 
	 * @param table
	 * @param campaignList
	 */
	private void processExportingCampaignListPDF(PdfPTable table, List<CampaignDO> campaignList) {

		for (int index = 0; index < campaignList.size(); index++) {
			CampaignDO campaignDO = campaignList.get(index);

			table.addCell(index + 1 + "");
			table.addCell(campaignDO.getCampaign_id());
			table.addCell(campaignDO.getAttribute());
			table.addCell(campaignDO.getAim());
			table.addCell(campaignDO.getCapacity_max() + "");
			table.addCell(campaignDO.getCapacity_min() + "");
			table.addCell(campaignDO.getUpdated_by_name());
			table.addCell(
					Utils.getFormatedDate(campaignDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			table.addCell(getPriorityString(campaignDO.getPriority_level()));
			table.addCell(campaignDO.getHold_unit_name());
			table.addCell(getStatusString(campaignDO.getStatus()));
		}
	}

	/**
	 * Set the Field value from Code Group Object to it's respective index number in
	 * the PDF Table
	 * 
	 * @param table
	 * @param codeGroupList
	 */
	private void processExportingCodeGroupListPDF(PdfPTable table, List<CodeGroupDO> codeGroupList) {
		for (int index = 0; index < codeGroupList.size(); index++) {
			CodeGroupDO codeGroupDO = codeGroupList.get(index);

			table.addCell(index + 1 + "");
			table.addCell(codeGroupDO.getGroup_code());
			table.addCell(codeGroupDO.getGroup_desc());
			table.addCell(
					Utils.getFormatedDate(codeGroupDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS));
			table.addCell(codeGroupDO.getUpdated_by_name());
			table.addCell(getStatusString(codeGroupDO.getStatus()));
		}
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

	/**
	 * Validate the New Status Against the current status
	 * 
	 * @param status
	 * @param presentStatus
	 * @throws ApplicationException
	 */
	private void validateOldStatus(int status, int presentStatus) throws ApplicationException {

		if (status == presentStatus && status == IConstants.STATUS_INACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_STATUS, IResponseMessages.INACTIVE_STATUS);
		else if (status == presentStatus && status == IConstants.STATUS_ACTIVE)
			throw new ApplicationException(IResponseCodes.INVALID_STATUS, IResponseMessages.INVALID_STATUS);
	}

	/**
	 * Return the Master Class name according to the provided master_name
	 * 
	 * @param master_name
	 * @return
	 * @throws ApplicationException
	 */
	private Class<?> getMasterByType(String master_name) throws ApplicationException {

		if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.CODE_GROUP))
			return CodeGroupDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.DPR_TARGET))
			return DPRTargetDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.CAMPAIGN))
			return CampaignDO.class;
		else
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.INVALID_MASTER_NAME);
	}

	/**
	 * Validate the Status provided to update against the existing status and update
	 * the Master object
	 * 
	 * @param masterByType
	 * @param master
	 * @param status
	 * @throws ApplicationException
	 */
	private void validateAndUpdateStatus(Class<?> masterByType, Object master, int status)
			throws ApplicationException, Exception {

		Field declaredField = masterByType.getDeclaredField(IPropertyConstant.STATUS);
		int oldStatus = (int) declaredField.get(master);

		validateOldStatus(oldStatus, status);

		declaredField.set(master, status);

		masterDAO.mergeEntity(master);
	}

	@Override
	public Object getMasterById(String master_name, int master_id) throws Exception {
		return masterDAO.validateEntityById(getMasterByType(master_name), master_id,
				IResponseMessages.INVALID_MASTER_ID);
	}

	@Override
	public void createLeadTime(LeadTimeDO leadTimeDO) throws ApplicationException, Exception {

		int leadTimeId = leadTimeDO.getId();
		
		String process_unit_name = leadTimeDO.getProcess_unit_name();
		

		// validate code group id
		masterDAO.validateEntityById(LeadTimeDO.class, leadTimeId, IResponseMessages.INVALID_LEAD_TIME_ID);

		ProcessUnitDO process_unit = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class,
				process_unit_name, IResponseMessages.INVALID_PROCESS_UNIT_ID);
		
		String unit = process_unit.getUnit();
		
		leadTimeDO.setProcess_unit_name(unit);
		

	

		UserDO loggedInUser = getLoggedInUser();
		leadTimeDO.setCreated_by(loggedInUser);
		leadTimeDO.setUpdated_by(loggedInUser);
		leadTimeDO.setStatus(IConstants.STATUS_ACTIVE);
		masterDAO.mergeEntity(leadTimeDO);

	}

	@Override
	public Object getLeadTimeList(RequestBO requestFilter, int page, int limit) throws ApplicationException, Exception {

		List<LeadTimeDO> leadTime = masterDAO.getLeadTimeList(requestFilter, page, limit);

		if (leadTime != null && !leadTime.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getCodeGroupListSize(requestFilter);

				return generatePaginationResponse(leadTime, listSize, page, limit);
			}
			return leadTime;
		}

		return null;
	}

	// @Override
	// public CampaignDO getCampaignId(int camp_id) throws ApplicationException {
	//
	// CampaignDO campaignDO = iCampaignDAO.findById(camp_id);
	//
	// if (campaignDO == null || campaignDO.getStatus() ==
	// IConstants.STATUS_INACTIVE)
	// throw new ApplicationException(IResponseCodes.INVALID_ENTITY,
	// IResponseMessages.INVALID_CAMPAIGN_ID);
	//
	// return campaignDO;
	// }

	// @Override
	// public void updateCampaignStatus(int camp_id, int status) throws
	// ApplicationException {
	//
	// CampaignDO campaignDO = iCampaignDAO.findById(camp_id);
	//
	// if (campaignDO == null)
	// throw new ApplicationException(IResponseCodes.INVALID_ENTITY,
	// IResponseMessages.INVALID_CAMPAIGN_ID);
	//
	// int presentStatus = campaignDO.getStatus();
	// // For validate Status
	// validateStatus(status);
	//
	// // for validate update status
	// validateOldStatus(status, presentStatus);
	//
	// campaignDO.setStatus(status);
	// iCampaignDAO.save(campaignDO);
	//
	// }

	// @Override
	// public CodeGroupDO getCodeGroupId(int code_group_id) throws
	// ApplicationException {
	//
	// CodeGroupDO codeGroupDO = iCodeGroupDAO.findById(code_group_id);
	// if (codeGroupDO == null || codeGroupDO.getStatus() ==
	// IConstants.STATUS_INACTIVE)
	// throw new ApplicationException(IResponseCodes.INVALID_ENTITY,
	// IResponseMessages.INVALID_GROUP_CODE_ID);
	//
	// return codeGroupDO;
	//
	// }
}
