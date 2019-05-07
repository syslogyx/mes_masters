package com.syslogyx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.syslogyx.bo.BaseResponseBO;

/**
 * Controller for Defining some basic Operations which will be accessible to all
 * Controllers All Module Controllers need to Extend this BaseController
 * 
 * @author namrata
 *
 */
public class BaseController {
	public ResponseEntity<BaseResponseBO> getResponseModel(Object responseObject, int statusCode, String msgText) {
		BaseResponseBO baseResponseModel = new BaseResponseBO();
		baseResponseModel.setResponse(responseObject);
		baseResponseModel.setMessage(msgText);
		baseResponseModel.setStatus(statusCode);

		return new ResponseEntity<BaseResponseBO>(baseResponseModel, HttpStatus.OK);
	}
}
