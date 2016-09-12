package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Teacher;

public interface IDAOTeacher {
		
	void record(Teacher teacher, int schoolId);
	
	void deleteByName(Teacher teacher);
	void deleteById(int id);
	void deleteBySchoolId(int schoolId);
	
	Teacher findById(int id);
	ArrayList<Teacher> findAllBySchoolId(int schoolId);
	
	int findSchoolIdByName(Teacher teacher);
	
	ArrayList<Teacher> getAllData();
	
}
