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
 * This Class store DPR Target Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "dpr_targets")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DPRTargetDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "dprtarget_Sequence")
	@SequenceGenerator(name = "dprtarget_Sequence", sequenceName = "DPR_SEQ", allocationSize = 1)
	@Column(name = "dprt_id")
	private int id;

	@Column(name = "business_plan_target")
	private float business_plan_target;

	@Column(name = "internal_target")
	private float internal_target;

	@Column(name = "year")
	private String year;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private ProcessUnitDO unit;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private ProductDefDO product;

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
	private int product_id;

	@Transient
	private String product_name;

	@Transient
	private String unit_name;

	@Transient
	private String updated_by_name;

	public DPRTargetDO() {
	}

	public DPRTargetDO(int id, float business_plan_target, float internal_target, String year, Date updated, int status,
			Integer unit_id, Integer product_id, String product_name, String unit_name, String updated_by_name) {
		this.id = id;
		this.business_plan_target = business_plan_target;
		this.internal_target = internal_target;
		this.year = year;
		this.updated = updated;
		this.status = status;

		if (unit_id != null)
			this.unit_id = unit_id;

		if (product_id != null)
			this.product_id = product_id;
		this.product_name = product_name;
		this.unit_name = unit_name;
		this.updated_by_name = updated_by_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBusiness_plan_target() {
		return business_plan_target;
	}

	public void setBusiness_plan_target(float business_plan_target) {
		this.business_plan_target = business_plan_target;
	}

	public float getInternal_target() {
		return internal_target;
	}

	public void setInternal_target(float internal_target) {
		this.internal_target = internal_target;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ProcessUnitDO getUnit() {
		return unit;
	}

	public void setUnit(ProcessUnitDO unit) {
		this.unit = unit;
	}

	public ProductDefDO getProduct() {
		return product;
	}

	public void setProduct(ProductDefDO product) {
		this.product = product;
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

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getUnit_name() {
		return unit_name;
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

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public void setUpdated_by_name(String updated_by_name) {
		this.updated_by_name = updated_by_name;
	}
	

}
