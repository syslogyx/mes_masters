package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.syslogyx.service.master.IMasterService;

/**
 * This class is used for mapping urls of masters module and handling Exceptions
 * 
 * @author palash
 *
 */
@RestController
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.MASTERS)
public class MasterController extends BaseController {

	@Autowired
	private IMasterService iMasterService;

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
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
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

	/**
	 * This method is used for Fetching Elongation list with Pagination and Quick
	 * filter
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.ELONGATION + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> getElongationList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
		try {

			Object dprTargetList = iMasterService.getElongationList(requestFilter, page, limit);

			if (dprTargetList != null)
				return getResponseModel(dprTargetList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * This method is used for retrieving codeGroup by id and response all data in
	 * 
	 * @param code_group_id
	 * @return
	 */
	// @GetMapping(value = INetworkConstants.IURLConstants.CODE_GROUP +
	// INetworkConstants.IURLConstants.VIEW + "/{"
	// + INetworkConstants.IPathVariableConstants.CODE_GROUP_ID + "}")
	// public ResponseEntity<BaseResponseBO> getCodeGroupId(
	// @PathVariable(name = INetworkConstants.IPathVariableConstants.CODE_GROUP_ID)
	// int code_group_id) {
	//
	// try {
	// CodeGroupDO codeGroupDO = iMasterService.getCodeGroupId(code_group_id);
	//
	// return getResponseModel(codeGroupDO, IResponseCodes.SUCCESS,
	// IResponseMessages.SUCCESS);
	// } catch (ApplicationException e) {
	// e.printStackTrace();
	// return getResponseModel(null, e.getCode(), e.getMessage());
	// } catch (Exception e) {
	// e.printStackTrace();
	// return getResponseModel(null, IResponseCodes.SERVER_ERROR,
	// IResponseMessages.SERVER_ERROR);
	// }
	//
	// }

	/**
	 * For Update Campaign status
	 * 
	 * @param camp_id
	 * @param status
	 * @return
	 */
	// @GetMapping(value = INetworkConstants.IURLConstants.CAMPAIGN +
	// INetworkConstants.IURLConstants.STATUS + "/{"
	// + INetworkConstants.IPathVariableConstants.CAMP_ID + "}")
	// public ResponseEntity<BaseResponseBO> updateCampaignStatus(
	// @PathVariable(name = INetworkConstants.IPathVariableConstants.CAMP_ID) int
	// camp_id,
	// @RequestParam(name = INetworkConstants.IRequestParamConstants.STATUS) int
	// status) {
	//
	// try {
	// iMasterService.updateCampaignStatus(camp_id, status);
	// return getResponseModel(null, IResponseCodes.SUCCESS,
	// IResponseMessages.STATUS_UPDATE);
	// } catch (ApplicationException e) {
	// e.printStackTrace();
	// return getResponseModel(null, e.getCode(), e.getMessage());
	// } catch (Exception e) {
	// e.printStackTrace();
	// return getResponseModel(null, IResponseCodes.SERVER_ERROR,
	// IResponseMessages.SERVER_ERROR);
	// }
	// }

	/**
	 * For view Campaign table Data on particular Id
	 * 
	 * @param camp_id
	 * @return
	 */
	// @GetMapping(value = INetworkConstants.IURLConstants.CAMPAIGN +
	// INetworkConstants.IURLConstants.VIEW + "/{"
	// + INetworkConstants.IPathVariableConstants.CAMP_ID + "}")
	// public ResponseEntity<BaseResponseBO> getCampaignId(
	// @PathVariable(name = INetworkConstants.IPathVariableConstants.CAMP_ID) int
	// camp_id) {
	//
	// try {
	// CampaignDO campaignDO = iMasterService.getCampaignId(camp_id);
	//
	// return getResponseModel(campaignDO, IResponseCodes.SUCCESS,
	// IResponseMessages.SUCCESS);
	// } catch (ApplicationException e) {
	// e.printStackTrace();
	// return getResponseModel(null, e.getCode(), e.getMessage());
	// } catch (Exception e) {
	// e.printStackTrace();
	// return getResponseModel(null, IResponseCodes.SERVER_ERROR,
	// IResponseMessages.SERVER_ERROR);
	// }
	// }

}
