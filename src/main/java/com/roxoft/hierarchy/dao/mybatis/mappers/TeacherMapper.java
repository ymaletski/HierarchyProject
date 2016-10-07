package com.roxoft.hierarchy.dao.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.roxoft.hierarchy.models.human.Teacher;

public interface TeacherMapper {
	
	@Select("SELECT name, surname FROM teachers")
	ArrayList<Teacher> getAllData();
	
	ArrayList<Teacher> getTeachersBySchoolId(int schoolId);
	
	ArrayList<Teacher> getAllDataWithSchoolsNames();

}
