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
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Trimming Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "trimmings")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrimmingDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "trim_Sequence")
	@SequenceGenerator(name = "trim_Sequence", sequenceName = "TRIM_SEQ", allocationSize = 1)
	@Column(name = "trim_id")
	private int id;
	
	@Column(name = "trim_allo_min")
	private float trim_allo_min;
	
	@Column(name = "trim_allo_max")
	private float trim_allo_max;
	
	@Column(name = "trim_allo_aim")
	private float trim_allo_aim;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private ProcessUnitDO unit;

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

	@Column(name = "status")
	public int status;

	@Transient
	private int unit_id;

	@Transient
	private String unit_name;

	@Transient
	private String updated_by_name;

	public TrimmingDO() {

	}

	public TrimmingDO(int id, float trim_allo_min, float trim_allo_max, float trim_allo_aim,
			int unit_id, String unit_name, String updated_by_name,Date updated, int status) {

		this.id = id;
		this.trim_allo_min = trim_allo_min;
		this.trim_allo_max = trim_allo_max;
		this.trim_allo_aim = trim_allo_aim;
		this.unit_id = unit_id;
		this.unit_name = unit_name;
		this.updated_by_name = updated_by_name;
		this.updated = updated;
		this.status = status;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTrim_allo_min() {
		return trim_allo_min;
	}

	public void setTrim_allo_min(float trim_allo_min) {
		this.trim_allo_min = trim_allo_min;
	}

	public float getTrim_allo_max() {
		return trim_allo_max;
	}

	public void setTrim_allo_max(float trim_allo_max) {
		this.trim_allo_max = trim_allo_max;
	}

	public float getTrim_allo_aim() {
		return trim_allo_aim;
	}

	public void setTrim_allo_aim(float trim_allo_aim) {
		this.trim_allo_aim = trim_allo_aim;
	}

	public ProcessUnitDO getUnit() {
		return unit;
	}

	public void setUnit(ProcessUnitDO unit) {
		this.unit = unit;
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

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	
	public String getUnit_name() {
		return unit_name;
	}
	
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	
	public String getUpdated_by_name() {
		return updated_by_name;
	}
	
	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}
	

}
