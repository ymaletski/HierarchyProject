package com.roxoft.hierarchy.services.systems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.systems.AddressDAO;
import com.roxoft.hierarchy.dao.systems.CompanyDAO;
import com.roxoft.hierarchy.dao.systems.ProjectDAO;
import com.roxoft.hierarchy.dao.systems.SpecialityDAO;
import com.roxoft.hierarchy.dbcp.DataSource;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;

public class CompanySystemService {
	
	private static final Logger LOGGER = LogManager.getLogger(CompanySystemService.class);
	
	public void recordCompanySystem(ArrayList<Company> companies, 
			ArrayList<Project> projects){	
		Connection connection = null;
		try {		
			connection = DataSource.getInstance("systems").getConnection();				
			CompanyDAO companyDAO = new CompanyDAO(connection);
			ProjectDAO projectDAO = new ProjectDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			SpecialityDAO specialityDAO = new SpecialityDAO(connection);		
			for (Company company: companies){		
				addressDAO.record(company.getAddress());
				companyDAO.record(company, addressDAO.findId(
						company.getAddress()));	
				for (Project project : company.getProjects()){			
					projectDAO.record(project, companyDAO.findIdByName(company));
					for (String speciality : project.getSpecialities())
						projectDAO.recordProjectsAndSpecialities(projectDAO.findIdByName(project), 
								specialityDAO.findIdByName(speciality));		
				}
			}				
		} catch (SQLException e) {
			LOGGER.error("SQLException in CompanySystemService.recordCompanySystem(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in CompanySystemService.recordCompanySystem(): ", e);
				}
		}
	}
	
	public ArrayList<Company> createCompanySystem(){
		ArrayList<Company> companies = new ArrayList<Company>();
		ArrayList<Project> projects = new ArrayList<Project>();
		int projectId = 0;
		int addressId = 0;		
		Connection connection = null;
		try {	
			connection = DataSource.getInstance("systems").getConnection();		
			CompanyDAO companyDAO = new CompanyDAO(connection);
			ProjectDAO projectDAO = new ProjectDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			SpecialityDAO specialityDAO = new SpecialityDAO(connection);
			companies.addAll(companyDAO.getAllData());
			projects.addAll(projectDAO.getAllData());			
			for (Project project : projects){	
				project.setCompany(companyDAO.findById(projectDAO.
						findCompanyIdByName(project)));
				projectId = projectDAO.findIdByName(project);
				if (projectId != 0){
					ArrayList<String> specialities = new ArrayList<String>();
					for (int specialityId : projectDAO.findSpecialitiesById(projectId)){
						specialities.add(specialityDAO.findById(specialityId));
					}
					project.setSpecialities(specialities);
				}
				projectId = 0;	
			}
			for (Company company : companies){	
				ArrayList<Project> projectsOfTheSameCompany = new ArrayList<Project>();
				for (Project project : projects){
					if (project.getCompany().getName().equals(company.getName()))
						projectsOfTheSameCompany.add(project);
				}
				company.setProjects(projectsOfTheSameCompany);
				addressId = companyDAO.findAddressIdByName(company);
				if (addressId != 0)
					company.setAddress(addressDAO.findById(addressId));
				addressId = 0;	
			}											
		} catch (SQLException e) {
			LOGGER.error("SQLException in CompanySystemService.createCompanySystem(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in CompanySystemService.createCompanySystem(): ", e);
				}
		}
		return companies;
	}
	
}
