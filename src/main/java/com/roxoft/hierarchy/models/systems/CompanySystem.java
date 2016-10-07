package com.roxoft.hierarchy.models.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.models.address.Address;
import com.roxoft.hierarchy.data.Companies;
import com.roxoft.hierarchy.data.Projects;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;
import com.roxoft.hierarchy.randomFillers.RandomSpecialitiesFiller;

public class CompanySystem {

	private ArrayList<Company> companies = new ArrayList<Company>();
	private ArrayList<Project> projects = new ArrayList<Project>();

	public void fillCompanySystem(int numberOfCompanies, int numberOfProjects){
		clearAllData();
		fillCompaniesNames(numberOfCompanies);
		fillCompaniesAddresses();
		fillProjectsNames(numberOfProjects);
		fillProjectsSpecialities();
		fillCompaniesWithProjects();	
	}	
	
	private void fillCompaniesNames(int numberOfCompanies){
		Companies dataCompaniesNames = new Companies();
		ArrayList<String> companiesNames = new ArrayList<String>(Arrays.asList(
				dataCompaniesNames.getCompanies()));
		for (String str : companiesNames){
			Company company = new Company();
			company.setName(str);				
			companies.add(company);
		}			
	}
	
	private void fillCompaniesAddresses(){
		for (Company company : companies){
			Address address = new Address();
			switch (company.getName()){
				case "MAPID":{
					address.setPostcode("220036");
					address.setCity("Minsk");
					address.setStreet("R.Luxemburg st.");
					address.setHouse("205");
					company.setAddress(address);
					break;
				}
				case "MTZ":{
					address.setPostcode("220009");
					address.setCity("Minsk");
					address.setStreet("Dolgobrodskaya st.");
					address.setHouse("29");
					company.setAddress(address);
					break;
				}
				case "BELAVIA":{
					address.setPostcode("220004");
					address.setCity("Minsk");
					address.setStreet("Nemiga st.");
					address.setHouse("14A");
					company.setAddress(address);
					break;
				}
			}
		}
	}
	
	private void fillProjectsNames(int numberOfProjects){
		Projects dataProjectsNames = new Projects();
		ArrayList<String> projectsNames = new ArrayList<String>(Arrays.asList(
				dataProjectsNames.getProjects()));
		for (String str : projectsNames){
			Project project = new Project();
			project.setName(str);				
			projects.add(project);
		}		
	}
	
	private void fillProjectsSpecialities(){
		RandomSpecialitiesFiller rsf = new RandomSpecialitiesFiller();		
		Random rand = new Random();						
		for (Project project : projects)
			project.setSpecialities(rsf.getSpecialities(rand.nextInt(3)+2));	
	}
	
	private void fillCompaniesWithProjects(){
		Random rand = new Random();
		int random = 0, size = companies.size();
				
		for (Project project : projects){
			random = rand.nextInt(size);
			project.setCompany(companies.get(random));
		}
		for (Company company : companies){
			ArrayList<Project> projectsOfTheSameCompany = new ArrayList<Project>();
			for (Project project : projects){
				if (project.getCompany().getName().equals(company.getName()))
					projectsOfTheSameCompany.add(project);
			}
			company.setProjects(projectsOfTheSameCompany);
		}
	}
	
	private void clearAllData(){
		if (!(companies.isEmpty()))
			companies.clear();
		if (!(projects.isEmpty()))
			projects.clear();		
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	
}
