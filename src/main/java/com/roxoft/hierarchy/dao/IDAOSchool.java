package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.School;

public interface IDAOSchool {
		
	void record(School school, int addressId);
	
	void deleteByName(School school);
	void deleteById(int id);
	
	School findById(int id);
	
	int findAddressIdByName(School school);
	int findIdByName(School school);
	
	ArrayList<School> getAllData();

}
