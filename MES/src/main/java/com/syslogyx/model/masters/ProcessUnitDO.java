package com.syslogyx.model.masters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This class store process unit data
 * 
 * @author palash
 *
 */
@Entity
@Table(name = "process_units")
public class ProcessUnitDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "processUnit_Sequence")
	@SequenceGenerator(name = "processUnit_Sequence", sequenceName = "PROCESS_SEQ", allocationSize = 1)
	@Column(name = "pu_id")
	private int id;

	@Column(name = "unit")
	private String unit;

	@Column(name = "status")
	private int status;

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

}
