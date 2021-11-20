package com.example.covid19apps.API;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Response")
	private List<CovidDataAPI> response;

	public List<CovidDataAPI> getResponse(){
		return response;
	}
}