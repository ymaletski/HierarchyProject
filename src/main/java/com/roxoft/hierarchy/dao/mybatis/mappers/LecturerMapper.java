package com.roxoft.hierarchy.dao.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.roxoft.hierarchy.models.human.Lecturer;

public interface LecturerMapper {
	
	@Select("SELECT name, surname FROM lecturers")
	ArrayList<Lecturer> getAllData();
	
	ArrayList<Lecturer> getLecturersByUniversityId(int universityId);
	
	ArrayList<Lecturer> getAllDataWithUniversitiesNames();

}
