package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

public interface IDAOSpeciality {
		
	void record(String speciality);
	
	void deleteByName(String speciality);
	void deleteById(int id);
	
	String findById(int id);
	
	int findIdByName(String speciality);
	
	ArrayList<String> getAllData();

}
