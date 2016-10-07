package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Student;

public interface IDAOStudent {
		
	void record(Student student, int universitylId, int specialityId);
	void deleteByName(Student student);
	void deleteById(int id);
	void deleteByUniversityId(int universityId);
	Student findById(int id);
	ArrayList<Student> findAllByUniversityId(int universityId);
	int findUniversityIdByName(Student student);
	int findSpecialityIdByName(Student student);
	ArrayList<Student> getAllData();

}
