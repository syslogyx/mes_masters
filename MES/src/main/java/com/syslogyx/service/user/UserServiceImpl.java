package com.syslogyx.service.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.dao.user.IUserDAO;
import com.syslogyx.model.user.UserDO;

/**
 * Service Layer Implementation Class to Handle the Business Logic Related to
 * User Module
 * 
 * @author namrata
 *
 */
@Service(value = "userService")
@Transactional(rollbackFor = { Exception.class })
public class UserServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	private IUserDAO userDao;

	/**
	 * @param username
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDO user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public UserDO findOne(String username) {
		return userDao.findByUsername(username);
	}

}
