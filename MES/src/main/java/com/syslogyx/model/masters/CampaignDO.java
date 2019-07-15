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
 * This class store Campaign data
 * 
 * @author Palash
 *
 */
@Entity
@Table(name = "campaigns")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "campaign_Sequence")
	@SequenceGenerator(name = "campaign_Sequence", sequenceName = "CAMPAIGN_SEQ", allocationSize = 1)
	@Column(name = "c_id")
	private int id;

	@Column(name = "campaign_id")
	private String campaign_id;

	@Column(name = "attribute")
	private String attribute;

	@Column(name = "aim")
	private String aim;

	@Column(name = "capacity_min")
	private float capacity_min;

	@Column(name = "capacity_max")
	private float capacity_max;

	@Column(name = "priority_level")
	private int priority_level;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hold_unit")
	private ProcessUnitDO hold_unit;

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
	private int hold_unit_id;

	@Transient
	private String hold_unit_name;

	@Transient
	private String updated_by_name;

	public CampaignDO() {

	}

	public CampaignDO(int id, String campaign_id, String attribute, String aim, float capacity_min, float capacity_max,
			int priority_level, Date created, Date updated, int status, String username, String unit, Integer pu_id) {

		this.id = id;
		this.campaign_id = campaign_id;
		this.attribute = attribute;
		this.aim = aim;
		this.capacity_min = capacity_min;
		this.capacity_max = capacity_max;
		this.priority_level = priority_level;
		this.created = created;
		this.updated = updated;
		this.status = status;
		this.updated_by_name = username;
		this.hold_unit_name = unit;

		if (pu_id != null)
			this.hold_unit_id = pu_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCampaign_id() {
		return campaign_id;
	}
	
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public float getCapacity_min() {
		return capacity_min;
	}

	public void setCapacity_min(float capacity_min) {
		this.capacity_min = capacity_min;
	}

	public float getCapacity_max() {
		return capacity_max;
	}

	public void setCapacity_max(float capacity_max) {
		this.capacity_max = capacity_max;
	}

	public int getPriority_level() {
		return priority_level;
	}

	public void setPriority_level(int priority_level) {
		this.priority_level = priority_level;
	}

	public ProcessUnitDO getHold_unit() {
		return hold_unit;
	}

	public void setHold_unit(ProcessUnitDO hold_unit) {
		this.hold_unit = hold_unit;
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

	public int getHold_unit_id() {
		return hold_unit_id;
	}

	public void setHold_unit_id(int hold_unit_id) {
		this.hold_unit_id = hold_unit_id;
	}

	public String getUpdated_by_name() {
		return updated_by_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}

	public String getHold_unit_name() {
		return hold_unit_name;
	}

	public void setHold_unit_name(String hold_unit_name) {
		this.hold_unit_name = hold_unit_name;
	}

	public int getUpdated_by_id() {
		return updated_by_id;
	}

	public void setUpdated_by_id(int updated_by_id) {
		this.updated_by_id = updated_by_id;
	}
	
}
