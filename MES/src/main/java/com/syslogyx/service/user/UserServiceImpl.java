package com.syslogyx.service.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IPropertyConstant;
import com.syslogyx.dao.master.IMasterDAO;
import com.syslogyx.dao.user.IUserDAO;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;
import com.syslogyx.model.user.AuthoritiesDO;
import com.syslogyx.model.user.RoleDO;
import com.syslogyx.model.user.UserDO;

/**
 * Service Layer Implementation Class to Handle the Business Logic Related to
 * User Module
 * 
 * @author namrata
 *
 */
@Service(value = "userService")
@Transactional(rollbackFor = { ApplicationException.class, Exception.class })
public class UserServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	private IUserDAO userDao;

	@Autowired
	private IMasterDAO masterDAO;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

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

	/**
	 * Loop through the User related Roles and Return the Set of User's Authorities
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unused")
	private Set<SimpleGrantedAuthority> getAuthority(UserDO user) {
		// Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		Set<RoleDO> roles = user.getRole();
		if (roles != null && !roles.isEmpty()) {

			// roles.forEach(role -> {
			// System.out.println(">>>>> role : " + role.getName());
			// // authorities.add(new SimpleGrantedAuthority(role.getName()));
			// authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			// });

			return getGrantedAuthorities(getPrivileges(roles));

		}
		// return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return null;
	}

	/**
	 * Loop through the Roles and get the Authorities of all roles
	 * 
	 * @param roles
	 * @return
	 */
	private List<String> getPrivileges(Set<RoleDO> roles) {

		List<String> privileges = new ArrayList<>();
		List<AuthoritiesDO> collection = new ArrayList<>();
		for (RoleDO role : roles) {
			collection.addAll(role.getAuthorities());
		}
		for (AuthoritiesDO item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	/**
	 * Process the Set of privileges and add to reference of SimpleGrantedAuthority
	 * 
	 * @param privileges
	 * @return
	 */
	private Set<SimpleGrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege));
		}
		return authorities;
	}

	@Override
	public UserDO findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<UserDO> findAll() {
		List<UserDO> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(UserDO userDO) throws ApplicationException, Exception {

		// validate UserDO data
		ValidateAndSetUserData(userDO, false);
		userDO.setPassword(bcryptEncoder.encode(userDO.getPassword()));
		userDO.setStatus(IConstants.STATUS_ACTIVE);

		userDao.save(userDO);

	}

	/**
	 * Validate and save User data to db
	 * 
	 * @param userDO
	 * @param b
	 * @throws ApplicationException
	 * @throws Exception
	 */
	private void ValidateAndSetUserData(UserDO userDO, boolean b) throws ApplicationException, Exception {

		int user_id = userDO.getId();
		String username = userDO.getUsername();

		// validate UserDO in update scenario
		masterDAO.validateEntityById(UserDO.class, user_id, IResponseMessages.INVALIDE_USER_ID);

		UserDO existingUsername = (UserDO) masterDAO.getEntityByPropertyName(UserDO.class, IPropertyConstant.USERNAME,
				username);

		if (existingUsername != null && existingUsername.getId() != user_id)
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.EXISTING_USERNAME);

		validateAndSetUserRoles(userDO);

	}

	/**
	 * Validate and Set UserRole id
	 * 
	 * @param userDO
	 */
	private void validateAndSetUserRoles(UserDO userDO) throws ApplicationException, Exception {

		if (userDO.getRole_ids() != null && !userDO.getRole_ids().isEmpty()) {
			Set<RoleDO> roles = new HashSet<>();

			for (Integer role_id : userDO.getRole_ids()) {
				RoleDO role = (RoleDO) masterDAO.getEntityById(RoleDO.class, role_id);

				if (role == null || role.getStatus() == IConstants.STATUS_INACTIVE)
					throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.INVALID_ROLE);

				roles.add(role);
			}
			userDO.setRole(roles);
		} else {
			throw new ApplicationException(IResponseCodes.INVALID_ENTITY, IResponseMessages.ROLES_ARE_NOT_AVAILABLE);
		}

	}

}
