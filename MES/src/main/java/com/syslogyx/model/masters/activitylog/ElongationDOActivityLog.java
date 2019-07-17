package com.syslogyx.model.masters.activitylog;

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
import com.syslogyx.model.masters.CRGradeDO;
import com.syslogyx.model.masters.ElongationDO;
import com.syslogyx.model.masters.ProcessUnitDO;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Elongation Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "elongations_activitylog")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElongationDOActivityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "elon_activity_Sequence")
	@SequenceGenerator(name = "elon_activity_Sequence", sequenceName = "ELON_ACTIVITY_SEQ", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "el_id")
	private ElongationDO el_id;

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

	@Transient
	private int updated_by_id;

	@Column(name = "status")
	public int status;

	@Transient
	private int unit_id;

	@Transient
	private int cr_grade_id;

	@Transient
	private String unit_name;

	@Transient
	private String cr_grade_name;

	@Transient
	private String updated_by_name;

	public ElongationDOActivityLog() {

	}

	public ElongationDOActivityLog(ElongationDO el_id, ProcessUnitDO unit, CRGradeDO cr_grade, UserDO created_by,
			UserDO updated_by, int status) {
		this.el_id = el_id;
		this.unit = unit;
		this.cr_grade = cr_grade;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.status = status;
	}

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

	public String getUnit_name() {
		return unit_name;
	}

	public String getCr_grade_name() {
		return cr_grade_name;
	}

	public String getUpdated_by_name() {
		return updated_by_name;
	}

	public int getUpdated_by_id() {
		return updated_by_id;
	}

	public void setUpdated_by_id(int updated_by_id) {
		this.updated_by_id = updated_by_id;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public void setCr_grade_name(String cr_grade_name) {
		this.cr_grade_name = cr_grade_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}

	public ElongationDO getEl_id() {
		return el_id;
	}

	public void setEl_id(ElongationDO el_id) {
		this.el_id = el_id;
	}

}
