package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOLecturer;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.human.Lecturer;

public class LecturerDAO extends GeneralSystemsDAO implements IDAOLecturer {
	
	private static final Logger LOGGER = LogManager.getLogger(LecturerDAO.class);

	public LecturerDAO(Connection connection) {
		this.connection = connection;
	}
		
	public void record(Lecturer lecturer, int universitylId) {		
		final String SQL_INSERT = 
				"INSERT INTO lecturers(name, surname, university_id) "
				+ "VALUES(?, ?, ?)";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {			
			preparedStatement.setString(1, lecturer.getName());
			preparedStatement.setString(2, lecturer.getSurname());
			preparedStatement.setInt(3, universitylId);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.record(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}				
	}

	public void deleteByName(Lecturer lecturer) {		
		final String SQL_DELETE = 
				"DELETE FROM lecturers WHERE name = ? AND surname =?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setString(1, lecturer.getName());
			preparedStatement.setString(2, lecturer.getSurname());
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.deleteByName(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}	
	}

	public void deleteById(int id) {		
		final String SQL_DELETE = 
				"DELETE FROM lecturers WHERE lecturer_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.deleteById(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}	
	}

	public void deleteByUniversityId(int universityId) {		
		final String SQL_DELETE = 
				"DELETE FROM lecturers WHERE university_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setInt(1, universityId);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.deleteByUniversityId(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public Lecturer findById(int id) {		
		Lecturer lecturer = new Lecturer();
		final String SQL_SELECT = "SELECT name, surname FROM lecturers "
				+ "WHERE lecturer_id = ?";	
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {		
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				lecturer.setName(resultSet.getString("name"));
				lecturer.setSurname(resultSet.getString("surname"));
			}										
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.findById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in LecturerDAO.findById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return lecturer;		
	}

	public ArrayList<Lecturer> getAllData() {	
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();	
		final String SQL_SELECT = "SELECT name, surname FROM lecturers";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		try {		
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Lecturer lecturer = new Lecturer();
				lecturer.setName(resultSet.getString("name"));
				lecturer.setSurname(resultSet.getString("surname"));
				lecturers.add(lecturer);
            }			
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in LecturerDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return lecturers;	
	}

	public ArrayList<Lecturer> findAllByUniversityId(int universityId) {		
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();	
		final String SQL_SELECT = "SELECT name, surname FROM lecturers "
				+ "WHERE university_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		try {	
			preparedStatement.setInt(1, universityId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Lecturer lecturer = new Lecturer();
				lecturer.setName(resultSet.getString("name"));
				lecturer.setSurname(resultSet.getString("surname"));
				lecturers.add(lecturer);
            }					
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.findAllByUniversityId(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in LecturerDAO.findAllByUniversityId(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}
		return lecturers;
	}

	public int findUniversityIdByName(Lecturer lecturer) {		
		int universityId = 0;
		final String SQL_SELECT = "SELECT university_id FROM lecturers "
				+ "WHERE name = ? AND surname = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, lecturer.getName());
			preparedStatement.setString(2, lecturer.getSurname());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				universityId = resultSet.getInt("university_id");										
		} catch (SQLException e) {
			LOGGER.error("SQLException in LecturerDAO.findUniversityIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in LecturerDAO.findUniversityIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return universityId;		
	}

}
