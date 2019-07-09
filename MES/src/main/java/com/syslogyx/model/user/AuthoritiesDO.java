package com.syslogyx.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Entity class to connect the table that holding User Authorities related data
 * 
 * @author palash
 *
 */
@Entity
@Table(name = "authorities")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthoritiesDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "au_id")
	private int id;

	@Column(name = "name")
	private String name;

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
