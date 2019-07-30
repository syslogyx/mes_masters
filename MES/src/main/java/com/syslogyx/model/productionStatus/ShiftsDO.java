package com.syslogyx.model.productionStatus;

import java.sql.Timestamp;

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
 * for storing the data of Shifts
 * @author Pinkesh
 *
 */

@Entity
@Table(name = "shifts")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ShiftsDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "s_id")
	private int id;
	// 
	@Column(name = "date")
	private Timestamp date;

	@Column(name = "hr_coil_no")
	private String hr_coil_no;

	@Column(name = "start_time")
	private Timestamp start_time;

	@Column(name = "finish_time")
	private Timestamp finish_time;

	@Column(name = "delay")
	private String delay;

	@Column(name = "delay_reason")
	private String delay_reason;

	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getHr_coil_no() {
		return hr_coil_no;
	}

	public void setHr_coil_no(String hr_coil_no) {
		this.hr_coil_no = hr_coil_no;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getDelay_reason() {
		return delay_reason;
	}

	public void setDelay_reason(String delay_reason) {
		this.delay_reason = delay_reason;
	}

	
	
}
