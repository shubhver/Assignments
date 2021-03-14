package com.sample.dto;

public class SalesIncentiveDTO {

	private String region;
	private String salesPersonLevelRegionWise;
	private String salesIncentiveFormula;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSalesPersonLevelRegionWise() {
		return salesPersonLevelRegionWise;
	}
	public void setSalesPersonLevelRegionWise(String salesPersonLevelRegionWise) {
		this.salesPersonLevelRegionWise = salesPersonLevelRegionWise;
	}
	public String getSalesIncentiveFormula() {
		return salesIncentiveFormula;
	}
	public void setSalesIncentiveFormula(String salesIncentiveFormula) {
		this.salesIncentiveFormula = salesIncentiveFormula;
	}
	@Override
	public String toString() {
		return "SalesIncentiveDTO [region=" + region + ", salesPersonLevelRegionWise=" + salesPersonLevelRegionWise
				+ ", salesIncentiveFormula=" + salesIncentiveFormula + "]";
	}
	
}
