package com.syslogyx.model.productionStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * for storing the data of scrap_monitoring
 * @author Pinkesh
 *
 */

@Entity
@Table(name = "scrap_monitoring")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ScrapMonitoringDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "sm_id")
	private int id;
	// 
	@Column(name = "hr_baby_coil_size")
	private String hr_baby_coil_size;

	@Column(name = "hr_baby_coil_weight")
	private String hr_baby_coil_weight;

	@Column(name = "side_trimming_size")
	private String side_trimming_size;

	@Column(name = "side_trimming_weight")
	private String side_trimming_weight;

	@Column(name = "coil_end_size")
	private String coil_end_size;

	@Column(name = "coil_end_weight")
	private String coil_end_weight;

	@Column(name = "hr_patta_size")
	private String hr_patta_size;

	@Column(name = "hr_patta_weight")
	private String hr_patta_weight;

	
	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHr_baby_coil_size() {
		return hr_baby_coil_size;
	}

	public void setHr_baby_coil_size(String hr_baby_coil_size) {
		this.hr_baby_coil_size = hr_baby_coil_size;
	}

	public String getHr_baby_coil_weight() {
		return hr_baby_coil_weight;
	}

	public void setHr_baby_coil_weight(String hr_baby_coil_weight) {
		this.hr_baby_coil_weight = hr_baby_coil_weight;
	}

	public String getSide_trimming_size() {
		return side_trimming_size;
	}

	public void setSide_trimming_size(String side_trimming_size) {
		this.side_trimming_size = side_trimming_size;
	}

	public String getSide_trimming_weight() {
		return side_trimming_weight;
	}

	public void setSide_trimming_weight(String side_trimming_weight) {
		this.side_trimming_weight = side_trimming_weight;
	}

	public String getCoil_end_size() {
		return coil_end_size;
	}

	public void setCoil_end_size(String coil_end_size) {
		this.coil_end_size = coil_end_size;
	}

	public String getCoil_end_weight() {
		return coil_end_weight;
	}

	public void setCoil_end_weight(String coil_end_weight) {
		this.coil_end_weight = coil_end_weight;
	}

	public String getHr_patta_size() {
		return hr_patta_size;
	}

	public void setHr_patta_size(String hr_patta_size) {
		this.hr_patta_size = hr_patta_size;
	}

	public String getHr_patta_weight() {
		return hr_patta_weight;
	}

	public void setHr_patta_weight(String hr_patta_weight) {
		this.hr_patta_weight = hr_patta_weight;
	}

	

}
