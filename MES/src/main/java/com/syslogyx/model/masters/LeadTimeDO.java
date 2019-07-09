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
 * This Class store Lead Time Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "lead_times")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeadTimeDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "leadt_Sequence")
	@SequenceGenerator(name = "leadt_Sequence", sequenceName = "LEADT_SEQ", allocationSize = 1)
	@Column(name = "lt_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "after_process_unit_id")
	private ProcessUnitDO after_process_unit;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "before_process_unit_id")
	private ProcessUnitDO before_process_unit;

	@Column(name = "idle_time_min")
	private String idle_time_min;

	@Column(name = "idle_time_max")
	private String idle_time_max;

	@Column(name = "handle_time_min")
	private String handle_time_min;

	@Column(name = "handle_time_max")
	private String handle_time_max;

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
	private int after_process_unit_id;

	@Transient
	private int before_process_unit_id;

	@Transient
	private String after_process_unit_name;

	@Transient
	private String before_process_unit_name;

	@Transient
	private String updated_by_name;

	public LeadTimeDO() {

	}

	public LeadTimeDO(int id, String idle_time_min, String idle_time_max, String handle_time_min,
			String handle_time_max, Integer before_pu_id, String before_unit, Integer after_pu_id, String after_unit,
			String username, Date updated, int status) {

		this.id = id;
		this.idle_time_min = idle_time_min;
		this.idle_time_max = idle_time_max;
		this.handle_time_min = handle_time_min;
		this.handle_time_max = handle_time_max;

		if (before_pu_id != null)
			this.before_process_unit_id = before_pu_id;

		this.before_process_unit_name = before_unit;

		if (after_pu_id != null)
			this.after_process_unit_id = after_pu_id;
		this.after_process_unit_name = after_unit;
		this.updated_by_name = username;
		this.updated = updated;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProcessUnitDO getAfter_process_unit() {
		return after_process_unit;
	}

	public void setAfter_process_unit(ProcessUnitDO after_process_unit) {
		this.after_process_unit = after_process_unit;
	}

	public ProcessUnitDO getBefore_process_unit() {
		return before_process_unit;
	}

	public void setBefore_process_unit(ProcessUnitDO before_process_unit) {
		this.before_process_unit = before_process_unit;
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

	public int getAfter_process_unit_id() {
		return after_process_unit_id;
	}

	public void setAfter_process_unit_id(int after_process_unit_id) {
		this.after_process_unit_id = after_process_unit_id;
	}

	public int getBefore_process_unit_id() {
		return before_process_unit_id;
	}

	public void setBefore_process_unit_id(int before_process_unit_id) {
		this.before_process_unit_id = before_process_unit_id;
	}

	public String getUpdated_by_name() {
		return updated_by_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}

	public String getAfter_process_unit_name() {
		return after_process_unit_name;
	}

	public void setAfter_process_unit_name(String after_process_unit_name) {
		this.after_process_unit_name = after_process_unit_name;
	}

	public String getBefore_process_unit_name() {
		return before_process_unit_name;
	}

	public void setBefore_process_unit_name(String before_process_unit_name) {
		this.before_process_unit_name = before_process_unit_name;
	}

	public String getIdle_time_min() {
		return idle_time_min;
	}

	public void setIdle_time_min(String idle_time_min) {
		this.idle_time_min = idle_time_min;
	}

	public String getIdle_time_max() {
		return idle_time_max;
	}

	public void setIdle_time_max(String idle_time_max) {
		this.idle_time_max = idle_time_max;
	}

	public String getHandle_time_min() {
		return handle_time_min;
	}

	public void setHandle_time_min(String handle_time_min) {
		this.handle_time_min = handle_time_min;
	}

	public String getHandle_time_max() {
		return handle_time_max;
	}

	public void setHandle_time_max(String handle_time_max) {
		this.handle_time_max = handle_time_max;
	}

}
