package com.syslogyx.model.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.syslogyx.constants.INetworkConstants.ITables;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syslogyx.constants.IPropertyConstant;

/**
 * Entity Class to connect the Table that holding User Role related data
 * 
 * @author palash
 *
 */
@Entity
@Table(name = "roles")
public class RoleDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = ITables.ROLE_AUTHORITIES, joinColumns = {
			@JoinColumn(name = IPropertyConstant.ROLE_ID) }, inverseJoinColumns = {
					@JoinColumn(name = IPropertyConstant.AUTHORITIES_ID) })
	private Set<AuthoritiesDO> authorities;

	@Column(name = "status")
	private int status;

	@JsonIgnore
	@Column(name = "created", updatable = false)
	private Date created;

	@JsonIgnore
	@Column(name = "updated")
	private Date updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AuthoritiesDO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<AuthoritiesDO> authorities) {
		this.authorities = authorities;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
