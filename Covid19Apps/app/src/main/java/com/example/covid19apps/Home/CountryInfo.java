package com.example.covid19apps.Home;

import com.google.gson.annotations.SerializedName;

public class CountryInfo{

	@SerializedName("flag")
	private String flag;

	@SerializedName("_id")
	private int id;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private double lat;

	@SerializedName("long")
	private double jsonMemberLong;

	@SerializedName("iso3")
	private String iso3;

	public String getFlag(){
		return flag;
	}

	public int getId(){
		return id;
	}

	public String getIso2(){
		return iso2;
	}

	public double getLat(){
		return lat;
	}

	public double getJsonMemberLong(){
		return jsonMemberLong;
	}

	public String getIso3(){
		return iso3;
	}
}