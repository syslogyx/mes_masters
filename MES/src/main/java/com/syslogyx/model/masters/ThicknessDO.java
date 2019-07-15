package com.syslogyx.model.masters;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Thickness Master Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "thickness")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThicknessDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "thick_Sequence")
	@SequenceGenerator(name = "thick_Sequence", sequenceName = "THICK_SEQ", allocationSize = 1)
	@Column(name = "th_id")
	private int id;

	@Column(name = "thickness_min")
	private float thickness_min;
	
	@Column(name = "thickness_max")
	private float thickness_max;

	@Column(name = "tolerance_plus")
	private float tolerance_plus;

	@Column(name = "tolerance_minus")
	private float tolerance_minus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "created_by", updatable = false)
	private UserDO created_by;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "updated_by")
	private UserDO updated_by;

	@CreatedDate
	@Column(name = "created", updatable = false)
	private Date created;

	@LastModifiedDate
	@Column(name = "updated")
	private Date updated;

	@Transient
	private int updated_by_id;
	
	@Column(name = "status")
	public int status;

	@Transient
	private String update_by_name;

	public ThicknessDO() {

	}

	public ThicknessDO(int id, float thickness_min, float thickness_max, float tolerance_minus, float tolerance_plus,
			String update_by_name, Date updated, int status) {

		this.id = id;
		this.thickness_min = thickness_min;
		this.thickness_max = thickness_max;
		this.tolerance_plus = tolerance_plus;
		this.tolerance_minus = tolerance_minus;
		this.updated = updated;
		this.status = status;
		this.update_by_name = update_by_name;
	}

	public String getUpdate_by_name() {
		return update_by_name;
	}

	public void setUpdate_by_name(String update_by_name) {
		this.update_by_name = update_by_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDO getCreated_by() {
		return created_by;
	}

	public void setCreated_by(UserDO created_by) {
		this.created_by = created_by;
	}

	public UserDO getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(UserDO updated_by) {
		this.updated_by = updated_by;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getThickness_min() {
		return thickness_min;
	}

	public void setThickness_min(float thickness_min) {
		this.thickness_min = thickness_min;
	}

	public float getThickness_max() {
		return thickness_max;
	}

	public void setThickness_max(float thickness_max) {
		this.thickness_max = thickness_max;
	}

	public float getTolerance_plus() {
		return tolerance_plus;
	}

	public void setTolerance_plus(float tolerance_plus) {
		this.tolerance_plus = tolerance_plus;
	}

	public float getTolerance_minus() {
		return tolerance_minus;
	}

	public void setTolerance_minus(float tolerance_minus) {
		this.tolerance_minus = tolerance_minus;
	}

	

}
