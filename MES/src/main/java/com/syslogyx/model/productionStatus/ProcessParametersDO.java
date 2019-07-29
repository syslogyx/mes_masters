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
 *  for storing the data of Process Parameters
 * @author Pinkesh
 *
 */

@Entity
@Table(name = "process_parameters")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessParametersDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "pp_id")
	private int id;
	// APT1
	@Column(name = "apt1_conc_actual1")
	private String apt1_conc_actual1;

	@Column(name = "apt1_conc_actual2")
	private String apt1_conc_actual2;

	@Column(name = "apt1_fe_actual1")
	private String apt1_fe_actual1;

	@Column(name = "apt1_fe_actual2")
	private String apt1_fe_actual2;

	@Column(name = "apt1_temp_actual1")
	private String apt1_temp_actual1;

	@Column(name = "apt1_temp_actual2")
	private String apt1_temp_actual2;

	// APT2
	@Column(name = "apt2_conc_actual1")
	private String apt2_conc_actual1;

	@Column(name = "apt2_conc_actual2")
	private String apt2_conc_actual2;

	@Column(name = "apt2_fe_actual1")
	private String apt2_fe_actual1;

	@Column(name = "apt2_fe_actual2")
	private String apt2_fe_actual2;

	@Column(name = "apt2_temp_actual1")
	private String apt2_temp_actual1;

	@Column(name = "apt2_temp_actual2")
	private String apt2_temp_actual2;

	// APT3
	@Column(name = "apt3_conc_actual1")
	private String apt3_conc_actual1;

	@Column(name = "apt3_conc_actual2")
	private String apt3_conc_actual2;

	@Column(name = "apt3_fe_actual1")
	private String apt3_fe_actual1;

	@Column(name = "apt3_fe_actual2")
	private String apt3_fe_actual2;

	@Column(name = "apt3_temp_actual1")
	private String apt3_temp_actual1;

	@Column(name = "apt3_temp_actual2")
	private String apt3_temp_actual2;

	// APT4
	@Column(name = "apt4_conc_actual1")
	private String apt4_conc_actual1;

	@Column(name = "apt4_conc_actual2")
	private String apt4_conc_actual2;

	@Column(name = "apt4_fe_actual1")
	private String apt4_fe_actual1;

	@Column(name = "apt4_fe_actual2")
	private String apt4_fe_actual2;

	@Column(name = "apt4_temp_actual1")
	private String apt4_temp_actual1;

	@Column(name = "apt4_temp_actual2")
	private String apt4_temp_actual2;

	// APT5
	@Column(name = "apt5_conc_actual1")
	private String apt5_conc_actual1;

	@Column(name = "apt5_conc_actual2")
	private String apt5_conc_actual2;

	@Column(name = "apt5_fe_actual1")
	private String apt5_fe_actual1;

	@Column(name = "apt5_fe_actual2")
	private String apt5_fe_actual2;

	@Column(name = "apt5_temp_actual1")
	private String apt5_temp_actual1;

	@Column(name = "apt5_temp_actual2")
	private String apt5_temp_actual2;

	// Rinse Chember
	@Column(name = "rinse_chember_ph_actual1")
	private String rinse_chember_ph_actual1;

	@Column(name = "rinse_chember_ph_actual2")
	private String rinse_chember_ph_actual2;

	@Column(name = "rinse_chember_chloride_actual1")
	private String rinse_chember_chloride_actual1;

	@Column(name = "rinse_chember_chloride_actual2")
	private String rinse_chember_chloride_actual2;

	// Scrubber Unit
	@Column(name = "scrubber_unit_conc_actual1")
	private String scrubber_unit_conc_actual1;

	@Column(name = "scrubber_unit_conc_actual2")
	private String scrubber_unit_conc_actual2;

	// Dryer No.1
	@Column(name = "dryer_no1_temperature_actual1")
	private String dryer_no1_temperature_actual1;

	@Column(name = "dryer_no1_temperature_actual2")
	private String dryer_no1_temperature_actual2;

	
	// Dryer No.2
	@Column(name = "dryer_no2_temperature_actual1")
	private String dryer_no2_temperature_actual1;

	@Column(name = "dryer_no2_temperature_actual2")
	private String dryer_no2_temperature_actual2;

	
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApt1_conc_actual1() {
		return apt1_conc_actual1;
	}

	public void setApt1_conc_actual1(String apt1_conc_actual1) {
		this.apt1_conc_actual1 = apt1_conc_actual1;
	}

	public String getApt1_conc_actual2() {
		return apt1_conc_actual2;
	}

	public void setApt1_conc_actual2(String apt1_conc_actual2) {
		this.apt1_conc_actual2 = apt1_conc_actual2;
	}

	public String getApt1_fe_actual1() {
		return apt1_fe_actual1;
	}

	public void setApt1_fe_actual1(String apt1_fe_actual1) {
		this.apt1_fe_actual1 = apt1_fe_actual1;
	}

	public String getApt1_fe_actual2() {
		return apt1_fe_actual2;
	}

	public void setApt1_fe_actual2(String apt1_fe_actual2) {
		this.apt1_fe_actual2 = apt1_fe_actual2;
	}

	public String getApt1_temp_actual1() {
		return apt1_temp_actual1;
	}

	public void setApt1_temp_actual1(String apt1_temp_actual1) {
		this.apt1_temp_actual1 = apt1_temp_actual1;
	}

	public String getApt1_temp_actual2() {
		return apt1_temp_actual2;
	}

	public void setApt1_temp_actual2(String apt1_temp_actual2) {
		this.apt1_temp_actual2 = apt1_temp_actual2;
	}

	public String getApt2_conc_actual1() {
		return apt2_conc_actual1;
	}

	public void setApt2_conc_actual1(String apt2_conc_actual1) {
		this.apt2_conc_actual1 = apt2_conc_actual1;
	}

	public String getApt2_conc_actual2() {
		return apt2_conc_actual2;
	}

	public void setApt2_conc_actual2(String apt2_conc_actual2) {
		this.apt2_conc_actual2 = apt2_conc_actual2;
	}

	public String getApt2_fe_actual1() {
		return apt2_fe_actual1;
	}

	public void setApt2_fe_actual1(String apt2_fe_actual1) {
		this.apt2_fe_actual1 = apt2_fe_actual1;
	}

	public String getApt2_fe_actual2() {
		return apt2_fe_actual2;
	}

	public void setApt2_fe_actual2(String apt2_fe_actual2) {
		this.apt2_fe_actual2 = apt2_fe_actual2;
	}

	public String getApt2_temp_actual1() {
		return apt2_temp_actual1;
	}

	public void setApt2_temp_actual1(String apt2_temp_actual1) {
		this.apt2_temp_actual1 = apt2_temp_actual1;
	}

	public String getApt2_temp_actual2() {
		return apt2_temp_actual2;
	}

	public void setApt2_temp_actual2(String apt2_temp_actual2) {
		this.apt2_temp_actual2 = apt2_temp_actual2;
	}

	public String getApt3_conc_actual1() {
		return apt3_conc_actual1;
	}

	public void setApt3_conc_actual1(String apt3_conc_actual1) {
		this.apt3_conc_actual1 = apt3_conc_actual1;
	}

	public String getApt3_conc_actual2() {
		return apt3_conc_actual2;
	}

	public void setApt3_conc_actual2(String apt3_conc_actual2) {
		this.apt3_conc_actual2 = apt3_conc_actual2;
	}

	public String getApt3_fe_actual1() {
		return apt3_fe_actual1;
	}

	public void setApt3_fe_actual1(String apt3_fe_actual1) {
		this.apt3_fe_actual1 = apt3_fe_actual1;
	}

	public String getApt3_fe_actual2() {
		return apt3_fe_actual2;
	}

	public void setApt3_fe_actual2(String apt3_fe_actual2) {
		this.apt3_fe_actual2 = apt3_fe_actual2;
	}

	public String getApt3_temp_actual1() {
		return apt3_temp_actual1;
	}

	public void setApt3_temp_actual1(String apt3_temp_actual1) {
		this.apt3_temp_actual1 = apt3_temp_actual1;
	}

	public String getApt3_temp_actual2() {
		return apt3_temp_actual2;
	}

	public void setApt3_temp_actual2(String apt3_temp_actual2) {
		this.apt3_temp_actual2 = apt3_temp_actual2;
	}

	public String getApt4_conc_actual1() {
		return apt4_conc_actual1;
	}

	public void setApt4_conc_actual1(String apt4_conc_actual1) {
		this.apt4_conc_actual1 = apt4_conc_actual1;
	}

	public String getApt4_conc_actual2() {
		return apt4_conc_actual2;
	}

	public void setApt4_conc_actual2(String apt4_conc_actual2) {
		this.apt4_conc_actual2 = apt4_conc_actual2;
	}

	public String getApt4_fe_actual1() {
		return apt4_fe_actual1;
	}

	public void setApt4_fe_actual1(String apt4_fe_actual1) {
		this.apt4_fe_actual1 = apt4_fe_actual1;
	}

	public String getApt4_fe_actual2() {
		return apt4_fe_actual2;
	}

	public void setApt4_fe_actual2(String apt4_fe_actual2) {
		this.apt4_fe_actual2 = apt4_fe_actual2;
	}

	public String getApt4_temp_actual1() {
		return apt4_temp_actual1;
	}

	public void setApt4_temp_actual1(String apt4_temp_actual1) {
		this.apt4_temp_actual1 = apt4_temp_actual1;
	}

	public String getApt4_temp_actual2() {
		return apt4_temp_actual2;
	}

	public void setApt4_temp_actual2(String apt4_temp_actual2) {
		this.apt4_temp_actual2 = apt4_temp_actual2;
	}

	public String getApt5_conc_actual1() {
		return apt5_conc_actual1;
	}

	public void setApt5_conc_actual1(String apt5_conc_actual1) {
		this.apt5_conc_actual1 = apt5_conc_actual1;
	}

	public String getApt5_conc_actual2() {
		return apt5_conc_actual2;
	}

	public void setApt5_conc_actual2(String apt5_conc_actual2) {
		this.apt5_conc_actual2 = apt5_conc_actual2;
	}

	public String getApt5_fe_actual1() {
		return apt5_fe_actual1;
	}

	public void setApt5_fe_actual1(String apt5_fe_actual1) {
		this.apt5_fe_actual1 = apt5_fe_actual1;
	}

	public String getApt5_fe_actual2() {
		return apt5_fe_actual2;
	}

	public void setApt5_fe_actual2(String apt5_fe_actual2) {
		this.apt5_fe_actual2 = apt5_fe_actual2;
	}

	public String getApt5_temp_actual1() {
		return apt5_temp_actual1;
	}

	public void setApt5_temp_actual1(String apt5_temp_actual1) {
		this.apt5_temp_actual1 = apt5_temp_actual1;
	}

	public String getApt5_temp_actual2() {
		return apt5_temp_actual2;
	}

	public void setApt5_temp_actual2(String apt5_temp_actual2) {
		this.apt5_temp_actual2 = apt5_temp_actual2;
	}

	public String getRinse_chember_ph_actual1() {
		return rinse_chember_ph_actual1;
	}

	public void setRinse_chember_ph_actual1(String rinse_chember_ph_actual1) {
		this.rinse_chember_ph_actual1 = rinse_chember_ph_actual1;
	}

	public String getRinse_chember_ph_actual2() {
		return rinse_chember_ph_actual2;
	}

	public void setRinse_chember_ph_actual2(String rinse_chember_ph_actual2) {
		this.rinse_chember_ph_actual2 = rinse_chember_ph_actual2;
	}

	public String getRinse_chember_chloride_actual1() {
		return rinse_chember_chloride_actual1;
	}

	public void setRinse_chember_chloride_actual1(String rinse_chember_chloride_actual1) {
		this.rinse_chember_chloride_actual1 = rinse_chember_chloride_actual1;
	}

	public String getRinse_chember_chloride_actual2() {
		return rinse_chember_chloride_actual2;
	}

	public void setRinse_chember_chloride_actual2(String rinse_chember_chloride_actual2) {
		this.rinse_chember_chloride_actual2 = rinse_chember_chloride_actual2;
	}

	public String getScrubber_unit_conc_actual1() {
		return scrubber_unit_conc_actual1;
	}

	public void setScrubber_unit_conc_actual1(String scrubber_unit_conc_actual1) {
		this.scrubber_unit_conc_actual1 = scrubber_unit_conc_actual1;
	}

	public String getScrubber_unit_conc_actual2() {
		return scrubber_unit_conc_actual2;
	}

	public void setScrubber_unit_conc_actual2(String scrubber_unit_conc_actual2) {
		this.scrubber_unit_conc_actual2 = scrubber_unit_conc_actual2;
	}

	public String getDryer_no1_temperature_actual1() {
		return dryer_no1_temperature_actual1;
	}

	public void setDryer_no1_temperature_actual1(String dryer_no1_temperature_actual1) {
		this.dryer_no1_temperature_actual1 = dryer_no1_temperature_actual1;
	}

	public String getDryer_no1_temperature_actual2() {
		return dryer_no1_temperature_actual2;
	}

	public void setDryer_no1_temperature_actual2(String dryer_no1_temperature_actual2) {
		this.dryer_no1_temperature_actual2 = dryer_no1_temperature_actual2;
	}

	public String getDryer_no2_temperature_actual1() {
		return dryer_no2_temperature_actual1;
	}

	public void setDryer_no2_temperature_actual1(String dryer_no2_temperature_actual1) {
		this.dryer_no2_temperature_actual1 = dryer_no2_temperature_actual1;
	}

	public String getDryer_no2_temperature_actual2() {
		return dryer_no2_temperature_actual2;
	}

	public void setDryer_no2_temperature_actual2(String dryer_no2_temperature_actual2) {
		this.dryer_no2_temperature_actual2 = dryer_no2_temperature_actual2;
	}

	
	
	
}
