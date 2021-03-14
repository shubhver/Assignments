package com.sample.dto;

public class CurrencyConversionDTO {

	private String currPair;
	private String exchRate;
	public String getCurrPair() {
		return currPair;
	}
	public void setCurrPair(String currPair) {
		this.currPair = currPair;
	}
	public String getExchRate() {
		return exchRate;
	}
	public void setExchRate(String exchRate) {
		this.exchRate = exchRate;
	}
	@Override
	public String toString() {
		return "CurrencyConversionDTO [currPair=" + currPair + ", exchRate=" + exchRate + "]";
	}
}
