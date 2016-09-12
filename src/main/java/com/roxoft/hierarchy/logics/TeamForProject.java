package com.roxoft.hierarchy.logics;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;

public class TeamForProject {
	
	public void SelectTeamForProject(ArrayList<Company> companies, 
			ArrayList<Student> students){
		int numberOfStudents = 0;
		boolean availabilityOfProject = false;
		System.out.println("\nTeam for project\n");
		for (Company company : companies){
			System.out.println("Company: \""+company.getName()+"\";");
			System.out.println("Current projects:");
			for(Project project : company.getProjects()){
				availabilityOfProject = true;
				System.out.println("    Project: \""+project.getName()+"\";");
				System.out.println("        Current vacancies:");
				for (String vacancy : project.getSpecialities()){
					System.out.println("        Vacancy: \""+vacancy+"\";");
					System.out.println("            Students:");
					for (Student student : students)
						if (student.getSpeciality().equals(vacancy)){
							System.out.println("            "+student.getName()+" "+student.getSurname());
							numberOfStudents++;
						}
					System.out.println("            Number of students for this vacancy: "+
						numberOfStudents+".");
					if (numberOfStudents == 0)
						availabilityOfProject = false;
					numberOfStudents = 0;
				}
				if (availabilityOfProject)
					System.out.println ("    This project is avalible for company.");
				else
					System.out.println ("    This project is not avalible for company.");
			}
		}
		
	}

}
