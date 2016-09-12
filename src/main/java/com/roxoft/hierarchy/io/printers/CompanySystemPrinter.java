package com.roxoft.hierarchy.io.printers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;

public class CompanySystemPrinter {
	
	private static final Logger companySystemLogger = LogManager.getLogger(CompanySystemPrinter.class);
	
	public void purePrint(ArrayList<Project> projects, ArrayList<Company> companies){
		
		/*
		System.out.println("\nCompany System\n");
		System.out.println("Number of projects: "+projects.size()+".");
		for (Project project : projects)
			System.out.println("Project "+(projects.indexOf(project)+1)+": "+
					project.getName()+", company: "+
					project.getCompany().getName()+", team: "+
					project.getSpecialities()+".");
		System.out.println("Number of companies: "+companies.size()+".");
		for (Company company : companies)
			System.out.println("Company "+(companies.indexOf(company)+1)+": "+
					company.getName()+"; address: "+company.getAddress().getStreet()+", "+
					company.getAddress().getHouse()+", "+
					company.getAddress().getPostcode()+", "+
					company.getAddress().getCity()+".");
		*/
		
		companySystemLogger.info("\nCompany System\n");
		companySystemLogger.info("Number of projects: "+projects.size()+".");
		for (Project project : projects)
			companySystemLogger.info("Project "+(projects.indexOf(project)+1)+": "+
					project.getName()+", company: "+
					project.getCompany().getName()+", team: "+
					project.getSpecialities()+".");
		companySystemLogger.info("Number of companies: "+companies.size()+".");
		for (Company company : companies)
			companySystemLogger.info("Company "+(companies.indexOf(company)+1)+": "+
					company.getName()+"; address: "+company.getAddress().getStreet()+", "+
					company.getAddress().getHouse()+", "+
					company.getAddress().getPostcode()+", "+
					company.getAddress().getCity()+".");
		
	}
	
	public void printCompanySystem(ArrayList<Company> companies){
		
		/*
		int numberOfProjects = 0;
		
		System.out.println("\nCompany System");
		
		for (Company company : companies){
			System.out.println("\nCompany: "+company.getName()+".");
			System.out.println("Address: "+company.getAddress().getStreet()+", "+
					company.getAddress().getHouse()+", "+
					company.getAddress().getPostcode()+", "+
					company.getAddress().getCity()+".");
			for (Project project : company.getProjects()){
				System.out.println("Project: "+project.getName()+
						", team: "+project.getSpecialities()+".");
				numberOfProjects++;
			}
			System.out.println("Company "+company.getName()+": number of projects = "+
					numberOfProjects+".");
			numberOfProjects = 0;
		}
		*/
		
		int numberOfProjects = 0;
		
		companySystemLogger.info("\nCompany System");
		
		for (Company company : companies){
			companySystemLogger.info("\nCompany: "+company.getName()+".");
			companySystemLogger.info("Address: "+company.getAddress().getStreet()+", "+
					company.getAddress().getHouse()+", "+
					company.getAddress().getPostcode()+", "+
					company.getAddress().getCity()+".");
			for (Project project : company.getProjects()){
				companySystemLogger.info("Project: "+project.getName()+
						", team: "+project.getSpecialities()+".");
				numberOfProjects++;
			}
			companySystemLogger.info("Company "+company.getName()+": number of projects = "+
					numberOfProjects+".");
			numberOfProjects = 0;
		}
		
	}

}
