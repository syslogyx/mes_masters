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
 * This class store process unit data
 * 
 * @author palash
 *
 */
@Entity
@Table(name = "process_units")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessUnitDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "processUnit_Sequence")
	@SequenceGenerator(name = "processUnit_Sequence", sequenceName = "PROCESS_SEQ", allocationSize = 1)
	@Column(name = "pu_id")
	private int id;

	@Column(name = "unit")
	private String unit;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "process_family_id")
	private ProcessFamilyDO process_family;

	@Column(name = "cost_center")
	private String cost_center;

	@Column(name = "capacity")
	private String capacity;
		
	@Column(name = "const_setup_time")
	private String const_setup_time;
					
	@Column(name = "yield")
	private String yield;
							
	@Column(name = "osp_identifier")
	private int osp_identifier;	
	
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
	private int process_family_id;

	@Transient
	private String process_family_name;

	@Transient
	private String updated_by_name;

	public ProcessUnitDO() {
	}

	public ProcessUnitDO(int id, String unit, String cost_center, String capacity, String const_setup_time,
			String yield, Integer osp_identifier, Date updated, int status, String updated_by_name,
			Integer process_family_id, String process_family_name) {
		this.id = id;
		this.unit = unit;
		this.cost_center = cost_center;
		this.capacity = capacity;
		this.const_setup_time = const_setup_time;
		this.yield = yield;

		if (osp_identifier != null)
			this.osp_identifier = osp_identifier;
		
		this.updated = updated;
		this.status = status;
		this.updated_by_name = updated_by_name;

		if (process_family_id != null)
			this.process_family_id = process_family_id;
		this.process_family_name = process_family_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProcessFamilyDO getProcess_family() {
		return process_family;
	}

	public void setProcess_family(ProcessFamilyDO process_family) {
		this.process_family = process_family;
	}

	public String getCost_center() {
		return cost_center;
	}

	public void setCost_center(String cost_center) {
		this.cost_center = cost_center;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getConst_setup_time() {
		return const_setup_time;
	}

	public void setConst_setup_time(String const_setup_time) {
		this.const_setup_time = const_setup_time;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public int getOsp_identifier() {
		return osp_identifier;
	}

	public void setOsp_identifier(int osp_identifier) {
		this.osp_identifier = osp_identifier;
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

	public int getProcess_family_id() {
		return process_family_id;
	}

	public void setProcess_family_id(int process_family_id) {
		this.process_family_id = process_family_id;
	}

	public String getProcess_family_name() {
		return process_family_name;
	}

	public String getUpdated_by_name() {
		return updated_by_name;
	}

}
