package com.roxoft.hierarchy.models.projects;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.institutions.Company;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "project", propOrder = {"specialities", "company"})
public class Project {
	
	@XmlAttribute(required = true)
	private String name;
	
	@XmlElement(name="speciality", required = true)
	@XmlElementWrapper (name = "specialities", required = true)
	private ArrayList<String> specialities;
	
	private Company company;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getSpecialities() {
		return specialities;
	}
	public void setSpecialities(ArrayList<String> specialities) {
		this.specialities = specialities;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
		
}
