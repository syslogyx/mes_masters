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
 * for storing the data of Coils To Process
 * @author Pinkesh
 *
 */

@Entity
@Table(name = "coils_to_process")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CoilsToProcessDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "cp_id")
	private int id;
	
	@Column(name = "date")
	private Timestamp date;
	
	@Column(name = "hr_coil_no")
	private String hr_coil_no;

	@Column(name = "input_thickness")
	private String input_thickness;

	@Column(name = "input_width")
	private String input_width;

	@Column(name = "input_weight")
	private String input_weight;

	@Column(name = "input_grade")
	private String input_grade;

	@Column(name = "required_width")
	private String required_width;

	@Column(name = "consumer_name")
	private String consumer_name;

	@Column(name = "process_order")
	private String process_order;
	
	@Column(name = "slaes_order")
	private String slaes_order;

	@Column(name = "next_route")
	private String next_route;

	@Column(name = "location")
	private String location;

	@Column(name = "priority")
	private String priority;

	@Column(name = "action")
	private String action;

	
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

	public String getInput_thickness() {
		return input_thickness;
	}

	public void setInput_thickness(String input_thickness) {
		this.input_thickness = input_thickness;
	}

	public String getInput_width() {
		return input_width;
	}

	public void setInput_width(String input_width) {
		this.input_width = input_width;
	}

	public String getInput_weight() {
		return input_weight;
	}

	public void setInput_weight(String input_weight) {
		this.input_weight = input_weight;
	}

	public String getInput_grade() {
		return input_grade;
	}

	public void setInput_grade(String input_grade) {
		this.input_grade = input_grade;
	}

	public String getRequired_width() {
		return required_width;
	}

	public void setRequired_width(String required_width) {
		this.required_width = required_width;
	}

	public String getConsumer_name() {
		return consumer_name;
	}

	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}

	public String getProcess_order() {
		return process_order;
	}

	public void setProcess_order(String process_order) {
		this.process_order = process_order;
	}

	public String getSlaes_order() {
		return slaes_order;
	}

	public void setSlaes_order(String slaes_order) {
		this.slaes_order = slaes_order;
	}

	public String getNext_route() {
		return next_route;
	}

	public void setNext_route(String next_route) {
		this.next_route = next_route;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


}
