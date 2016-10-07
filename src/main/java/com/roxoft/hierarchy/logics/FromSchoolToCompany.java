package com.roxoft.hierarchy.logics;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.institutions.University;
import com.roxoft.hierarchy.models.projects.Project;

public class FromSchoolToCompany {
	
	private static final Logger LOGGER = LogManager.getLogger(FromSchoolToCompany.class);
	
	public void showTheWayFromSchoolToCompany(ArrayList<Company> companies, 
			ArrayList<University> universities){
		LOGGER.info("\nFrom school to company\n");
		for (Company company : companies){
			LOGGER.info("Company: \""+company.getName()+"\";");
			LOGGER.info("Current projects:");
			for(Project project : company.getProjects()){
				LOGGER.info("    Project: \""+project.getName()+"\";");
				LOGGER.info("        Current vacancies:");
				for (String vacancy : project.getSpecialities()){
					LOGGER.info("            Vacancy: \""+vacancy+"\";");
					LOGGER.info("                Universities:");
					for (University university : universities)
						for (String speciality : university.getSpecialities())
							if (speciality.equals(vacancy))
								LOGGER.info("                "+university.getName());
				}
			}
		}
	}

}
