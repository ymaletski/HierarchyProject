package com.roxoft.hierarchy.instituteHandlerJackson;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.institutions.School;
import com.roxoft.hierarchy.models.institutions.University;

public class InstituteHandlerJackson {

	private ArrayList<School> schools = new ArrayList<School>();
	private ArrayList<University> universities = new ArrayList<University>();
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
