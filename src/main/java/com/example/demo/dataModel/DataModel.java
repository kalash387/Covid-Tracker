package com.example.demo.dataModel;


public class DataModel {

	
	@Override
	public String toString() {
		return "DataModel [state=" + state + ", country=" + country + ", totalCases=" + totalCases + "]";
	}
	private String state;
	private String country;
	private int totalCases;
	private int totalRecovered;
	private int totalDeaths;
	public int getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
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
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	
}
