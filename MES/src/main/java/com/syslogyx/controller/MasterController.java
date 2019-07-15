package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syslogyx.bo.BaseResponseBO;
import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.INetworkConstants;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.CampaignDO;
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.masters.DPRTargetDO;
import com.syslogyx.model.masters.ElongationDO;
import com.syslogyx.model.masters.LeadTimeDO;
import com.syslogyx.model.masters.MastersDO;
import com.syslogyx.model.masters.ProcessFamilyDO;
import com.syslogyx.model.masters.ProcessTypeDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.masters.ProductDefDO;
import com.syslogyx.model.masters.ShelfLifeDO;
import com.syslogyx.model.masters.ShrinkageDO;
import com.syslogyx.model.masters.ThicknessDO;
import com.syslogyx.model.masters.TrimmingDO;
import com.syslogyx.service.master.IMasterService;

/**
 * This class is used for mapping urls of masters module and handling Exceptions
 * 
 * @author palash
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.MASTERS)
public class MasterController extends BaseController {

	@Autowired
	private IMasterService iMasterService;

	/**
	 * This Method is used to Created Masters and save Masters table in db
	 * 
	 * @param mastersDO
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createMasters(@RequestBody MastersDO mastersDO) {
		try {

			iMasterService.createMasters(mastersDO);

			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR + e);
		}
	}
	
	/**
	 * For fetching Masters list with Pagination and Quick Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> listMastersList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {
			
			Object mastersList = iMasterService.MastersList(requestFilter, page, limit);

			if (mastersList != null)
				return getResponseModel(mastersList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save CodeGroup Data to db
	 * 
	 * @param codeGroupDO
	 *            : contains codeGroup Data provided by users
	 * @return : Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.CODE_GROUP + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createGroupCode(@RequestBody CodeGroupDO codeGroupDO) {
		try {

			iMasterService.createGroupCode(codeGroupDO);

			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR + e);
		}
	}

	/**
	 * For fetching CodeGroup list with Pagination and Quick Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.CODE_GROUP + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> listCodeGroup(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object codeGroupList = iMasterService.listCodeGroup(requestFilter, page, limit);

			if (codeGroupList != null)
				return getResponseModel(codeGroupList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save DPRTargetDO Data to db
	 * 
	 * @param dprTargetDO
	 *            : contains DPRTargetDO Data provided by users
	 * @return : Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.DPR_TARGET + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createDPRTarget(@RequestBody DPRTargetDO dprTargetDO) {
		try {

			iMasterService.createDPRTarget(dprTargetDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used for Fetching DPR Target list with Pagination and Quick
	 * filter
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.DPR_TARGET + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getDPRTargetList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object dprTargetList = iMasterService.getDPRTargetList(requestFilter, page, limit);

			if (dprTargetList != null)
				return getResponseModel(dprTargetList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save Campaign Data to db
	 * 
	 * @param CampaignDO
	 *            : contains Campaign_Id Data provided by users
	 * @return : Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.CAMPAIGN + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createCampaign(@RequestBody CampaignDO campaignDO) {
		try {

			iMasterService.createCampaign(campaignDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}

	}

	/**
	 * For fetching Campaign list with Pagination and Quick Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.CAMPAIGN + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getCampaignList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object campaignList = iMasterService.getCampaignList(requestFilter, page, limit);

			if (campaignList != null)
				return getResponseModel(campaignList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save LeadTime Data to db
	 * 
	 * @param leadTimeDO
	 *            : contains leadTime Data provided by users
	 * 
	 * @return: Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.LEAD_TIME + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createLeadTime(@RequestBody LeadTimeDO leadTimeDO) {
		try {

			iMasterService.createLeadTime(leadTimeDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * for fetching leadTime list with pagination and quick finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.LEAD_TIME + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getLeadTimeList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object leadTimeList = iMasterService.getLeadTimeList(requestFilter, page, limit);

			if (leadTimeList != null)
				return getResponseModel(leadTimeList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Get Master's Entity according to the specified Master name and id
	 * 
	 * @param master_id
	 * @param status
	 * @return
	 */
	@GetMapping(value = "/{" + INetworkConstants.IPathVariableConstants.MASTER_NAME + "}" + "/{"
			+ INetworkConstants.IPathVariableConstants.MASTER_ID + "}")
	public ResponseEntity<BaseResponseBO> getMasterById(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_NAME) String master_name,
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_ID) int master_id) {
		try {

			Object master = iMasterService.getMasterById(master_name, master_id);

			return getResponseModel(master, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Update the Status of Master's Entity according to the specified Master name
	 * 
	 * @param master_id
	 * @param status
	 * @return
	 */
	@GetMapping(value = "/{" + INetworkConstants.IPathVariableConstants.MASTER_NAME + "}"
			+ INetworkConstants.IURLConstants.STATUS + "/{" + INetworkConstants.IPathVariableConstants.MASTER_ID + "}")
	public ResponseEntity<BaseResponseBO> updateMastersStatus(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_NAME) String master_name,
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_ID) int master_id,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.STATUS) int status) {
		try {

			iMasterService.updateMastersStatus(master_name, master_id, status);

			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.STATUS_UPDATE);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Controller Method to Map the URL which export the Master's list data to Excel
	 * sheet and return the Path of Excel
	 * 
	 * @param master_name
	 * @return
	 */
	@GetMapping(value = INetworkConstants.IURLConstants.EXCEL + "/{"
			+ INetworkConstants.IPathVariableConstants.MASTER_NAME + "}")
	public ResponseEntity<BaseResponseBO> exportListToExcel(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_NAME) String master_name) {
		try {

			String filePath = iMasterService.exportListToExcel(master_name);

			return getResponseModel(filePath, IResponseCodes.SUCCESS, IResponseMessages.LIST_EXPORTED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Controller Method to Map the URL which export the Master's list data to PDF
	 * sheet and return the Path of PDF
	 * 
	 * @param master_name
	 * @return
	 */
	@GetMapping(value = INetworkConstants.IURLConstants.PDF + "/{"
			+ INetworkConstants.IPathVariableConstants.MASTER_NAME + "}")
	public ResponseEntity<BaseResponseBO> exportListToPDF(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_NAME) String master_name) {
		try {

			String filePath = iMasterService.exportListToPDF(master_name);

			return getResponseModel(filePath, IResponseCodes.SUCCESS, IResponseMessages.LIST_EXPORTED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save elongationDO Data to db
	 * 
	 * @param elongationDO
	 *            : contains Elongation Data provided by users
	 * @return : Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.ELONGATION + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createElongation(@RequestBody ElongationDO elongationDO) {
		try {

			iMasterService.createElongation(elongationDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	@PostMapping(value = INetworkConstants.IURLConstants.ELONGATION + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getElongationList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object elongationtList = iMasterService.getElongationList(requestFilter, page, limit);

			if (elongationtList != null)
				return getResponseModel(elongationtList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * For store Process Family data to db
	 * 
	 * @param processFamilyDO
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PROCESS_FAMILY + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createProcessFamily(@RequestBody ProcessFamilyDO processFamilyDO) {
		try {

			iMasterService.createProcessFamily(processFamilyDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * For fetching Process Family list with Pagination and Quick filter
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PROCESS_FAMILY + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getProcessFamilyList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object processFamilytList = iMasterService.getProcessFamilyList(requestFilter, page, limit);

			if (processFamilytList != null)
				return getResponseModel(processFamilytList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save ProcessUnitDO Data to db
	 * 
	 * @param processUnitDO
	 *            : contains ProcessUnit Data provided by users
	 * @return : Return response
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PROCESS_UNIT + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createProcessUnit(@RequestBody ProcessUnitDO processUnitDO) {
		try {

			iMasterService.createProcessUnit(processUnitDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * For fetching Process Unit list with Pagination and Quick filter
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PROCESS_UNIT + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getProcessUnitList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object processUnitList = iMasterService.getProcessUnitList(requestFilter, page, limit);

			if (processUnitList != null)
				return getResponseModel(processUnitList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used to save ProductDef to the db
	 * 
	 * @param productDefDO:
	 *            Contains Product Data provided by users
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PRODUCT + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createProduct(@RequestBody ProductDefDO productDefDO) {
		try {

			iMasterService.createProduct(productDefDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Fetch the Product Definition List according to the Pagination and Quick
	 * Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.PRODUCT + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getProductList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object productList = iMasterService.getProductList(requestFilter, page, limit);

			if (productList != null)
				return getResponseModel(productList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method to Store the Shelf Life Data in db
	 * 
	 * @param shelfLifeDO
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SHELF_LIFE + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createShelfLife(@RequestBody ShelfLifeDO shelfLifeDO) {
		try {

			iMasterService.createShelfLife(shelfLifeDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Fetch the Shelf Life List according to the Pagination and Quick Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SHELF_LIFE + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getShelfLifeList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object shelfLifeList = iMasterService.getShelfLifeList(requestFilter, page, limit);

			if (shelfLifeList != null)
				return getResponseModel(shelfLifeList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is store Shrink Age data in db
	 * 
	 * @param shrinkageDO:
	 *            contains Shrink Age provided by user
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SHRINKAGE + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createShrinkAge(@RequestBody ShrinkageDO shrinkageDO) {
		try {

			iMasterService.createShrinkAge(shrinkageDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Fetch the Shrinkage list according to the Pagination and Quick finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SHRINKAGE + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getShrinkAgeList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object shrinkAgeList = iMasterService.getShrinkAgeList(requestFilter, page, limit);

			if (shrinkAgeList != null)
				return getResponseModel(shrinkAgeList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method to store the Trimming Master data to the db
	 * 
	 * @param shrinkageDO:
	 *            contains Trimming provided by users
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.TRIMMING + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createTrimming(@RequestBody TrimmingDO trimmingDO) {
		try {

			iMasterService.createTrimming(trimmingDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * Fetch the Trimming list according to the Pagination and quick finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.TRIMMING + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getTrimmingList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object trimmingList = iMasterService.getTrimmingList(requestFilter, page, limit);

			if (trimmingList != null)
				return getResponseModel(trimmingList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method used to store Thickness data to the db
	 * 
	 * @param thicknessDO
	 *            : contains Thickness data provided by users
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.THICKNESS + INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createThickness(@RequestBody ThicknessDO thicknessDO) {
		try {
			iMasterService.createThickness(thicknessDO);
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.DATA_STORED_SUCCESSFULLY);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}

	}

	/**
	 * Fetch the Trimming list according to the Pagination and quick finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.THICKNESS + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getThicknessList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object thicknessList = iMasterService.getThicknessList(requestFilter, page, limit);

			if (thicknessList != null)
				return getResponseModel(thicknessList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}

	}

}
