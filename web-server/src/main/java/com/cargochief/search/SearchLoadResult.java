package com.cargochief.search;

public class SearchLoadResult {

	private String carrierName = null;
	private String carrierMc = null;
	private String hqCity = null;
	private String hqState = null;
	private String originCity = null;
	private String originState = null;
	private String destCity = null;
	private String destState = null;
	private int fleetSize = 0;
	private int fleetAge = 0;
	private double listingAge = 0;
	private double rank = 0;
	private String dho = null;
	private double dhoMiles = 0;
	private String destinationToHqDistance = null;
	private double destinationToHqDistanceMiles = 0;

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

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierMc() {
		return carrierMc;
	}

	public void setCarrierMc(String carrierMc) {
		this.carrierMc = carrierMc;
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

	public int getFleetSize() {
		return fleetSize;
	}

	public void setFleetSize(int fleetSize) {
		this.fleetSize = fleetSize;
	}

	public double getFleetAge() {
		return fleetAge;
	}

	public void setFleetAge(int fleetAge) {
		this.fleetAge = fleetAge;
	}

	public double getListingAge() {
		return listingAge;
	}

	public void setListingAge(double listingAge) {
		this.listingAge = listingAge;
	}

	public String getFullOrigin() {
		return getOriginCity() + "," + getOriginState();
	}

	public String getFullHq() {
		return getHqCity() + "," + getHqState();
	}

	public String getFullDestination() {
		return getDestCity() + "," + getDestState();
	}

	public String getDho() {
		return dho;
	}

	public void setDho(String dho) {
		this.dho = dho;
	}

	public String getDestinationToHqDistance() {
		return destinationToHqDistance;
	}

	public void setDestinationToHqDistance(String destinationToHqDistance) {
		this.destinationToHqDistance = destinationToHqDistance;
	}

	public double getDhoMiles() {
		return dhoMiles;
	}

	public void setDhoMiles(double dhoMiles) {
		this.dhoMiles = dhoMiles;
	}

	public double getDestinationToHqDistanceMiles() {
		return destinationToHqDistanceMiles;
	}

	public void setDestinationToHqDistanceMiles(double destinationToHqDistanceMiles) {
		this.destinationToHqDistanceMiles = destinationToHqDistanceMiles;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	
}
