package com.roxoft.hierarchy.httpClientExample;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitListOfCountries {

private ArrayList<String> messages = new ArrayList<String>();
	
	@JsonProperty("result")
	private Country country = new Country();

	public ArrayList<String> getMessages() {
		return messages;
	}

	public Country getCountry() {
		return country;
	}
	
	public void print(){	
			System.out.println("Country : "+ country.getName());
			System.out.println("Alpha3 : "+ country.getAlpha3_code());
			System.out.println("Alpha2 : "+ country.getAlpha2_code());
	}
	
}
