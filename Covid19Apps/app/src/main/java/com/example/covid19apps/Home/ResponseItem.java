package com.example.covid19apps.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ResponseItem{

	@SerializedName("continent")
	@Expose
	private String continent;

	@SerializedName("country")
	@Expose
	private String country;

	@SerializedName("recoveredPerOneMillion")
	@Expose
	private double recoveredPerOneMillion;

	@SerializedName("cases")
	@Expose
	private int cases;

	@SerializedName("critical")
	@Expose
	private int critical;

	@SerializedName("oneCasePerPeople")
	@Expose
	private int oneCasePerPeople;

	@SerializedName("active")
	@Expose
	private int active;

	@SerializedName("testsPerOneMillion")
	@Expose
	private int testsPerOneMillion;

	@SerializedName("population")
	@Expose
	private int population;

	@SerializedName("oneDeathPerPeople")
	@Expose
	private int oneDeathPerPeople;

	@SerializedName("recovered")
	@Expose
	private int recovered;

	@SerializedName("oneTestPerPeople")
	@Expose
	private int oneTestPerPeople;

	@SerializedName("tests")
	@Expose
	private int tests;

	@SerializedName("criticalPerOneMillion")
	@Expose
	private double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	@Expose
	private int deathsPerOneMillion;

	@SerializedName("todayRecovered")
	@Expose
	private int todayRecovered;

	@SerializedName("casesPerOneMillion")
	@Expose
	private int casesPerOneMillion;

	@SerializedName("countryInfo")
	@Expose
	private CountryInfo countryInfo;

	@SerializedName("updated")
	@Expose
	private long updated;

	@SerializedName("deaths")
	@Expose
	private int deaths;

	@SerializedName("activePerOneMillion")
	@Expose
	private double activePerOneMillion;

	@SerializedName("todayCases")
	@Expose
	private int todayCases;

	@SerializedName("todayDeaths")
	@Expose
	private int todayDeaths;

	public String getContinent(){
		return continent;
	}

	public String getCountry(){
		return country;
	}

	public double getRecoveredPerOneMillion(){
		return recoveredPerOneMillion;
	}

	public int getCases(){
		return cases;
	}

	public int getCritical(){
		return critical;
	}

	public int getOneCasePerPeople(){
		return oneCasePerPeople;
	}

	public int getActive(){
		return active;
	}

	public int getTestsPerOneMillion(){
		return testsPerOneMillion;
	}

	public int getPopulation(){
		return population;
	}

	public int getOneDeathPerPeople(){
		return oneDeathPerPeople;
	}

	public int getRecovered(){
		return recovered;
	}

	public int getOneTestPerPeople(){
		return oneTestPerPeople;
	}

	public int getTests(){
		return tests;
	}

	public double getCriticalPerOneMillion(){
		return criticalPerOneMillion;
	}

	public int getDeathsPerOneMillion(){
		return deathsPerOneMillion;
	}

	public int getTodayRecovered(){
		return todayRecovered;
	}

	public int getCasesPerOneMillion(){
		return casesPerOneMillion;
	}

	public CountryInfo getCountryInfo(){
		return countryInfo;
	}

	public long getUpdated(){
		return updated;
	}

	public int getDeaths(){
		return deaths;
	}

	public double getActivePerOneMillion(){
		return activePerOneMillion;
	}

	public int getTodayCases(){
		return todayCases;
	}

	public int getTodayDeaths(){
		return todayDeaths;
	}
}