package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.projects.Project;

public interface ProjectMapper {
	
	ArrayList<Project> getAllData();
	
	Project getProjectByCompanyId(int companyId);
	
	ArrayList<Project> getAllDataWithCompaniesNames();

}
