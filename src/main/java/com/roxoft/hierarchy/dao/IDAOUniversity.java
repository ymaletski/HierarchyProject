package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.University;

public interface IDAOUniversity {
		
	void record(University university, int addressId);
	void recordUniversitiesAndSpecialities(int universityId, int specialityId);
	void deleteByName(University university);
	void deleteById(int id);
	int findAddressIdByName(University university);
	int findIdByName(University university);
	University findById(int id);
	ArrayList<University> getAllData();
	ArrayList<Integer> findSpecialitiesIdsById(int id);

}
