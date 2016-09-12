package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.roxoft.hierarchy.dao.IDAOTeacher;
import com.roxoft.hierarchy.dao.JeneralSystemsDAO;
import com.roxoft.hierarchy.models.human.Teacher;

public class TeacherDAO extends JeneralSystemsDAO implements IDAOTeacher{
	
	public TeacherDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void record(Teacher teacher, int schoolId) {

		final String SQL_INSERT = 
				"INSERT INTO teachers(name, surname, school_id) "
				+ "VALUES(?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);
		
		try {
			
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSurname());
			preparedStatement.setInt(3, schoolId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}
	
	public void deleteByName(Teacher teacher) {
		
		final String SQL_DELETE = 
				"DELETE FROM teachers WHERE name = ? AND surname =?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSurname());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public void deleteById(int id) {
		
		final String SQL_DELETE = 
				"DELETE FROM teachers WHERE teacher_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}
	
	public void deleteBySchoolId(int schoolId) {
		
		final String SQL_DELETE = 
				"DELETE FROM teachers WHERE school_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setInt(1, schoolId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}
	
	public ArrayList<Teacher> getAllData() {
		
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		final String SQL_SELECT = "SELECT name, surname FROM teachers";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setName(resultSet.getString("name"));
				teacher.setSurname(resultSet.getString("surname"));
				teachers.add(teacher);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return teachers;
		
	}

	public ArrayList<Teacher> findAllBySchoolId(int schoolId) {
		
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		final String SQL_SELECT = "SELECT name, surname FROM teachers "
				+ "WHERE school_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, schoolId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setName(resultSet.getString("name"));
				teacher.setSurname(resultSet.getString("surname"));
				teachers.add(teacher);
            }
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return teachers;
		
	}

	public Teacher findById(int id) {
		
		Teacher teacher = new Teacher();
		final String SQL_SELECT = "SELECT name, surname FROM teachers "
				+ "WHERE teacher_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				teacher.setName(resultSet.getString("name"));
				teacher.setSurname(resultSet.getString("surname"));
			}
										
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return teacher;
		
	}
	
public int findSchoolIdByName(Teacher teacher) {
		
		int schoolId = 0;
		final String SQL_SELECT = "SELECT school_id FROM teachers "
				+ "WHERE name = ? AND surname = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSurname());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				schoolId = resultSet.getInt("school_id");
										
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return schoolId;
		
	}

}
