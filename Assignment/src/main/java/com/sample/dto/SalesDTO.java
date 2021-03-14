package com.sample.dto;

public class SalesDTO {

	private String salesPersonID;
	private String region;
	private String country;
	private String city;
	private String salesPersonLevelRegionWise;
	private String salesAmtBaseCurr;
	private String monthYear;
	public String getSalesPersonID() {
		return salesPersonID;
	}
	public void setSalesPersonID(String salesPersonID) {
		this.salesPersonID = salesPersonID;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSalesPersonLevelRegionWise() {
		return salesPersonLevelRegionWise;
	}
	public void setSalesPersonLevelRegionWise(String salesPersonLevelRegionWise) {
		this.salesPersonLevelRegionWise = salesPersonLevelRegionWise;
	}
	public String getSalesAmtBaseCurr() {
		return salesAmtBaseCurr;
	}
	public void setSalesAmtBaseCurr(String salesAmtBaseCurr) {
		this.salesAmtBaseCurr = salesAmtBaseCurr;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	@Override
	public String toString() {
		return "SalesDTO [salesPersonID=" + salesPersonID + ", region=" + region + ", country=" + country + ", city="
				+ city + ", salesPersonLevelRegionWise=" + salesPersonLevelRegionWise + ", salesAmtBaseCurr="
				+ salesAmtBaseCurr + ", monthYear=" + monthYear + "]";
	}
	
	
}
