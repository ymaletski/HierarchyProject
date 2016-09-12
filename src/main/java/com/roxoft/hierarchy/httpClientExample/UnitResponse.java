package com.roxoft.hierarchy.httpClientExample;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitResponse {

	@JsonProperty("RestResponse")
	private UnitListOfCountries unitListOfCountries;

	public UnitListOfCountries getUnitListOfCountries() {
		return unitListOfCountries;
	}
	
}
