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
import com.syslogyx.model.masters.CodeGroupDO;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Code_Group Data
 * 
 * @author palash
 *
 */
@Entity
@Table(name = "code_group_activitylog")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeGroupDOActivityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "codeg_activity_Sequence")
	@SequenceGenerator(name = "codeg_activity_Sequence", sequenceName = "CODE_ACTIVITY_SEQ", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cg_id")
	private CodeGroupDO cg_id;

	@Column(name = "group_code")
	private String group_code;

	@Column(name = "group_desc")
	private String group_desc;

	@Column(name = "incrementor")
	private int incrementor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "created_by", updatable = false)
	private UserDO created_by;

	@Transient
	private String updated_by_name;

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

	public CodeGroupDOActivityLog() {

	}

	public CodeGroupDOActivityLog(CodeGroupDO cg_id, String group_code, String group_desc, int incrementor,
			UserDO created_by, UserDO updated_by, int status) {
		this.cg_id = cg_id;
		this.group_code = group_code;
		this.group_desc = group_desc;
		this.incrementor = incrementor;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.status = status;
	}

	public CodeGroupDOActivityLog(int id,String group_code, String group_desc, int incrementor, Date created,
			Date updated, int status, String username) {
		
		this.id = id;
		this.group_code = group_code;
		this.group_desc = group_desc;
		this.incrementor = incrementor;
		this.created = created;
		this.updated = updated;
		this.status = status;
		this.updated_by_name = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getGroup_desc() {
		return group_desc;
	}

	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	public int getIncrementor() {
		return incrementor;
	}

	public void setIncrementor(int incrementor) {
		this.incrementor = incrementor;
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

	public String getUpdated_by_name() {
		return updated_by_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUpdated_by_id() {
		return updated_by_id;
	}

	public void setUpdated_by_id(int updated_by_id) {
		this.updated_by_id = updated_by_id;
	}

	public CodeGroupDO getCg_id() {
		return cg_id;
	}

	public void setCg_id(CodeGroupDO cg_id) {
		this.cg_id = cg_id;
	}

}
