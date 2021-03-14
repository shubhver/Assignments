package com.sample.dto;

public class IndiaPhoneDTO {
	
	private String phoneNo;
	private String city;
	private String state;
	private String country;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "IndiaPhoneDTO [phoneNo=" + phoneNo + ", city=" + city + ", state=" + state + ", country=" + country
				+ "]";
	}
	
}
