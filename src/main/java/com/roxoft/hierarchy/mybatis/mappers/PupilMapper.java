package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;
import com.roxoft.hierarchy.models.human.Pupil;

public interface PupilMapper {
	
	@Select("SELECT name, surname FROM pupils")
	ArrayList<Pupil> getAllData();
	
	ArrayList<Pupil> getPupulsBySchoolId(int schoolId);
	
	ArrayList<Pupil> getAllDataWithSchoolsNames();
	
}
