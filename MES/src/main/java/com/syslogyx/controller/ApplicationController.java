package com.syslogyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.syslogyx.bo.AuthToken;
import com.syslogyx.bo.LoginUser;
import com.syslogyx.config.TokenProvider;
import com.syslogyx.constants.INetworkConstants;
import com.syslogyx.constants.INetworkConstants.IURLConstants;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;

/**
 * Application Controller Map all those API urls where we don't need API token
 * Security
 * 
 * @author namrata
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = INetworkConstants.IURLConstants.API + INetworkConstants.IURLConstants.APP)
public class ApplicationController extends BaseController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	// @Autowired
	// private IUserService userService;

	/**
	 * Mapped the URL to Login the User
	 * 
	 * @param loginUser
	 *            : Object containing Username and Password
	 * @return
	 */
	@RequestMapping(value = IURLConstants.LOGIN, method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {

		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(authentication);

			// userService.findOne(loginUser.getUsername());

			return getResponseModel(new AuthToken(token, loginUser.getUsername()), IResponseCodes.SUCCESS,
					IResponseMessages.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCodes.UNAUTHORIZED, IResponseMessages.UNAUTHORIZED_USER);
		}
	}
}
