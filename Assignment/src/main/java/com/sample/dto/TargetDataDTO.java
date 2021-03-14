package com.sample.dto;

public class TargetDataDTO {

	private String salesPersonID;
	private String region;
	private String country;
	private String city;
	private String salesAmtBaseCurr;
	private String salesAmtUSD;
	private String globalRank;
	private String regionRank;
	private String countryRank;
	private String salesIncentive;
	private String month;
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
	public String getSalesAmtBaseCurr() {
		return salesAmtBaseCurr;
	}
	public void setSalesAmtBaseCurr(String salesAmtBaseCurr) {
		this.salesAmtBaseCurr = salesAmtBaseCurr;
	}
	public String getSalesAmtUSD() {
		return salesAmtUSD;
	}
	public void setSalesAmtUSD(String salesAmtUSD) {
		this.salesAmtUSD = salesAmtUSD;
	}
	public String getGlobalRank() {
		return globalRank;
	}
	public void setGlobalRank(String globalRank) {
		this.globalRank = globalRank;
	}
	public String getRegionRank() {
		return regionRank;
	}
	public void setRegionRank(String regionRank) {
		this.regionRank = regionRank;
	}
	public String getCountryRank() {
		return countryRank;
	}
	public void setCountryRank(String countryRank) {
		this.countryRank = countryRank;
	}
	public String getSalesIncentive() {
		return salesIncentive;
	}
	public void setSalesIncentive(String salesIncentive) {
		this.salesIncentive = salesIncentive;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "TargetDataDTO [salesPersonID=" + salesPersonID + ", region=" + region + ", country=" + country
				+ ", city=" + city + ", salesAmtBaseCurr=" + salesAmtBaseCurr + ", salesAmtUSD=" + salesAmtUSD
				+ ", globalRank=" + globalRank + ", regionRank=" + regionRank + ", countryRank=" + countryRank
				+ ", salesIncentive=" + salesIncentive + ", month=" + month + "]";
	}
}
