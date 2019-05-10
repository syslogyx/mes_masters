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
import com.syslogyx.model.masters.CodeGroupDO;
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
			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}

	}

	/**
	 * This method is used for Fetching list and Pagination
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
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}

	/**
	 * this method is used for update status in CodeGroup table
	 * 
	 * @param code_group_id
	 * @param status
	 * @return
	 */
	@GetMapping(value = INetworkConstants.IURLConstants.CODE_GROUP + INetworkConstants.IURLConstants.STATUS + "/{"
			+ INetworkConstants.IPathVariableConstants.CODE_GROUP_ID + "}")
	public ResponseEntity<BaseResponseBO> updateStatus(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.CODE_GROUP_ID) int code_group_id,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.STATUS) int status) {
		try {

			iMasterService.updateStatus(code_group_id, status);

			return getResponseModel(null, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);
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
}
