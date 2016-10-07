package com.roxoft.hierarchy.dao.mybatis.mappers;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.School;

public interface SchoolMapper {
	
	ArrayList<School> getAllData();
	
	School getSchoolById(int schoolId);
	
	School getPureSchoolById(int schoolId);

}
