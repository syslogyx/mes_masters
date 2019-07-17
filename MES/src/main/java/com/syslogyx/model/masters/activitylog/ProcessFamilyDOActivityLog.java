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
import com.syslogyx.model.masters.ProcessFamilyDO;
import com.syslogyx.model.masters.ProcessTypeDO;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Process Family Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "process_families_activitylog")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessFamilyDOActivityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "processF_activity_Sequence")
	@SequenceGenerator(name = "processF_activity_Sequence", sequenceName = "PROCESSF_ACTIVITY_SEQ", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pf_id")
	private ProcessFamilyDO pf_id;

	@Column(name = "process_family")
	private String process_family;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "process_type_id")
	private ProcessTypeDO process_type;

	@Column(name = "priority")
	private int priority;

	@Column(name = "bucket")
	private String bucket;

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
	private int updated_by_id;

	@Transient
	private int process_type_id;

	@Transient
	private String process_type_name;

	@Transient
	private String updated_by_name;

	public ProcessFamilyDOActivityLog() {

	}

	public ProcessFamilyDOActivityLog(ProcessFamilyDO pf_id, String process_family, ProcessTypeDO process_type,
			int priority, String bucket, UserDO created_by, UserDO updated_by, int status) {
		this.pf_id = pf_id;
		this.process_family = process_family;
		this.process_type = process_type;
		this.priority = priority;
		this.bucket = bucket;
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

	public String getProcess_family() {
		return process_family;
	}

	public void setProcess_family(String process_family) {
		this.process_family = process_family;
	}

	public ProcessTypeDO getProcess_type() {
		return process_type;
	}

	public void setProcess_type(ProcessTypeDO process_type) {
		this.process_type = process_type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
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

	public int getProcess_type_id() {
		return process_type_id;
	}

	public void setProcess_type_id(int process_type_id) {
		this.process_type_id = process_type_id;
	}

	public String getProcess_type_name() {
		return process_type_name;
	}

	public void setProcess_type_name(String name) {
		this.process_type_name = name;
	}

	public String getUpdated_by_name() {
		return updated_by_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}

	public int getUpdated_by_id() {
		return updated_by_id;
	}

	public void setUpdated_by_id(int updated_by_id) {
		this.updated_by_id = updated_by_id;
	}

	public ProcessFamilyDO getPf_id() {
		return pf_id;
	}

	public void setPf_id(ProcessFamilyDO pf_id) {
		this.pf_id = pf_id;
	}

}
