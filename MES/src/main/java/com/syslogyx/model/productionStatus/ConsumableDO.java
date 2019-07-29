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
 * for storing the data of Consumable
 * @author test
 *
 */

@Entity
@Table(name = "consumable")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumableDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "co_id")
	private int id;
	// 
	@Column(name = "ra_1")
	private String ra_1;

	@Column(name = "ra_2")
	private String ra_2;

	@Column(name = "fresh_hcl_1")
	private String fresh_hcl_1;

	@Column(name = "fresh_hcl_2")
	private String fresh_hcl_2;

	@Column(name = "inhibitor_1")
	private String inhibitor_1;

	@Column(name = "inhibitor_2")
	private String inhibitor_2;

	@Column(name = "rp_oil_1")
	private String rp_oil_1;

	@Column(name = "rp_oil_2")
	private String rp_oil_2;

	@Column(name = "time_1")
	private String time_1;

	@Column(name = "time_2")
	private String time_2;

	@Column(name = "tank_level_1")
	private String tank_level_1;

	@Column(name = "tank_level_2")
	private String tank_level_2;

	@Column(name = "spent_liquor_1")
	private String spent_liquor_1;

	@Column(name = "spent_liquor_2")
	private String apt3_conc_actual2;

	@Column(name = "rinse_water_1")
	private String rinse_water_1;

	@Column(name = "rinse_water_2")
	private String rinse_water_2;
	
	@Column(name = "side_trimmer_knife_change_1")
	private String side_trimmer_knife_change_1;

	@Column(name = "side_trimmer_knife_change_2")
	private String side_trimmer_knife_change_2;

	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRa_1() {
		return ra_1;
	}

	public void setRa_1(String ra_1) {
		this.ra_1 = ra_1;
	}

	public String getRa_2() {
		return ra_2;
	}

	public void setRa_2(String ra_2) {
		this.ra_2 = ra_2;
	}

	public String getFresh_hcl_1() {
		return fresh_hcl_1;
	}

	public void setFresh_hcl_1(String fresh_hcl_1) {
		this.fresh_hcl_1 = fresh_hcl_1;
	}

	public String getFresh_hcl_2() {
		return fresh_hcl_2;
	}

	public void setFresh_hcl_2(String fresh_hcl_2) {
		this.fresh_hcl_2 = fresh_hcl_2;
	}

	public String getInhibitor_1() {
		return inhibitor_1;
	}

	public void setInhibitor_1(String inhibitor_1) {
		this.inhibitor_1 = inhibitor_1;
	}

	public String getInhibitor_2() {
		return inhibitor_2;
	}

	public void setInhibitor_2(String inhibitor_2) {
		this.inhibitor_2 = inhibitor_2;
	}

	public String getRp_oil_1() {
		return rp_oil_1;
	}

	public void setRp_oil_1(String rp_oil_1) {
		this.rp_oil_1 = rp_oil_1;
	}

	public String getRp_oil_2() {
		return rp_oil_2;
	}

	public void setRp_oil_2(String rp_oil_2) {
		this.rp_oil_2 = rp_oil_2;
	}

	public String getTime_1() {
		return time_1;
	}

	public void setTime_1(String time_1) {
		this.time_1 = time_1;
	}

	public String getTime_2() {
		return time_2;
	}

	public void setTime_2(String time_2) {
		this.time_2 = time_2;
	}

	public String getTank_level_1() {
		return tank_level_1;
	}

	public void setTank_level_1(String tank_level_1) {
		this.tank_level_1 = tank_level_1;
	}

	public String getTank_level_2() {
		return tank_level_2;
	}

	public void setTank_level_2(String tank_level_2) {
		this.tank_level_2 = tank_level_2;
	}

	public String getSpent_liquor_1() {
		return spent_liquor_1;
	}

	public void setSpent_liquor_1(String spent_liquor_1) {
		this.spent_liquor_1 = spent_liquor_1;
	}

	public String getApt3_conc_actual2() {
		return apt3_conc_actual2;
	}

	public void setApt3_conc_actual2(String apt3_conc_actual2) {
		this.apt3_conc_actual2 = apt3_conc_actual2;
	}

	public String getRinse_water_1() {
		return rinse_water_1;
	}

	public void setRinse_water_1(String rinse_water_1) {
		this.rinse_water_1 = rinse_water_1;
	}

	public String getRinse_water_2() {
		return rinse_water_2;
	}

	public void setRinse_water_2(String rinse_water_2) {
		this.rinse_water_2 = rinse_water_2;
	}

	public String getSide_trimmer_knife_change_1() {
		return side_trimmer_knife_change_1;
	}

	public void setSide_trimmer_knife_change_1(String side_trimmer_knife_change_1) {
		this.side_trimmer_knife_change_1 = side_trimmer_knife_change_1;
	}

	public String getSide_trimmer_knife_change_2() {
		return side_trimmer_knife_change_2;
	}

	public void setSide_trimmer_knife_change_2(String side_trimmer_knife_change_2) {
		this.side_trimmer_knife_change_2 = side_trimmer_knife_change_2;
	}


	
}
