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
import com.syslogyx.model.masters.ProductDefDO;
import com.syslogyx.model.masters.ProductFormDO;
import com.syslogyx.model.masters.ProductTypeDO;
import com.syslogyx.model.user.UserDO;

/**
 * This Class store Product Definition Data
 * 
 * @author namrata
 *
 */
@Entity
@Table(name = "product_definition_activitylog")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDefDOActivityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productDef_activity_Sequence")
	@SequenceGenerator(name = "productDef_activity_Sequence", sequenceName = "PRODUCTDEF_ACTIVITY_SEQ", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pd_id")
	private ProductDefDO pd_id;

	@Column(name = "product")
	private String product;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_type_id")
	private ProductTypeDO product_type;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_form_id")
	private ProductFormDO product_form;

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

	@Transient
	private String product_type_name;

	@Transient
	private int updated_by_id;

	@Transient
	private int product_form_id;

	@Transient
	private String product_form_name;

	@Transient
	private String updated_by_name;

	public ProductDefDOActivityLog() {

	}

	public ProductDefDOActivityLog(ProductDefDO pd_id, String product, ProductTypeDO product_type,
			ProductFormDO product_form, UserDO created_by, UserDO updated_by, int status) {
		this.pd_id = pd_id;
		this.product = product;
		this.product_type = product_type;
		this.product_form = product_form;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public ProductFormDO getProduct_form() {
		return product_form;
	}

	public void setProduct_form(ProductFormDO product_form) {
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

	public int getProduct_form_id() {
		return product_form_id;
	}

	public void setProduct_form_id(int product_form_id) {
		this.product_form_id = product_form_id;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getProduct_form_name() {
		return product_form_name;
	}

	public void setProduct_form_name(String product_form_name) {
		this.product_form_name = product_form_name;
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

	public ProductDefDO getPd_id() {
		return pd_id;
	}

	public void setPd_id(ProductDefDO pd_id) {
		this.pd_id = pd_id;
	}

}
