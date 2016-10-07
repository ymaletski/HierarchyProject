package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.projects.Project;

public interface IDAOProject {
		
	void record(Project project, int companyId);
	void recordProjectsAndSpecialities(int projectId, int specialityId);
	void deleteByName(Project project);
	void deleteById(int id);
	int findIdByName(Project project);
	Project findById(int id);
	int findCompanyIdByName(Project project);
	ArrayList<Project> getAllData();
	ArrayList<Project> findAllByCompanyId(int companyId);
	ArrayList<Integer> findSpecialitiesById(int id);

}
