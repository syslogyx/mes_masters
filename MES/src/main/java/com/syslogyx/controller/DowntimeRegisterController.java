package com.syslogyx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syslogyx.bo.BaseResponseBO;
import com.syslogyx.constants.INetworkConstants;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.masters.MastersDO;

/**
 * This class is used for mapping urls of DowntimeRegister module and handling
 * Exceptions
 * 
 * @author palash
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.MASTERS)
public class DowntimeRegisterController extends BaseController {

	
	
	/**
	 * This Method is used to store data of Downtime Register table in db
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
}
