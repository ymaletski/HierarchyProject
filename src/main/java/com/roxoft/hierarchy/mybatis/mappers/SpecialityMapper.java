package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

public interface SpecialityMapper {
	
	@Select("SELECT name FROM specialities")
	ArrayList<String> getAllData();
	
	String getSpecialityById(int specialitylId);
	
	ArrayList<String> getSpecialitiesByUniversityId(int universitylId);
	ArrayList<String> getSpecialitiesByProjectId(int projectlId);

}
