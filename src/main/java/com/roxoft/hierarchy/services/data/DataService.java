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
	
	private static final Logger rootLogger = LogManager.getRootLogger();
		
	private ArrayList<String> dataMaleNames = new ArrayList<String>();
	private ArrayList<String> dataFemaleNames = new ArrayList<String>();
	private ArrayList<String> dataMaleSurnames = new ArrayList<String>();
	private ArrayList<String> dataFemaleSurnames = new ArrayList<String>();
	private ArrayList<String> dataSchoolsNames = new ArrayList<String>();
	private ArrayList<String> dataUniversitiesNames = new ArrayList<String>();
	private ArrayList<String> dataCompaniesNames = new ArrayList<String>();
	private ArrayList<String> dataSpecialitiesNames = new ArrayList<String>();
	private ArrayList<String> dataProjectsNames = new ArrayList<String>();
	
	public void fillAllData(){
		
		Connection connection = null;
		try {
			
			connection = DataSource.getInstance("initial_data").getConnection();
				
			clearAllData();
			
			setMaleNames(connection);
			setFemaleNames(connection);
			setMaleSurnames(connection);
			setFemaleSurnames(connection);
			setSchoolsNames(connection);
			setUniversitiesNames(connection);
			setCompaniesNames(connection);
			setSpecialitiesNames(connection);
			setProjectsNames(connection);
			
		} catch (SQLException e) {
			rootLogger.error("SQLException", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					rootLogger.error("SQLException", e);
				}
		}
					
	}
	
	private void clearAllData(){
		
		if (!(dataMaleNames.isEmpty()))
			dataMaleNames.clear();
		if (!(dataFemaleNames.isEmpty()))
			dataFemaleNames.clear();
		if (!(dataMaleSurnames.isEmpty()))
			dataMaleSurnames.clear();
		if (!(dataFemaleSurnames.isEmpty()))
			dataFemaleSurnames.clear();
		if (!(dataSchoolsNames.isEmpty()))
			dataSchoolsNames.clear();
		if (!(dataUniversitiesNames.isEmpty()))
			dataUniversitiesNames.clear();
		if (!(dataCompaniesNames.isEmpty()))
			dataCompaniesNames.clear();
		if (!(dataSpecialitiesNames.isEmpty()))
			dataSpecialitiesNames.clear();
		if (!(dataProjectsNames.isEmpty()))
			dataProjectsNames.clear();
	}
	
	private void setMaleNames(Connection connection) {
		
		NamesMaleDAO nmDAO = new NamesMaleDAO(connection);
		nmDAO.fillTable();
		dataMaleNames.addAll(nmDAO.getAllData());
		
	}
	
	private void setFemaleNames(Connection connection) {
			
			NamesFemaleDAO nfDAO = new NamesFemaleDAO(connection);
			nfDAO.fillTable();
			dataFemaleNames.addAll(nfDAO.getAllData());
		
	}

	private void setMaleSurnames(Connection connection) {
		
		SurnamesMaleDAO smDAO = new SurnamesMaleDAO(connection);
		smDAO.fillTable();
		dataMaleSurnames.addAll(smDAO.getAllData());
	
	}
	
	private void setFemaleSurnames(Connection connection) {
		
		SurnamesFemaleDAO sfDAO = new SurnamesFemaleDAO(connection);
		sfDAO.fillTable();
		dataFemaleSurnames.addAll(sfDAO.getAllData());
	
	}
	
	private void setSchoolsNames(Connection connection) {
		
		SchoolsNamesDAO snDAO = new SchoolsNamesDAO(connection);
		snDAO.fillTable();
		dataSchoolsNames.addAll(snDAO.getAllData());
		
	}
	
	private void setUniversitiesNames(Connection connection) {
		
		UniversitiesNamesDAO unDAO = new UniversitiesNamesDAO(connection);
		unDAO.fillTable();
		dataUniversitiesNames.addAll(unDAO.getAllData());
		
	}
	
	private void setCompaniesNames(Connection connection) {
		
		CompaniesNamesDAO cnDAO = new CompaniesNamesDAO(connection);
		cnDAO.fillTable();
		dataCompaniesNames.addAll(cnDAO.getAllData());
		
	}
	
	private void setSpecialitiesNames(Connection connection) {
		
		SpecialitiesNamesDAO snDAO = new SpecialitiesNamesDAO(connection);
		snDAO.fillTable();
		dataSpecialitiesNames.addAll(snDAO.getAllData());
		
	}
	
	private void setProjectsNames(Connection connection) {
		
		ProjectsNamesDAO pnDAO = new ProjectsNamesDAO(connection);
		pnDAO.fillTable();
		dataProjectsNames.addAll(pnDAO.getAllData());
		
	}
	
	public ArrayList<String> getDataMaleNames() {
		return dataMaleNames;
	}

	public ArrayList<String> getDataFemaleNames() {
		return dataFemaleNames;
	}

	public ArrayList<String> getDataMaleSurnames() {
		return dataMaleSurnames;
	}

	public ArrayList<String> getDataFemaleSurnames() {
		return dataFemaleSurnames;
	}

	public ArrayList<String> getDataSchoolsNames() {
		return dataSchoolsNames;
	}
		
	public ArrayList<String> getDataUniversitiesNames() {
		return dataUniversitiesNames;
	}

	public ArrayList<String> getDataCompaniesNames() {
		return dataCompaniesNames;
	}

	public ArrayList<String> getDataSpecialitiesNames() {
		return dataSpecialitiesNames;
	}

	public ArrayList<String> getDataProjectsNames() {
		return dataProjectsNames;
	}

	public void printData() {
		
		rootLogger.info("\nAll data from DB 'initial_data'\n");
		
		rootLogger.info("Names male:");
		for (String str : dataMaleNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Names female");
		for (String str : dataFemaleNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Surnames male");
		for (String str : dataMaleSurnames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Surnames female");
		for (String str : dataFemaleSurnames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Schools names");
		for (String str : dataSchoolsNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Universities names");
		for (String str : dataUniversitiesNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Companies names");
		for (String str : dataCompaniesNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Specialities names");
		for (String str : dataSpecialitiesNames)
			rootLogger.info(str + " ");
		
		rootLogger.info("Projects names");
		for (String str : dataProjectsNames)
			rootLogger.info(str + " ");
		
	}
	
}
