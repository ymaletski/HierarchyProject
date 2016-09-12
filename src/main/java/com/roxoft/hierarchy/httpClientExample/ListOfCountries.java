package com.roxoft.hierarchy.httpClientExample;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListOfCountries {
	
	private ArrayList<String> messages = new ArrayList<String>();
	
	@JsonProperty("result")
	private ArrayList<Country> countries = new ArrayList<Country>();

	public ArrayList<String> getMessages() {
		return messages;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	public void print(){
		
		for (Country country : countries){
			System.out.println("Country : "+ country.getName());
			System.out.println("Alpha3 : "+ country.getAlpha3_code());
			System.out.println("Alpha2 : "+ country.getAlpha2_code());
		}
		
	}

}
