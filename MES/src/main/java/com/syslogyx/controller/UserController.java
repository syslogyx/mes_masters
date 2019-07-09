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
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.user.UserDO;
import com.syslogyx.service.master.IMasterService;
import com.syslogyx.service.user.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.ROLE)
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMasterService iMasterService;

	/**
	 * For new user Registration and to save there credential in UserDO table
	 * 
	 * @param userDO
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.SAVE)
	public ResponseEntity<BaseResponseBO> saveUser(@RequestBody UserDO userDO) {

		try {

			userService.saveUser(userDO);

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
	 * For fetching UserDO list with Pagination and Quick Finder
	 * 
	 * @param page
	 * @param limit
	 * @param requestFilter
	 * @return
	 */
	@PostMapping(value = INetworkConstants.IURLConstants.USERS + INetworkConstants.IURLConstants.LIST)
	public ResponseEntity<BaseResponseBO> userList(
			@RequestParam(name = INetworkConstants.IRequestParamConstants.PAGE, required = false, defaultValue = "-1") int page,
			@RequestParam(name = INetworkConstants.IRequestParamConstants.LIMIT, required = false, defaultValue = "-1") int limit,
			@RequestBody RequestBO requestFilter) { 
		try {										
			
			Object mastersList = iMasterService.userList(requestFilter, page, limit);
			
			if (mastersList != null)
				return getResponseModel(mastersList, IResponseCodes.SUCCESS, IResponseMessages.SUCCESS);
			
			return getResponseModel(null, IResponseCodes.DATA_NOT_FOUND, IResponseMessages.DATA_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.SERVER_ERROR, IResponseMessages.SERVER_ERROR);
		}
	}
	
	

}
