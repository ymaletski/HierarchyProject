package com.roxoft.hierarchy.dao.mybatis.mappers;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.University;

public interface UniversityMapper {
	
	ArrayList<University> getAllData();
	
	University getUniversityById(int universityId);
	
	University getPureUniversityById(int universityId);

}
