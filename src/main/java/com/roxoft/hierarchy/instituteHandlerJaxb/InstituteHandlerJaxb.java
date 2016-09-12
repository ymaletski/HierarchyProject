package com.roxoft.hierarchy.instituteHandlerJaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.institutions.School;
import com.roxoft.hierarchy.models.institutions.University;

@XmlRootElement(name = "institutes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "institutes", propOrder = {"schools", "universities", "companies"})
public class InstituteHandlerJaxb {
	
	@XmlElement(name = "school", required = true)
	@XmlElementWrapper (name = "schools", required = true)
	private ArrayList<School> schools = new ArrayList<School>();
	
	@XmlElement(name = "university", required = true)
	@XmlElementWrapper (name = "universities", required = true)
	private ArrayList<University> universities = new ArrayList<University>();
	
	@XmlElement(name = "company", required = true)
	@XmlElementWrapper (name = "companies", required = true)
	private ArrayList<Company> companies = new ArrayList<Company>();

	public ArrayList<School> getSchools() {
		return schools;
	}

	public ArrayList<University> getUniversities() {
		return universities;
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}
	
}
