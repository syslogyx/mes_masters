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
 * This Class store Elongation Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "elongations")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElongationDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "elon_Sequence")
	@SequenceGenerator(name = "elon_Sequence", sequenceName = "ELON_SEQ", allocationSize = 1)
	@Column(name = "el_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private ProcessUnitDO unit;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_grade_id")
	private CRGradeDO cr_grade;

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
	private int status;

	@Transient
	private int unit_id;

	@Transient
	private int cr_grade_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProcessUnitDO getUnit() {
		return unit;
	}

	public void setUnit(ProcessUnitDO unit) {
		this.unit = unit;
	}

	public CRGradeDO getCr_grade() {
		return cr_grade;
	}

	public void setCr_grade(CRGradeDO cr_grade) {
		this.cr_grade = cr_grade;
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

	public int getCr_grade_id() {
		return cr_grade_id;
	}

	public void setCr_grade_id(int cr_grade_id) {
		this.cr_grade_id = cr_grade_id;
	}

}
