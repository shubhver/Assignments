package com.sample.dto;

public class CityCodeDTO implements Comparable<CityCodeDTO> {

	private String city;
	private String state;
	private String country;
	private int cityCode;
	private int records;

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

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		CityCodeDTO dto = (CityCodeDTO) obj;

		return ((dto.cityCode == this.cityCode) && dto.city.equals(this.city));
	}

	@Override
	public String toString() {
		return "CityCodeDTO [city=" + city + ", state=" + state + ", country=" + country + ", cityCode=" + cityCode
				+ ", records=" + records + "]";
	}

	@Override
	public int compareTo(CityCodeDTO o) {
		if (this.records == o.records)
			return 0;
		else if (this.records < o.records)
			return 1;
		else
			return -1;
	}

}
