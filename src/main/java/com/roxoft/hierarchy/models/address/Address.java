package com.roxoft.hierarchy.models.address;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {"street", "house", "postcode", "city"})
public class Address {
	
	@XmlElement(name = "postcode", required = true)
	private String postcode;
	
	@XmlElement(name = "city", required = true)
	private String city;
	
	@XmlElement(name = "street", required = true)
	private String street;
	
	@XmlElement(name = "house", required = true)
	private String house;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
	
}
