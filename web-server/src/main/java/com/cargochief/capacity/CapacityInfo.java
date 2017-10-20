package com.cargochief.capacity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Dima:
 * This class represents CC_CAPACITY_INFO table. Each row is an instance of this class and represents one row from the available capacity
 */
@Entity
@Table(name = "CC_CAPACITY_INFO")
public class CapacityInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private String businessName = null;
	private String firmMc = null;
	private String originCity = null;
	private String originState = null;
	private Long originZip = null;
	private String destCity = null;
	private String destState = null;
	private Long destZip = null;
	private Long minuteAge = null;
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
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getOriginState() {
		return originState;
	}
	public void setOriginState(String originState) {
		this.originState = originState;
	}
	public Long getOriginZip() {
		return originZip;
	}
	public void setOriginZip(Long originZip) {
		this.originZip = originZip;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public String getDestState() {
		return destState;
	}
	public void setDestState(String destState) {
		this.destState = destState;
	}
	public Long getDestZip() {
		return destZip;
	}
	public void setDestZip(Long destZip) {
		this.destZip = destZip;
	}
	public Long getMinuteAge() {
		return minuteAge;
	}
	public void setMinuteAge(Long minuteAge) {
		this.minuteAge = minuteAge;
	}
	
	

}
