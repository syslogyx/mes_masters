package com.syslogyx.model.user;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.syslogyx.constants.INetworkConstants.ITables;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.syslogyx.constants.IPropertyConstant;

/**
 * Entity class to map the User related data in Database
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_Sequence")
	@SequenceGenerator(name = "user_Sequence", sequenceName = "USER_SEQ", allocationSize = 1)
	@Column(name = "u_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = ITables.USER_ROLES, joinColumns = {
			@JoinColumn(name = IPropertyConstant.USER_ID) }, inverseJoinColumns = {
					@JoinColumn(name = IPropertyConstant.ROLE_ID) })
	private Set<RoleDO> role;
	
	@Column(name = "status")
	public int status;
	
	@Transient
	private ArrayList<Integer> role_ids;
	
	
	
	public UserDO() {

	}

	public UserDO(int id, String username, String password, String email, String mobile, int status) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<RoleDO> getRole() {
		return role;
	}

	public void setRole(Set<RoleDO> role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Integer> getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(ArrayList<Integer> role_ids) {
		this.role_ids = role_ids;
	}

}
