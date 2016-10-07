package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOSpeciality;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;

public class SpecialityDAO extends GeneralSystemsDAO implements IDAOSpeciality {
	
	private static final Logger LOGGER = LogManager.getLogger(SpecialityDAO.class);

	public SpecialityDAO(Connection connection) {
		this.connection = connection;
	}
		
	public void record(String speciality) {		
		final String SQL_INSERT = 
				"INSERT INTO specialities(name) VALUES(?)";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {			
			preparedStatement.setString(1, speciality);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.record(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteByName(String speciality) {		
		final String SQL_DELETE = 
				"DELETE FROM specialities WHERE name = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setString(1, speciality);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.deleteByName(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteById(int id) {		
		final String SQL_DELETE = 
				"DELETE FROM specialities WHERE speciality_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {		
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.deleteById(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public String findById(int id) {		
		String speciality = new String();
		final String SQL_SELECT = "SELECT name FROM specialities "
				+ "WHERE speciality_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {		
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				speciality = resultSet.getString("name");									
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.findById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in SpecialityDAO.findById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return speciality;	
	}

	public int findIdByName(String speciality) {	
		int id = 0;
		final String SQL_SELECT = "SELECT speciality_id FROM specialities "
				+ "WHERE name = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {		
			preparedStatement.setString(1, speciality);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getInt("speciality_id");						
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.findIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in SpecialityDAO.findIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return id;		
	}

	public ArrayList<String> getAllData() {		
		ArrayList<String> specialities = new ArrayList<String>();		
		final String SQL_SELECT = "SELECT name FROM specialities";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		try {			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String speciality = new String();
				speciality = resultSet.getString("name");
				specialities.add(speciality);
            }			
		} catch (SQLException e) {
			LOGGER.error("SQLException in SpecialityDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in SpecialityDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return specialities;		
	}

}
