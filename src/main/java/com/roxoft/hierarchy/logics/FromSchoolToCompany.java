package com.roxoft.hierarchy.logics;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.institutions.University;
import com.roxoft.hierarchy.models.projects.Project;

public class FromSchoolToCompany {
	
	public void showTheWayFromSchoolToCompany(ArrayList<Company> companies, 
			ArrayList<University> universities){
		System.out.println("\nFrom school to company\n");
		for (Company company : companies){
			System.out.println("Company: \""+company.getName()+"\";");
			System.out.println("Current projects:");
			for(Project project : company.getProjects()){
				System.out.println("    Project: \""+project.getName()+"\";");
				System.out.println("        Current vacancies:");
				for (String vacancy : project.getSpecialities()){
					System.out.println("            Vacancy: \""+vacancy+"\";");
					System.out.println("                Universities:");
					for (University university : universities)
						for (String speciality : university.getSpecialities())
							if (speciality.equals(vacancy))
								System.out.println("                "+university.getName());
				}
			}
		}
	}

}
