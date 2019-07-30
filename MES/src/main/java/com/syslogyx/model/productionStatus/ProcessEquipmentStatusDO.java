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
 *  for storing the data of Process Equipment Status
 * @author Pinkesh
 *
 */
@Entity
@Table(name = "process_equipment_status")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProcessEquipmentStatusDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shift_Sequence")
	@SequenceGenerator(name = "shift_Sequence", sequenceName = "SHIFT_SEQ", allocationSize = 1)
	@Column(name = "pes_id")
	private int id;
	// 
	@Column(name = "apt_1_runnig")
	private String apt_1_runnig;

	@Column(name = "apt_1_standby")
	private String apt_1_standby;

	@Column(name = "apt_2_runnig")
	private String apt_2_runnig;

	@Column(name = "apt_2_standby")
	private String apt_2_standby;

	@Column(name = "apt_3_runnig")
	private String apt_3_runnig;

	@Column(name = "apt_3_standby")
	private String apt_3_standby;

	@Column(name = "apt_4_runnig")
	private String apt_4_runnig;

	@Column(name = "apt_4_standby")
	private String apt_4_standby;

	@Column(name = "apt_5_runnig")
	private String apt_5_runnig;

	@Column(name = "apt_5_standby")
	private String apt_5_standby;

	@Column(name = "csru_1_running")
	private String csru_1_running;

	@Column(name = "csru_1_standby")
	private String csru_1_standby;

	@Column(name = "csru_2_running")
	private String csru_2_running;

	@Column(name = "csru_2_standby")
	private String csru_2_standby;

	@Column(name = "csru_3_running")
	private String csru_3_running;

	@Column(name = "csru_3_standby")
	private String csru_3_standby;
	
	@Column(name = "csru_4_running")
	private String csru_4_running;

	@Column(name = "csru_4_standby")
	private String csru_4_standby;
	
	@Column(name = "csru_5_running")
	private String csru_5_running;

	@Column(name = "csru_5_standby")
	private String csru_5_standby;
	
	@Column(name = "scrubber_pump_running")
	private String scrubber_pump_running;

	@Column(name = "scrubber_pump_standby")
	private String scrubber_pump_standby;

	@Column(name = "equipments_running")
	private String equipments_running;

	@Column(name = "equipments_standby")
	private String equipments_standby;

	@Column(name = "hot_air_dryer_running")
	private String hot_air_dryer_running;

	@Column(name = "hot_air_dryer_standby")
	private String hot_air_dryer_standby;

	@Column(name = "scrubber_blower_running")
	private String scrubber_blower_running;

	@Column(name = "scrubber_blower_standby")
	private String scrubber_blower_standby;

	@Column(name = "side_trimmer_running")
	private String side_trimmer_running;

	@Column(name = "side_trimmer_standby")
	private String side_trimmer_standby;

	@Column(name = "csb_1_crane_health")
	private String csb_1_crane_health;

	@Column(name = "csb_1_remarks")
	private String csb_1_remarks;
	
	@Column(name = "csb_2_crane_health")
	private String csb_2_crane_health;

	@Column(name = "csb_2_remarks")
	private String csb_2_remarks;

	@Column(name = "mt5_old_crane_health") //5mt_old_crane_health
	private String mt5_old_crane_health;

	@Column(name = "mt5_old_remarks") // 5mt_old_remarks
	private String mt5_old_remarks;

	@Column(name = "mt5_new_crane_health") //5mt_new_crane_health
	private String mt5_new_crane_health;

	@Column(name = "mt5_new_remarks") //5mt_new_remarks
	private String mt5_new_remarks;

	@Column(name = "cpl_exit_25mt_crane_health")
	private String cpl_exit_25mt_crane_health;

	@Column(name = "cpl_exit_25mt_remarks")
	private String cpl_exit_25mt_remarks;

	@Column(name = "interlock_status_crane_health")
	private String interlock_status_crane_health;

	@Column(name = "interlock_status_remarks")
	private String interlock_status_remarks;

	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApt_1_runnig() {
		return apt_1_runnig;
	}

	public void setApt_1_runnig(String apt_1_runnig) {
		this.apt_1_runnig = apt_1_runnig;
	}

	public String getApt_1_standby() {
		return apt_1_standby;
	}

	public void setApt_1_standby(String apt_1_standby) {
		this.apt_1_standby = apt_1_standby;
	}

	public String getApt_2_runnig() {
		return apt_2_runnig;
	}

	public void setApt_2_runnig(String apt_2_runnig) {
		this.apt_2_runnig = apt_2_runnig;
	}

	public String getApt_2_standby() {
		return apt_2_standby;
	}

	public void setApt_2_standby(String apt_2_standby) {
		this.apt_2_standby = apt_2_standby;
	}

	public String getApt_3_runnig() {
		return apt_3_runnig;
	}

	public void setApt_3_runnig(String apt_3_runnig) {
		this.apt_3_runnig = apt_3_runnig;
	}

	public String getApt_3_standby() {
		return apt_3_standby;
	}

	public void setApt_3_standby(String apt_3_standby) {
		this.apt_3_standby = apt_3_standby;
	}

	public String getApt_4_runnig() {
		return apt_4_runnig;
	}

	public void setApt_4_runnig(String apt_4_runnig) {
		this.apt_4_runnig = apt_4_runnig;
	}

	public String getApt_4_standby() {
		return apt_4_standby;
	}

	public void setApt_4_standby(String apt_4_standby) {
		this.apt_4_standby = apt_4_standby;
	}

	public String getApt_5_runnig() {
		return apt_5_runnig;
	}

	public void setApt_5_runnig(String apt_5_runnig) {
		this.apt_5_runnig = apt_5_runnig;
	}

	public String getApt_5_standby() {
		return apt_5_standby;
	}

	public void setApt_5_standby(String apt_5_standby) {
		this.apt_5_standby = apt_5_standby;
	}

	public String getCsru_1_running() {
		return csru_1_running;
	}

	public void setCsru_1_running(String csru_1_running) {
		this.csru_1_running = csru_1_running;
	}

	public String getCsru_1_standby() {
		return csru_1_standby;
	}

	public void setCsru_1_standby(String csru_1_standby) {
		this.csru_1_standby = csru_1_standby;
	}

	public String getCsru_2_running() {
		return csru_2_running;
	}

	public void setCsru_2_running(String csru_2_running) {
		this.csru_2_running = csru_2_running;
	}

	public String getCsru_2_standby() {
		return csru_2_standby;
	}

	public void setCsru_2_standby(String csru_2_standby) {
		this.csru_2_standby = csru_2_standby;
	}

	public String getCsru_3_running() {
		return csru_3_running;
	}

	public void setCsru_3_running(String csru_3_running) {
		this.csru_3_running = csru_3_running;
	}

	public String getCsru_3_standby() {
		return csru_3_standby;
	}

	public void setCsru_3_standby(String csru_3_standby) {
		this.csru_3_standby = csru_3_standby;
	}

	public String getCsru_4_running() {
		return csru_4_running;
	}

	public void setCsru_4_running(String csru_4_running) {
		this.csru_4_running = csru_4_running;
	}

	public String getCsru_4_standby() {
		return csru_4_standby;
	}

	public void setCsru_4_standby(String csru_4_standby) {
		this.csru_4_standby = csru_4_standby;
	}

	public String getCsru_5_running() {
		return csru_5_running;
	}

	public void setCsru_5_running(String csru_5_running) {
		this.csru_5_running = csru_5_running;
	}

	public String getCsru_5_standby() {
		return csru_5_standby;
	}

	public void setCsru_5_standby(String csru_5_standby) {
		this.csru_5_standby = csru_5_standby;
	}

	public String getScrubber_pump_running() {
		return scrubber_pump_running;
	}

	public void setScrubber_pump_running(String scrubber_pump_running) {
		this.scrubber_pump_running = scrubber_pump_running;
	}

	public String getScrubber_pump_standby() {
		return scrubber_pump_standby;
	}

	public void setScrubber_pump_standby(String scrubber_pump_standby) {
		this.scrubber_pump_standby = scrubber_pump_standby;
	}

	public String getEquipments_running() {
		return equipments_running;
	}

	public void setEquipments_running(String equipments_running) {
		this.equipments_running = equipments_running;
	}

	public String getEquipments_standby() {
		return equipments_standby;
	}

	public void setEquipments_standby(String equipments_standby) {
		this.equipments_standby = equipments_standby;
	}

	public String getHot_air_dryer_running() {
		return hot_air_dryer_running;
	}

	public void setHot_air_dryer_running(String hot_air_dryer_running) {
		this.hot_air_dryer_running = hot_air_dryer_running;
	}

	public String getHot_air_dryer_standby() {
		return hot_air_dryer_standby;
	}

	public void setHot_air_dryer_standby(String hot_air_dryer_standby) {
		this.hot_air_dryer_standby = hot_air_dryer_standby;
	}

	public String getScrubber_blower_running() {
		return scrubber_blower_running;
	}

	public void setScrubber_blower_running(String scrubber_blower_running) {
		this.scrubber_blower_running = scrubber_blower_running;
	}

	public String getScrubber_blower_standby() {
		return scrubber_blower_standby;
	}

	public void setScrubber_blower_standby(String scrubber_blower_standby) {
		this.scrubber_blower_standby = scrubber_blower_standby;
	}

	public String getSide_trimmer_running() {
		return side_trimmer_running;
	}

	public void setSide_trimmer_running(String side_trimmer_running) {
		this.side_trimmer_running = side_trimmer_running;
	}

	public String getSide_trimmer_standby() {
		return side_trimmer_standby;
	}

	public void setSide_trimmer_standby(String side_trimmer_standby) {
		this.side_trimmer_standby = side_trimmer_standby;
	}

	public String getCsb_1_crane_health() {
		return csb_1_crane_health;
	}

	public void setCsb_1_crane_health(String csb_1_crane_health) {
		this.csb_1_crane_health = csb_1_crane_health;
	}

	public String getCsb_1_remarks() {
		return csb_1_remarks;
	}

	public void setCsb_1_remarks(String csb_1_remarks) {
		this.csb_1_remarks = csb_1_remarks;
	}

	public String getCsb_2_crane_health() {
		return csb_2_crane_health;
	}

	public void setCsb_2_crane_health(String csb_2_crane_health) {
		this.csb_2_crane_health = csb_2_crane_health;
	}

	public String getCsb_2_remarks() {
		return csb_2_remarks;
	}

	public void setCsb_2_remarks(String csb_2_remarks) {
		this.csb_2_remarks = csb_2_remarks;
	}

	public String getMt5_old_crane_health() {
		return mt5_old_crane_health;
	}

	public void setMt5_old_crane_health(String mt5_old_crane_health) {
		this.mt5_old_crane_health = mt5_old_crane_health;
	}

	public String getMt5_old_remarks() {
		return mt5_old_remarks;
	}

	public void setMt5_old_remarks(String mt5_old_remarks) {
		this.mt5_old_remarks = mt5_old_remarks;
	}

	public String getMt5_new_crane_health() {
		return mt5_new_crane_health;
	}

	public void setMt5_new_crane_health(String mt5_new_crane_health) {
		this.mt5_new_crane_health = mt5_new_crane_health;
	}

	public String getMt5_new_remarks() {
		return mt5_new_remarks;
	}

	public void setMt5_new_remarks(String mt5_new_remarks) {
		this.mt5_new_remarks = mt5_new_remarks;
	}

	public String getCpl_exit_25mt_crane_health() {
		return cpl_exit_25mt_crane_health;
	}

	public void setCpl_exit_25mt_crane_health(String cpl_exit_25mt_crane_health) {
		this.cpl_exit_25mt_crane_health = cpl_exit_25mt_crane_health;
	}

	public String getCpl_exit_25mt_remarks() {
		return cpl_exit_25mt_remarks;
	}

	public void setCpl_exit_25mt_remarks(String cpl_exit_25mt_remarks) {
		this.cpl_exit_25mt_remarks = cpl_exit_25mt_remarks;
	}

	public String getInterlock_status_crane_health() {
		return interlock_status_crane_health;
	}

	public void setInterlock_status_crane_health(String interlock_status_crane_health) {
		this.interlock_status_crane_health = interlock_status_crane_health;
	}

	public String getInterlock_status_remarks() {
		return interlock_status_remarks;
	}

	public void setInterlock_status_remarks(String interlock_status_remarks) {
		this.interlock_status_remarks = interlock_status_remarks;
	}

	


}
