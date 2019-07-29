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
 *  for storing the data of downtime register
 * @author pinkesh
 *
 */
@Entity
@Table(name = "downtime_register")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DowntimeRegisterDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "dr_id")
	private int id;

	@Column(name = "type_of_delay")
	private String type_of_delay;
	
	@Column(name = "start_time")
	private Timestamp start_time;
	
	@Column(name = "finish_time")
	private Timestamp finish_time;
	
	@Column(name = "duration")
	private Timestamp duration;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "agency")
	private String agency;
	
	@Column(name = "action_taken")
	private String action_taken;
	
	@Column(name = "remark")
	private String remark;

	
	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType_of_delay() {
		return type_of_delay;
	}

	public void setType_of_delay(String type_of_delay) {
		this.type_of_delay = type_of_delay;
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

	public Timestamp getDuration() {
		return duration;
	}

	public void setDuration(Timestamp duration) {
		this.duration = duration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
