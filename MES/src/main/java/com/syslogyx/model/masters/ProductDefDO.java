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
 * This Class store Product Definition Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "product_definition")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDefDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productDef_Sequence")
	@SequenceGenerator(name = "productDef_Sequence", sequenceName = "PRODUCTDEF_SEQ", allocationSize = 1)
	@Column(name = "pd_id")
	private int id;

	@Column(name = "product_form")
	private String product_form;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_type_id")
	private ProductTypeDO product_type;

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
	private int product_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_form() {
		return product_form;
	}

	public void setProduct_form(String product_form) {
		this.product_form = product_form;
	}

	public ProductTypeDO getProduct_type() {
		return product_type;
	}

	public void setProduct_type(ProductTypeDO product_type) {
		this.product_type = product_type;
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

	public int getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(int product_type_id) {
		this.product_type_id = product_type_id;
	}

}
