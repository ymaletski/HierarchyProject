package com.roxoft.hierarchy.dao.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOData;
import com.roxoft.hierarchy.dao.GeneralDataDAO;
import com.roxoft.hierarchy.data.Projects;

public class ProjectsNamesDAO extends GeneralDataDAO implements IDAOData {
	
	private static final Logger LOGGER = LogManager.getLogger(ProjectsNamesDAO.class);
	
	public ProjectsNamesDAO(Connection connection) {
		this.connection = connection;
	}
		
	public ArrayList<String> getAllData() {
		ArrayList<String> names = new ArrayList<String>();		
		final String SQL_SELECT = "SELECT `name` FROM `projects`";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		try {
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
            }
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectsNamesDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectsNamesDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return names;	
	}

	public void fillTable() {
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(
				(new Projects().getProjects())));
		
		final String SQL_INSERT = 
				"INSERT INTO `projects`(`name`) VALUES(?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {
			for (String str: names){
				preparedStatement.setString(1, str);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectsNamesDAO.fillTable(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

}
