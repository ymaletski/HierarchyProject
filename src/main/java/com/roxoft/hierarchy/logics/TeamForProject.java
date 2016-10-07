package com.roxoft.hierarchy.logics;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;

public class TeamForProject {
	
	private static final Logger LOGGER = LogManager.getLogger(FromSchoolToCompany.class);
	
	public void SelectTeamForProject(ArrayList<Company> companies, 
			ArrayList<Student> students){
		int numberOfStudents = 0;
		boolean availabilityOfProject = false;
		LOGGER.info("\nTeam for project\n");
		for (Company company : companies){
			LOGGER.info("Company: \""+company.getName()+"\";");
			LOGGER.info("Current projects:");
			for(Project project : company.getProjects()){
				availabilityOfProject = true;
				LOGGER.info("    Project: \""+project.getName()+"\";");
				LOGGER.info("        Current vacancies:");
				for (String vacancy : project.getSpecialities()){
					LOGGER.info("        Vacancy: \""+vacancy+"\";");
					LOGGER.info("            Students:");
					for (Student student : students)
						if (student.getSpeciality().equals(vacancy)){
							LOGGER.info("            "+student.getName()+" "+student.getSurname());
							numberOfStudents++;
						}
					LOGGER.info("            Number of students for this vacancy: "+
						numberOfStudents+".");
					if (numberOfStudents == 0)
						availabilityOfProject = false;
					numberOfStudents = 0;
				}
				if (availabilityOfProject)
					LOGGER.info("    This project is avalible for company.");
				else
					LOGGER.info("    This project is not avalible for company.");
			}
		}
		
	}

}
