package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOUniversity;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.institutions.University;

public class UniversityDAO extends GeneralSystemsDAO implements IDAOUniversity {
	
	private static final Logger LOGGER = LogManager.getLogger(UniversityDAO.class);

	public UniversityDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void record(University university, int addressId) {		
		final String SQL_INSERT = 
				"INSERT INTO universities(name, address_id) VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {			
			preparedStatement.setString(1, university.getName());
			preparedStatement.setInt(2, addressId);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.record(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteByName(University university) {		
		final String SQL_DELETE = 
				"DELETE FROM universities WHERE name = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {		
			preparedStatement.setString(1, university.getName());
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.deleteByName(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteById(int id) {		
		final String SQL_DELETE = 
				"DELETE FROM universities WHERE university_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.deleteById(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public int findAddressIdByName(University university) {		
		int addressId = 0;
		final String SQL_SELECT = "SELECT address_id FROM universities "
				+ "WHERE name = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, university.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				addressId = resultSet.getInt("address_id");						
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.findAddressIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversityDAO.findAddressIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}	
		return addressId;		
	}

	public int findIdByName(University university) {		
		int id = 0;
		final String SQL_SELECT = "SELECT university_id FROM universities "
				+ "WHERE name = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, university.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getInt("university_id");						
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.findIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversityDAO.findIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return id;		
	}

	public University findById(int id) {		
		University university = new University();
		final String SQL_SELECT = "SELECT name FROM universities "
				+ "WHERE university_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				university.setName(resultSet.getString("name"));										
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.findById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversityDAO.findById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return university;		
	}

	public ArrayList<University> getAllData() {		
		ArrayList<University> universities = new ArrayList<University>();		
		final String SQL_SELECT = "SELECT name FROM universities";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		try {			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				University university = new University();
				university.setName(resultSet.getString("name"));
				universities.add(university);
            }			
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversityDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return universities;		
	}

	public ArrayList<Integer> findSpecialitiesIdsById(int id) {		
		ArrayList<Integer> specialities = new ArrayList<Integer>();
		final String SQL_SELECT = "SELECT speciality_id FROM universities_has_specialities "
				+ "WHERE university_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				specialities.add(resultSet.getInt("speciality_id"));
			}										
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.findSpecialitiesIdsById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversityDAO.findSpecialitiesIdsById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}				
		return specialities;		
	}

	public void recordUniversitiesAndSpecialities(int universityId, 
			int specialityId) {		
		final String SQL_INSERT = 
				"INSERT INTO universities_has_specialities"
				+ "(university_id, speciality_id) VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);	
		try {
				preparedStatement.setInt(1, universityId);
				preparedStatement.setInt(2, specialityId);
				preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversityDAO.recordUniversitiesAndSpecialities(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

}
