package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Student;

public interface StudentMapper {

	ArrayList<Student> getAllData();
	
	ArrayList<Student> getStudentsByUniversityId(int universityId);
	
	ArrayList<Student> getAllDataWithUniversitiesNames();
	
}
