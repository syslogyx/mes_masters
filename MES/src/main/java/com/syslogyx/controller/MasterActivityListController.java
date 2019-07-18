package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syslogyx.bo.BaseResponseBO;
import com.syslogyx.bo.RequestBO;
import com.syslogyx.constants.INetworkConstants;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.service.master.IMasterActivityService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.MASTER_ACTIVITY_LOG)
public class MasterActivityListController extends BaseController {

	@Autowired
	private IMasterActivityService iMasterActivityService;

	/**
	 * 
	 * For fetching Masters list with Pagination and Quick Finder
	 *
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = "/{" + INetworkConstants.IPathVariableConstants.MASTER_ID + "}")
	public ResponseEntity<BaseResponseBO> listMasterActivityLog(
			@PathVariable(name = INetworkConstants.IPathVariableConstants.MASTER_ID) int master_id,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit) {

		try {
			
			Object master_activity_log = iMasterActivityService.getMasterById(master_id, page, limit);
			
			if (master_activity_log != null)
				return getResponseModel(master_activity_log, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);

			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}

	}

}
