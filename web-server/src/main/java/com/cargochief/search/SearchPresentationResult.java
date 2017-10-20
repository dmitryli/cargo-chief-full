package com.cargochief.search;

public class SearchPresentationResult {

	private String carrierName = null;

	private String carrierMC = null;

	private String dho = null;

	private String truckOrigin = null;

	private int fleetSize = 0;

	private double fleetAge = 0;

	private double listingAge = 0;

	private int matchScore = 0;

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierMC() {
		return carrierMC;
	}

	public void setCarrierMC(String carrierMC) {
		this.carrierMC = carrierMC;
	}

	public String getDho() {
		return dho;
	}

	public void setDho(String dho) {
		this.dho = dho;
	}

	public String getTruckOrigin() {
		return truckOrigin;
	}

	public void setTruckOrigin(String truckOrigin) {
		this.truckOrigin = truckOrigin;
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

	public void setFleetAge(double fleetAge) {
		this.fleetAge = fleetAge;
	}

	public double getListingAge() {
		return listingAge;
	}

	public void setListingAge(double listingAge) {
		this.listingAge = listingAge;
	}

	public int getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

}
