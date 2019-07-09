
package com.syslogyx.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.syslogyx.constants.INetworkConstants;
import com.syslogyx.config.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Filter Class to filter all the API Requests by their Token, Validate the
 * Username and Password from the Token and Restrict the Unauthorized User
 * 
 * @author namrata
 *
 */
@Component	
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final String header = req.getHeader(INetworkConstants.IHeaderConstants.X_AUTH_TOKEN);
		String username = null;
		String authToken = null;
		if (header != null) {

			// authToken = header.replace(TOKEN_PREFIX, "");
			authToken = header;
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			try {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				if (jwtTokenUtil.validateToken(authToken, userDetails)) {

					UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,
							SecurityContextHolder.getContext().getAuthentication(), userDetails);

					// UsernamePasswordAuthenticationToken authentication = new
					// UsernamePasswordAuthenticationToken(
					// userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
					// new SimpleGrantedAuthority("ROLE_ADMIN")));
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					logger.info("authenticated user " + username + ", setting security context");
					// SecurityContext sc = SecurityContextHolder.getContext();

					System.out.println("Authentication :" + authentication);
					SecurityContextHolder.getContext().setAuthentication(authentication);

				}
			} catch (Exception e) {
				System.out.println("hello..");
				res.sendError(404, e.getMessage() + " " + username + " " + authToken);
			}
		}

		chain.doFilter(req, res);

	}

}
