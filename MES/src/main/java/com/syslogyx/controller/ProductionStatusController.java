package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.syslogyx.service.production.IProductionStatusService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.PRODUCTION_STATUS)
public class ProductionStatusController extends BaseController{

	@Autowired
	private IProductionStatusService productionStatus;
	
	/**
	 * This Method is used to Created Masters and save Masters table in db
	 * 
	 * @param mastersDO
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> createProductionStatus(@RequestBody MastersDO mastersDO) {
		try {

			productionStatus.createProductionStatus(mastersDO);

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
