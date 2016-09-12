package com.roxoft.hierarchy.httpClientExample;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("RestResponse")
	private ListOfCountries listOfCountries;

	public ListOfCountries getListOfCountries() {
		return listOfCountries;
	}
	
}
