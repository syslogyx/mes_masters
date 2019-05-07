package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syslogyx.bo.BaseResponseBO;
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
	@PostMapping(value = INetworkConstants.IURLConstants.GROUP_CODE + INetworkConstants.IURLConstants.SAVE)
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
	
	
}
