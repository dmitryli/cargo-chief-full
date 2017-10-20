package com.cargochief.carrier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Dima:
 * This class represents CC_CARRIER_INFO table. Each row is an instance of this class and represents one carrier
 */

@Entity
@Table(name = "CC_CARRIER_INFO")
public class CarrierInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private String businessName = null;
	private String firmMc = null;
	private String hqCity = null;
	private String hqState = null;
	private Long hqZip = null;
	private Long fleetSize = null;
	private Long fleetAge = null;
	
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getFirmMc() {
		return firmMc;
	}
	public void setFirmMc(String firmMc) {
		this.firmMc = firmMc;
	}
	public String getHqCity() {
		return hqCity;
	}
	public void setHqCity(String hqCity) {
		this.hqCity = hqCity;
	}
	public String getHqState() {
		return hqState;
	}
	public void setHqState(String hqState) {
		this.hqState = hqState;
	}
	public Long getHqZip() {
		return hqZip;
	}
	public void setHqZip(Long hqZip) {
		this.hqZip = hqZip;
	}
	public Long getFleetSize() {
		return fleetSize;
	}
	public void setFleetSize(Long fleetSize) {
		this.fleetSize = fleetSize;
	}
	public Long getFleetAge() {
		return fleetAge;
	}
	public void setFleetAge(Long fleetAge) {
		this.fleetAge = fleetAge;
	}
	
	
}
