package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOStudent;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.human.Student;

public class StudentDAO extends GeneralSystemsDAO implements IDAOStudent {
	
	private static final Logger LOGGER = LogManager.getLogger(StudentDAO.class);

	public StudentDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void record(Student student, int universitylId, int specialityId) {
		final String SQL_INSERT = 
				"INSERT INTO students(name, surname, university_id, speciality_id) "
				+ "VALUES(?, ?, ?, ?)";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {			
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			preparedStatement.setInt(3, universitylId);
			preparedStatement.setInt(4, specialityId);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.record(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteByName(Student student) {		
		final String SQL_DELETE = 
				"DELETE FROM students WHERE name = ? AND surname =?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.deleteByName(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteById(int id) {		
		final String SQL_DELETE = 
				"DELETE FROM students WHERE student_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.deleteById(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteByUniversityId(int universityId) {		
		final String SQL_DELETE = 
				"DELETE FROM students WHERE university_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);		
		try {		
			preparedStatement.setInt(1, universityId);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.deleteByUniversityId(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public Student findById(int id) {		
		Student student = new Student();
		final String SQL_SELECT = "SELECT name, surname FROM students "
				+ "WHERE student_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;	
		try {			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				student.setName(resultSet.getString("name"));
				student.setSurname(resultSet.getString("surname"));
			}									
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.findById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in StudentDAO.findById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return student;		
	}

	public int findSpecialityIdByName(Student student) {		
		int specialityId = 0;		
		final String SQL_SELECT = "SELECT speciality_id FROM students "
				+ "WHERE name = ? AND surname = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				specialityId = resultSet.getInt("speciality_id");						
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.findSpecialityIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in StudentDAO.findSpecialityIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return specialityId;		
	}

	public ArrayList<Student> getAllData() {		
		ArrayList<Student> students = new ArrayList<Student>();		
		final String SQL_SELECT = "SELECT name, surname FROM students";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		try {			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setName(resultSet.getString("name"));
				student.setSurname(resultSet.getString("surname"));
				students.add(student);
            }		
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in StudentDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return students;		
	}

	public ArrayList<Student> findAllByUniversityId(int universityId) {		
		ArrayList<Student> students = new ArrayList<Student>();		
		final String SQL_SELECT = "SELECT name, surname FROM students "
				+ "WHERE university_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setInt(1, universityId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setName(resultSet.getString("name"));
				student.setSurname(resultSet.getString("surname"));
				students.add(student);
            }						
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.findAllByUniversityId(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in StudentDAO.findAllByUniversityId(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return students;	
	}

	public int findUniversityIdByName(Student student) {	
		int universityId = 0;
		final String SQL_SELECT = "SELECT university_id FROM students "
				+ "WHERE name = ? AND surname = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				universityId = resultSet.getInt("university_id");										
		} catch (SQLException e) {
			LOGGER.error("SQLException in StudentDAO.findUniversityIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in StudentDAO.findUniversityIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return universityId;		
	}
	
}
