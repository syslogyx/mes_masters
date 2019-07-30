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
 * for storing the data of Shift Production Log Book
 * @author Pinkesh
 *
 */

@Entity
@Table(name = "shift_production_log_book")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ShiftProductionLogBookDO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "splb_id")
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

	@Column(name = "cpl_coil_no")
	private String cpl_coil_no;

	@Column(name = "output_thickness")
	private String output_thickness;

	@Column(name = "output_cpl_width")
	private String output_cpl_width;
	
	@Column(name = "output_set_width")
	private String output_set_width;

	@Column(name = "output_measured_width")
	private String output_measured_width;

	@Column(name = "output_weight")
	private String output_weight;

	@Column(name = "remark")
	private String remark;

	@Column(name = "process_order")
	private String process_order;
	
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

	public String getCpl_coil_no() {
		return cpl_coil_no;
	}

	public void setCpl_coil_no(String cpl_coil_no) {
		this.cpl_coil_no = cpl_coil_no;
	}

	public String getOutput_thickness() {
		return output_thickness;
	}

	public void setOutput_thickness(String output_thickness) {
		this.output_thickness = output_thickness;
	}

	public String getOutput_cpl_width() {
		return output_cpl_width;
	}

	public void setOutput_cpl_width(String output_cpl_width) {
		this.output_cpl_width = output_cpl_width;
	}

	public String getOutput_set_width() {
		return output_set_width;
	}

	public void setOutput_set_width(String output_set_width) {
		this.output_set_width = output_set_width;
	}

	public String getOutput_measured_width() {
		return output_measured_width;
	}

	public void setOutput_measured_width(String output_measured_width) {
		this.output_measured_width = output_measured_width;
	}

	public String getOutput_weight() {
		return output_weight;
	}

	public void setOutput_weight(String output_weight) {
		this.output_weight = output_weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProcess_order() {
		return process_order;
	}

	public void setProcess_order(String process_order) {
		this.process_order = process_order;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	


}
