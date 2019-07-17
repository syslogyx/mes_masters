package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.ACTIVITY)
public class MasterActivityListController extends BaseController {
	
	@Autowired
	private IMasterActivityService iMasterActivityService;
	
	@PostMapping(value = INetworkConstants.IURLConstants.CODE_GROUP_ACTIVITY + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> listCodeGroupActivity(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) {
				
		try {
			Object codeGroupActivityList = iMasterActivityService.listCodeGroupActivity(requestFilter, page, limit);
			
			if (codeGroupActivityList != null)
				return getResponseModel(codeGroupActivityList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);
			
			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
			
	}

}
