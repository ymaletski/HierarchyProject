package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Lecturer;

public interface IDAOLecturer {
		
	void record(Lecturer lecturer, int universitylId);
	void deleteByName(Lecturer lecturer);
	void deleteById(int id);
	void deleteByUniversityId(int universityId);
	Lecturer findById(int id);
	ArrayList<Lecturer> findAllByUniversityId(int universityId);
	int findUniversityIdByName(Lecturer lecturer);
	ArrayList<Lecturer> getAllData();
	
}
