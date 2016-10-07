package com.roxoft.hierarchy.services.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.data.CompaniesNamesDAO;
import com.roxoft.hierarchy.dao.data.NamesFemaleDAO;
import com.roxoft.hierarchy.dao.data.NamesMaleDAO;
import com.roxoft.hierarchy.dao.data.ProjectsNamesDAO;
import com.roxoft.hierarchy.dao.data.SchoolsNamesDAO;
import com.roxoft.hierarchy.dao.data.SpecialitiesNamesDAO;
import com.roxoft.hierarchy.dao.data.SurnamesFemaleDAO;
import com.roxoft.hierarchy.dao.data.SurnamesMaleDAO;
import com.roxoft.hierarchy.dao.data.UniversitiesNamesDAO;
import com.roxoft.hierarchy.dbcp.DataSource;

public class DataService {
	
	private static final Logger LOGGER = LogManager.getLogger(DataService.class);
	
	public void fillAllData(){	
		Connection connection = null;
		try {		
			connection = DataSource.getInstance("initial_data").getConnection();			
			fillMaleNames(connection);
			fillFemaleNames(connection);
			fillMaleSurnames(connection);
			fillFemaleSurnames(connection);
			fillSchoolsNames(connection);
			fillUniversitiesNames(connection);
			fillCompaniesNames(connection);
			fillSpecialitiesNames(connection);
			fillProjectsNames(connection);		
		} catch (SQLException e) {
			LOGGER.error("SQLException in DataService.fillAllData(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in DataService.fillAllData(): ", e);
				}
		}					
	}
	
	private void fillMaleNames(Connection connection) {		
		NamesMaleDAO nmDAO = new NamesMaleDAO(connection);
		nmDAO.fillTable();
	}
	
	private ArrayList<String> getMaleNames(Connection connection) {
		ArrayList<String> NamesMale = new ArrayList<String>();
		NamesMaleDAO nmDAO = new NamesMaleDAO(connection);
		NamesMale.addAll(nmDAO.getAllData());
		return NamesMale;
	}
	
	private void fillFemaleNames(Connection connection) {			
		NamesFemaleDAO nfDAO = new NamesFemaleDAO(connection);
		nfDAO.fillTable();
	}
	
	private ArrayList<String> getFemaleNames(Connection connection) {
		ArrayList<String> NamesFemale = new ArrayList<String>();
		NamesFemaleDAO nfDAO = new NamesFemaleDAO(connection);
		NamesFemale.addAll(nfDAO.getAllData());
		return NamesFemale;
	}

	private void fillMaleSurnames(Connection connection) {		
		SurnamesMaleDAO smDAO = new SurnamesMaleDAO(connection);
		smDAO.fillTable();	
	}
	
	private ArrayList<String> getMaleSurnames(Connection connection) {
		ArrayList<String> MaleSurnames = new ArrayList<String>();
		SurnamesMaleDAO smDAO = new SurnamesMaleDAO(connection);
		MaleSurnames.addAll(smDAO.getAllData());
		return MaleSurnames;
	}
	
	private void fillFemaleSurnames(Connection connection) {		
		SurnamesFemaleDAO sfDAO = new SurnamesFemaleDAO(connection);
		sfDAO.fillTable();
	}
	
	private ArrayList<String> getFemaleSurnames(Connection connection) {
		ArrayList<String> FemaleSurnames = new ArrayList<String>();
		SurnamesFemaleDAO sfDAO = new SurnamesFemaleDAO(connection);
		FemaleSurnames.addAll(sfDAO.getAllData());
		return FemaleSurnames;
	}
	
	private void fillSchoolsNames(Connection connection) {		
		SchoolsNamesDAO snDAO = new SchoolsNamesDAO(connection);
		snDAO.fillTable();	
	}
	
	private ArrayList<String> getSchoolsNames(Connection connection) {
		ArrayList<String> SchoolsNames = new ArrayList<String>();
		SchoolsNamesDAO snDAO = new SchoolsNamesDAO(connection);
		SchoolsNames.addAll(snDAO.getAllData());
		return SchoolsNames;
	}
	
	private void fillUniversitiesNames(Connection connection) {		
		UniversitiesNamesDAO unDAO = new UniversitiesNamesDAO(connection);
		unDAO.fillTable();	
	}
	
	private ArrayList<String> getUniversitiesNames(Connection connection) {
		ArrayList<String> UniversitiesNames = new ArrayList<String>();
		UniversitiesNamesDAO unDAO = new UniversitiesNamesDAO(connection);
		UniversitiesNames.addAll(unDAO.getAllData());
		return UniversitiesNames;
	}
	
	private void fillCompaniesNames(Connection connection) {		
		CompaniesNamesDAO cnDAO = new CompaniesNamesDAO(connection);
		cnDAO.fillTable();	
	}
	
	private ArrayList<String> getCompaniesNames(Connection connection) {
		ArrayList<String> CompaniesNames = new ArrayList<String>();
		CompaniesNamesDAO cnDAO = new CompaniesNamesDAO(connection);
		CompaniesNames.addAll(cnDAO.getAllData());
		return CompaniesNames;
	}
	
	private void fillSpecialitiesNames(Connection connection) {		
		SpecialitiesNamesDAO snDAO = new SpecialitiesNamesDAO(connection);
		snDAO.fillTable();	
	}
	
	private ArrayList<String> getSpecialitiesNames(Connection connection) {
		ArrayList<String> SpecialitiesNames = new ArrayList<String>();
		SpecialitiesNamesDAO snDAO = new SpecialitiesNamesDAO(connection);
		SpecialitiesNames.addAll(snDAO.getAllData());
		return SpecialitiesNames;
	}
	
	private void fillProjectsNames(Connection connection) {		
		ProjectsNamesDAO pnDAO = new ProjectsNamesDAO(connection);
		pnDAO.fillTable();	
	}
	
	private ArrayList<String> getProjectsNames(Connection connection) {
		ArrayList<String> ProjectsNames = new ArrayList<String>();
		ProjectsNamesDAO pnDAO = new ProjectsNamesDAO(connection);
		ProjectsNames.addAll(pnDAO.getAllData());
		return ProjectsNames;
	}

	public void printData() {
		Connection connection = null;
		try {
			connection = DataSource.getInstance("initial_data").getConnection();
			LOGGER.info("\nAll data from DB 'initial_data'\n");		
			LOGGER.info("Names male:");
			for (String str : getMaleNames(connection))
				LOGGER.info(str + " ");		
			LOGGER.info("Names female");
			for (String str : getFemaleNames(connection))
				LOGGER.info(str + " ");	
			LOGGER.info("Surnames male");
			for (String str : getMaleSurnames(connection))
				LOGGER.info(str + " ");		
			LOGGER.info("Surnames female");
			for (String str : getFemaleSurnames(connection))
				LOGGER.info(str + " ");	
			LOGGER.info("Schools names");
			for (String str : getSchoolsNames(connection))
				LOGGER.info(str + " ");	
			LOGGER.info("Universities names");
			for (String str : getUniversitiesNames(connection))
				LOGGER.info(str + " ");		
			LOGGER.info("Companies names");
			for (String str : getCompaniesNames(connection))
				LOGGER.info(str + " ");		
			LOGGER.info("Specialities names");
			for (String str : getSpecialitiesNames(connection))
				LOGGER.info(str + " ");		
			LOGGER.info("Projects names");
			for (String str : getProjectsNames(connection))
				LOGGER.info(str + " ");	
		} catch (SQLException e) {
			LOGGER.error("SQLException in DataService.printData(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in DataService.printData(): ", e);
				}
		}	
	}
	
}
