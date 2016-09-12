package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Pupil;

public interface IDAOPupil {
		
	void record(Pupil pupil, int schoolId);
	
	void deleteByName(Pupil pupil);
	void deleteById(int id);
	void deleteBySchoolId(int schoolId);
	
	Pupil findById(int id);
	ArrayList<Pupil> findAllBySchoolId(int schoolId);
	
	int findSchoolIdByName(Pupil pupil);
	
	ArrayList<Pupil> getAllData();

}
