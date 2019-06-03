package com.syslogyx.service.master;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IFileHeaderConstants;
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CRGradeDO;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.ElongationDO;
import com.syslogyx.model.masters.LeadTimeDO;
import com.syslogyx.model.masters.ProcessFamilyDO;
import com.syslogyx.model.masters.ProcessTypeDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.ProductDefDO;
import com.syslogyx.model.masters.ProductFormDO;
import com.syslogyx.model.masters.ProductTypeDO;
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

			// Set the Column width to adjust it's size according to the text size
			for (int index = 0; index < headerList.size(); index++) {
				sheet.autoSizeColumn(index);
			}

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

		else if (master_name.equals(IConstants.MASTERS_NAME.LEAD_TIME))
			processExportingLeadTimeListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.ELONGATION))
			processExportingElongationListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			processExportingProcessFamilyListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_UNIT))
			processExportingProcessUnitListExcel(sheet, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PRODUCT))
			processExportingProductListExcel(sheet, mastersList);
	}

	/**
	 * Set the field value from ProductDefDO object to it's respected index number
	 * in excel sheet
	 * 
	 * @param sheet
	 * @param productDefinitionList
	 */
	private void processExportingProductListExcel(HSSFSheet sheet, List<ProductDefDO> productDefinitionList) {

		for (int index = 0; index < productDefinitionList.size(); index++) {
			ProductDefDO productDefDO = productDefinitionList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(productDefDO.getProduct());
			row.createCell(2).setCellValue(productDefDO.getProduct_type_name());
			row.createCell(3).setCellValue(productDefDO.getProduct_form_name());
			row.createCell(4).setCellValue(productDefDO.getUpdated_by_name());
			row.createCell(5).setCellValue(Utils.getFormatedDate(productDefDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(6).setCellValue(getStatusString(productDefDO.getStatus()));
		}
	}

	/**
	 * Set the field value from ProcessUnitDO Object to it's respective index number
	 * in the excel sheet
	 * 
	 * @param sheet
	 * @param processUnitList
	 */
	private void processExportingProcessUnitListExcel(HSSFSheet sheet, List<ProcessUnitDO> processUnitList) {
		for (int index = 0; index < processUnitList.size(); index++) {
			ProcessUnitDO processUnitDO = processUnitList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(processUnitDO.getUnit());
			row.createCell(2).setCellValue(processUnitDO.getProcess_family_name());
			row.createCell(3).setCellValue(processUnitDO.getCost_center());
			row.createCell(4).setCellValue(processUnitDO.getCapacity());
			row.createCell(5).setCellValue(processUnitDO.getConst_setup_time());
			row.createCell(6).setCellValue(processUnitDO.getYield());
			row.createCell(7).setCellValue(getOSPString(processUnitDO.getOsp_identifier()));
			row.createCell(8).setCellValue(processUnitDO.getUpdated_by_name());
			row.createCell(9).setCellValue(Utils.getFormatedDate(processUnitDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(10).setCellValue(getStatusString(processUnitDO.getStatus()));
		}
	}

	/**
	 * Set the field value from ProcessFamilyDO Object to it's respective index
	 * number in the excel sheet
	 * 
	 * @param sheet
	 * @param processFamilyList
	 */
	private void processExportingProcessFamilyListExcel(HSSFSheet sheet, List<ProcessFamilyDO> processFamilyList) {

		for (int index = 0; index < processFamilyList.size(); index++) {
			ProcessFamilyDO processFamilyDO = processFamilyList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(processFamilyDO.getProcess_family());
			row.createCell(2).setCellValue(processFamilyDO.getProcess_type_name());
			row.createCell(3).setCellValue(getPriorityString(processFamilyDO.getPriority()));
			row.createCell(4).setCellValue(processFamilyDO.getBucket());
			row.createCell(5).setCellValue(Utils.getFormatedDate(processFamilyDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(6).setCellValue(processFamilyDO.getUpdated_by_name());
			row.createCell(7).setCellValue(getStatusString(processFamilyDO.getStatus()));
		}

	}

	/**
	 * Set the Field value from LeadTimeDO Object to it's respective index number in
	 * the excel sheet
	 * 
	 * @param sheet
	 * @param leadTimeList
	 */
	private void processExportingLeadTimeListExcel(HSSFSheet sheet, List<LeadTimeDO> leadTimeList) {

		for (int index = 0; index < leadTimeList.size(); index++) {
			LeadTimeDO leadTimeDO = leadTimeList.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(leadTimeDO.getBefore_process_unit_name());
			row.createCell(2).setCellValue(leadTimeDO.getAfter_process_unit_name());
			row.createCell(3).setCellValue(leadTimeDO.getIdle_time_min());
			row.createCell(4).setCellValue(leadTimeDO.getIdle_time_max());
			row.createCell(5).setCellValue(leadTimeDO.getHandle_time_min());
			row.createCell(6).setCellValue(leadTimeDO.getHandle_time_max());
			row.createCell(7).setCellValue(leadTimeDO.getUpdated_by_name());
			row.createCell(8).setCellValue(
					Utils.getFormatedDate(leadTimeDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(9).setCellValue(getStatusString(leadTimeDO.getStatus()));
		}

	}

	/**
	 * Set the Field value from DPRTargetDO Object to it's respective index number
	 * in the excel sheet
	 * 
	 * @param sheet
	 * @param elongations
	 */
	private void processExportingElongationListExcel(HSSFSheet sheet, List<ElongationDO> elongations) {
		for (int index = 0; index < elongations.size(); index++) {
			ElongationDO elongationDO = elongations.get(index);
			HSSFRow row = sheet.createRow(index + 1);
			row.createCell(0).setCellValue(index + 1);
			row.createCell(1).setCellValue(elongationDO.getUnit_name());
			row.createCell(2).setCellValue(elongationDO.getCr_grade_name());
			row.createCell(3).setCellValue(elongationDO.getUpdated_by_name());
			row.createCell(4).setCellValue(Utils.getFormatedDate(elongationDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(5).setCellValue(getStatusString(elongationDO.getStatus()));
		}
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
					Utils.getFormatedDate(dprTargetDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
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
					Utils.getFormatedDate(campaignDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
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
					Utils.getFormatedDate(codeGroupDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A));
			row.createCell(4).setCellValue(codeGroupDO.getUpdated_by_name());
			row.createCell(5).setCellValue(getStatusString(codeGroupDO.getStatus()));
		}
	}

	@Override
	public String exportListToPDF(String master_name) throws ApplicationException {
		List mastersList = masterDAO.findMastersList(master_name);

		if (mastersList != null && !mastersList.isEmpty()) {
			Document document = new Document(PageSize.A4);

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
	 * @throws ApplicationException
	 */
	private float[] setPDFwidth(String master_name) throws ApplicationException {
		if (master_name.equals(IConstants.MASTERS_NAME.CODE_GROUP))
			return new float[] { 1, 1, 2, 2, 2, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.CAMPAIGN))
			return new float[] { 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.DPR_TARGET))
			return new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.LEAD_TIME))
			return new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.ELONGATION))
			return new float[] { 1, 1, 1, 1, 2, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			return new float[] { 1, 2, 1, 1, 1, 1, 1, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_UNIT))
			return new float[] { 1, 1, 2, 1, 1, 1.5f, 1, 1, 1, 2, 1 };

		else if (master_name.equals(IConstants.MASTERS_NAME.PRODUCT))
			return new float[] { 1, 1, 1, 1, 1, 1, 1 };

		else
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.INVALID_MASTER_NAME);
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

		else if (master_name.equals(IConstants.MASTERS_NAME.LEAD_TIME))
			processExportingLeadTimeListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.ELONGATION))
			processExportingElongationListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			processExportingProcessFamilyListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PROCESS_UNIT))
			processExportingProcessUnitListPDF(table, mastersList);

		else if (master_name.equals(IConstants.MASTERS_NAME.PRODUCT))
			processExportingProductListPDF(table, mastersList);

	}

	/**
	 * Set the field value from ProductDefDO object to it's respective index number
	 * in the PDF Table
	 * 
	 * @param table
	 * @param productDefinitionList
	 */
	private void processExportingProductListPDF(PdfPTable table, List<ProductDefDO> productDefinitionList) {

		for (int index = 0; index < productDefinitionList.size(); index++) {
			ProductDefDO productDefDO = productDefinitionList.get(index);

			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(productDefDO.getProduct(), font));
			table.addCell(new Phrase(productDefDO.getProduct_type_name(), font));
			table.addCell(new Phrase(productDefDO.getProduct_form_name(), font));
			table.addCell(new Phrase(productDefDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(productDefDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(getStatusString(productDefDO.getStatus()), font));

		}
	}

	/**
	 * Set the field value from ProcessUnitDO object to it's respective index number
	 * in the PDF Table
	 * 
	 * @param table
	 * @param processUnitList
	 */
	private void processExportingProcessUnitListPDF(PdfPTable table, List<ProcessUnitDO> processUnitList) {
		for (int index = 0; index < processUnitList.size(); index++) {
			ProcessUnitDO processUnitDO = processUnitList.get(index);
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(processUnitDO.getUnit(), font));
			table.addCell(new Phrase(processUnitDO.getProcess_family_name(), font));
			table.addCell(new Phrase(processUnitDO.getCost_center(), font));
			table.addCell(new Phrase(processUnitDO.getCapacity(), font));
			table.addCell(new Phrase(processUnitDO.getConst_setup_time(), font));
			table.addCell(new Phrase(processUnitDO.getYield(), font));
			table.addCell(new Phrase(getOSPString(processUnitDO.getOsp_identifier()), font));
			table.addCell(new Phrase(processUnitDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(Utils.getFormatedDate(processUnitDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A), font));
			table.addCell(new Phrase(getStatusString(processUnitDO.getStatus()), font));
		}
	}

	/**
	 * Set the field value from ProcessFamily object to it's respective index number
	 * in the PDF Table
	 * 
	 * @param table
	 * @param processFamilyList
	 */
	private void processExportingProcessFamilyListPDF(PdfPTable table, List<ProcessFamilyDO> processFamilyList) {

		for (int index = 0; index < processFamilyList.size(); index++) {
			ProcessFamilyDO processFamilyDO = processFamilyList.get(index);
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(processFamilyDO.getProcess_family(), font));
			table.addCell(new Phrase(processFamilyDO.getProcess_type_name(), font));
			table.addCell(new Phrase(getPriorityString(processFamilyDO.getPriority()), font));
			table.addCell(new Phrase(processFamilyDO.getBucket(), font));
			table.addCell(new Phrase(processFamilyDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(Utils.getFormatedDate(processFamilyDO.getUpdated(),
					IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A), font));
			table.addCell(new Phrase(getStatusString(processFamilyDO.getStatus()), font));

		}
	}

	/**
	 * Set the Field value from lead time Object to it's respective index number in
	 * the PDF Table
	 * 
	 * @param table
	 * @param leadTimeList
	 */
	private void processExportingLeadTimeListPDF(PdfPTable table, List<LeadTimeDO> leadTimeList) {

		for (int index = 0; index < leadTimeList.size(); index++) {
			LeadTimeDO leadTimeDO = leadTimeList.get(index);
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(leadTimeDO.getAfter_process_unit_name(), font));
			table.addCell(new Phrase(leadTimeDO.getBefore_process_unit_name(), font));
			table.addCell(new Phrase(leadTimeDO.getIdle_time_min(), font));
			table.addCell(new Phrase(leadTimeDO.getIdle_time_max(), font));
			table.addCell(new Phrase(leadTimeDO.getHandle_time_min(), font));
			table.addCell(new Phrase(leadTimeDO.getHandle_time_max(), font));
			table.addCell(new Phrase(leadTimeDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(leadTimeDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(getStatusString(leadTimeDO.getStatus()), font));
		}

	}

	/**
	 * Set the Field value from Elongation Object to it's respective index number in
	 * the PDF Table
	 * 
	 * @param table
	 * @param elongationDOs
	 */
	private void processExportingElongationListPDF(PdfPTable table, List<ElongationDO> elongationDOs) {
		for (int index = 0; index < elongationDOs.size(); index++) {
			ElongationDO elongationDO = elongationDOs.get(index);
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(elongationDO.getUnit_name(), font));
			table.addCell(new Phrase(elongationDO.getCr_grade_name(), font));
			table.addCell(new Phrase(elongationDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(elongationDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(getStatusString(elongationDO.getStatus()), font));
		}
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
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(dprTargetDO.getYear(), font));
			table.addCell(new Phrase(dprTargetDO.getUnit_name(), font));
			table.addCell(new Phrase(dprTargetDO.getProduct_name(), font));
			table.addCell(new Phrase(dprTargetDO.getBusiness_plan_target() + "", font));
			table.addCell(new Phrase(dprTargetDO.getInternal_target() + "", font));
			table.addCell(new Phrase(dprTargetDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(dprTargetDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(getStatusString(dprTargetDO.getStatus()), font));
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
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(campaignDO.getCampaign_id(), font));
			table.addCell(new Phrase(campaignDO.getAttribute(), font));
			table.addCell(new Phrase(campaignDO.getAim(), font));
			table.addCell(new Phrase(campaignDO.getCapacity_max() + "", font));
			table.addCell(new Phrase(campaignDO.getCapacity_min() + "", font));
			table.addCell(new Phrase(campaignDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(campaignDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(getPriorityString(campaignDO.getPriority_level()), font));
			table.addCell(new Phrase(campaignDO.getHold_unit_name(), font));
			table.addCell(new Phrase(getStatusString(campaignDO.getStatus()), font));
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
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);

			table.addCell(new Phrase(index + 1 + "", font));
			table.addCell(new Phrase(codeGroupDO.getGroup_code(), font));
			table.addCell(new Phrase(codeGroupDO.getGroup_desc(), font));
			table.addCell(new Phrase(
					Utils.getFormatedDate(codeGroupDO.getUpdated(), IConstants.DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A),
					font));
			table.addCell(new Phrase(codeGroupDO.getUpdated_by_name(), font));
			table.addCell(new Phrase(getStatusString(codeGroupDO.getStatus()), font));
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
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.LEAD_TIME))
			return LeadTimeDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.ELONGATION))
			return ElongationDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.PROCESS_FAMILY))
			return ProcessFamilyDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.PROCESS_UNIT))
			return ProcessFamilyDO.class;
		else if (master_name.equalsIgnoreCase(IConstants.MASTERS_NAME.PRODUCT))
			return ProductDefDO.class;
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

		int after_process_unit_id = leadTimeDO.getAfter_process_unit_id();
		int before_process_unit_id = leadTimeDO.getBefore_process_unit_id();
		String idle_time_min = leadTimeDO.getIdle_time_min();
		String idle_time_max = leadTimeDO.getIdle_time_max();
		String handle_time_min = leadTimeDO.getHandle_time_min();
		String handle_time_max = leadTimeDO.getHandle_time_max();

		// validate lead_time_id
		masterDAO.validateEntityById(LeadTimeDO.class, leadTimeId, IResponseMessages.INVALID_LEAD_TIME_ID);

		ProcessUnitDO after_process_unit = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class,
				after_process_unit_id, IResponseMessages.INVALID_PROCESS_UNIT_ID);

		ProcessUnitDO before_process_unit = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class,
				before_process_unit_id, IResponseMessages.INVALID_PROCESS_UNIT_ID);

		leadTimeDO.setAfter_process_unit(after_process_unit);
		leadTimeDO.setBefore_process_unit(before_process_unit);

		Utils.validateDateFormat(idle_time_min, IConstants.DATE_TIME_FORMAT.HH_MM_SS);
		Utils.validateDateFormat(idle_time_max, IConstants.DATE_TIME_FORMAT.HH_MM_SS);
		Utils.validateDateFormat(handle_time_min, IConstants.DATE_TIME_FORMAT.HH_MM_SS);
		Utils.validateDateFormat(handle_time_max, IConstants.DATE_TIME_FORMAT.HH_MM_SS);

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
				long listSize = masterDAO.getLeadTimeListSize(requestFilter);

				return generatePaginationResponse(leadTime, listSize, page, limit);
			}
			return leadTime;
		}

		return null;
	}

	@Override
	public void createElongation(ElongationDO elongationDO) throws ApplicationException, Exception {
		int elong_id = elongationDO.getId();
		int cr_grade_id = elongationDO.getCr_grade_id();
		int unit_id = elongationDO.getUnit_id();

		// validate the elongation id in case of update scenario
		masterDAO.validateEntityById(ElongationDO.class, elong_id, IResponseMessages.INVALID_ELONGATION_ID);

		// validate and set the CR Grade id
		CRGradeDO crGradeDO = (CRGradeDO) masterDAO.validateEntityById(CRGradeDO.class, cr_grade_id,
				IResponseMessages.INVALID_CR_GRADE_ID);
		elongationDO.setCr_grade(crGradeDO);

		// validate and set the CR Grade id
		ProcessUnitDO processUnitDO = (ProcessUnitDO) masterDAO.validateEntityById(ProcessUnitDO.class, unit_id,
				IResponseMessages.INVALID_PROCESS_UNIT_ID);
		elongationDO.setUnit(processUnitDO);

		// set the required details
		UserDO loggedInUser = getLoggedInUser();

		elongationDO.setStatus(IConstants.STATUS_ACTIVE);
		elongationDO.setCreated_by(loggedInUser);
		elongationDO.setUpdated_by(loggedInUser);

		masterDAO.mergeEntity(elongationDO);
	}

	@Override
	public Object getElongationList(RequestBO requestFilter, int page, int limit) {
		List<ElongationDO> elongations = masterDAO.getElongationList(requestFilter, page, limit);

		if (elongations != null && !elongations.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getElongationListSize(requestFilter);

				return generatePaginationResponse(elongations, listSize, page, limit);
			}
			return elongations;
		}

		return null;
	}

	@Override
	public void createProcessFamily(ProcessFamilyDO processFamilyDO) throws ApplicationException, Exception {

		int processFamily_id = processFamilyDO.getId();
		int process_type_id = processFamilyDO.getProcess_type_id();
		int priority_level = processFamilyDO.getPriority();

		// validate ProcessFamily id in case of update scenario
		masterDAO.validateEntityById(ProcessFamilyDO.class, processFamily_id,
				IResponseMessages.INVALID_PROCESS_FAMILY_ID);

		// validate and Set ProcessType id
		ProcessTypeDO processTypeDO = (ProcessTypeDO) masterDAO.validateEntityById(ProcessTypeDO.class, process_type_id,
				IResponseMessages.INVALID_PROCESS_TYPE_ID);
		processFamilyDO.setProcess_type(processTypeDO);

		validatePriority(priority_level);

		UserDO loggedInUser = getLoggedInUser();
		processFamilyDO.setStatus(IConstants.STATUS_ACTIVE);
		processFamilyDO.setCreated_by(loggedInUser);
		processFamilyDO.setUpdated_by(loggedInUser);

		masterDAO.mergeEntity(processFamilyDO);
	}

	@Override
	public Object getProcessFamilyList(RequestBO requestFilter, int page, int limit) {
		List<ProcessFamilyDO> processFamily = masterDAO.getProcessFamilyList(requestFilter, page, limit);

		if (processFamily != null && !processFamily.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getProcessFamilyListSize(requestFilter);

				return generatePaginationResponse(processFamily, listSize, page, limit);
			}
			return processFamily;
		}

		return null;
	}

	@Override
	public void createProcessUnit(ProcessUnitDO processUnitDO) throws ApplicationException, Exception {

		int process_unit_id = processUnitDO.getId();
		int process_family_id = processUnitDO.getProcess_family_id();
		int osp_identifier = processUnitDO.getOsp_identifier();
		String const_setup_time = processUnitDO.getConst_setup_time();
		String unit = processUnitDO.getUnit();

		// validate the process unit id in update scenario
		masterDAO.validateEntityById(ProcessUnitDO.class, process_unit_id, IResponseMessages.INVALID_PROCESS_UNIT_ID);

		// validate the group code if already exists in database or not
		ProcessUnitDO existingUnit = (ProcessUnitDO) masterDAO.getEntityByPropertyName(ProcessUnitDO.class,
				IPropertyConstant.UNIT, unit);

		if (existingUnit != null && existingUnit.getId() != process_unit_id)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_PROCESS_UNIT);

		// validate and Set Process family
		ProcessFamilyDO processFamilyDO = (ProcessFamilyDO) masterDAO.validateEntityById(ProcessFamilyDO.class,
				process_family_id, IResponseMessages.INVALID_PROCESS_FAMILY_ID);
		processUnitDO.setProcess_family(processFamilyDO);

		// validate OSP
		validateOSPIdentifier(osp_identifier);

		// validate the time format
		Utils.validateDateFormat(const_setup_time, IConstants.DATE_TIME_FORMAT.HH_MM_SS);

		UserDO loggedInUser = getLoggedInUser();
		processUnitDO.setStatus(IConstants.STATUS_ACTIVE);
		processUnitDO.setCreated_by(loggedInUser);
		processUnitDO.setUpdated_by(loggedInUser);

		masterDAO.mergeEntity(processUnitDO);
	}

	/**
	 * Validate the value of OSP Identifier
	 * 
	 * @param osp_identifier
	 * @throws ApplicationException
	 */
	private void validateOSPIdentifier(int osp_identifier) throws ApplicationException {
		if (osp_identifier != IConstants.OSP_IDENTIFIER.YES && osp_identifier != IConstants.OSP_IDENTIFIER.NO)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_OSP_IDENTIFIER);
	}

	@Override
	public Object getProcessUnitList(RequestBO requestFilter, int page, int limit) {
		List<ProcessUnitDO> processUnit = masterDAO.getProcessUnitList(requestFilter, page, limit);

		if (processUnit != null && !processUnit.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getProcessUnitListSize(requestFilter);

				return generatePaginationResponse(processUnit, listSize, page, limit);
			}
			return processUnit;
		}

		return null;
	}

	@Override
	public void createProduct(ProductDefDO productDefDO) throws ApplicationException, Exception {

		int product_id = productDefDO.getId();
		int product_type_id = productDefDO.getProduct_type_id();
		int product_form_id = productDefDO.getProduct_form_id();
		String product = productDefDO.getProduct();

		// validate the product_id in update scenario
		masterDAO.validateEntityById(ProductDefDO.class, product_id, IResponseMessages.INVALID_PRODUCT_DEFINATION_ID);

		// validate the Product Name if already exists in database or not
		ProductDefDO existingProduct = (ProductDefDO) masterDAO.getEntityByPropertyName(ProductDefDO.class,
				IPropertyConstant.PRODUCT, product);

		if (existingProduct != null && existingProduct.getId() != product_id)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_PRODUCT);

		// validate and Set Product Type
		ProductTypeDO productTypeDO = (ProductTypeDO) masterDAO.validateEntityById(ProductTypeDO.class, product_type_id,
				IResponseMessages.INVALID_PRODUCT_TYPE_ID);

		// validate and Set Product Form
		ProductFormDO productFormDO = (ProductFormDO) masterDAO.validateEntityById(ProductFormDO.class, product_form_id,
				IResponseMessages.INVALID_PRODUCT_FORM_ID);

		productDefDO.setProduct_type(productTypeDO);
		productDefDO.setProduct_form(productFormDO);

		UserDO loggedInUser = getLoggedInUser();
		productDefDO.setStatus(IConstants.STATUS_ACTIVE);
		productDefDO.setCreated_by(loggedInUser);
		productDefDO.setUpdated_by(loggedInUser);

		masterDAO.mergeEntity(productDefDO);

	}

	@Override
	public Object getProductList(RequestBO requestFilter, int page, int limit) {

		List<ProductDefDO> productList = masterDAO.getProductList(requestFilter, page, limit);

		if (productList != null && !productList.isEmpty()) {
			if (page != IConstants.DEFAULT && limit != IConstants.DEFAULT) {
				long listSize = masterDAO.getProductSize(requestFilter);

				return generatePaginationResponse(productList, listSize, page, limit);
			}
			return productList;
		}

		return null;
	}

}
