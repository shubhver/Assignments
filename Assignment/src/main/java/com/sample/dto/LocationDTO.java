package com.sample.dto;

public class LocationDTO {

	private String cityName;
	private String cityAbbr;
	private String countryName;
	private String countryAbbr;
	private String region;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityAbbr() {
		return cityAbbr;
	}
	public void setCityAbbr(String cityAbbr) {
		this.cityAbbr = cityAbbr;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryAbbr() {
		return countryAbbr;
	}
	public void setCountryAbbr(String countryAbbr) {
		this.countryAbbr = countryAbbr;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "LocationDTO [cityName=" + cityName + ", cityAbbr=" + cityAbbr + ", countryName=" + countryName
				+ ", countryAbbr=" + countryAbbr + ", region=" + region + "]";
	}
	
}