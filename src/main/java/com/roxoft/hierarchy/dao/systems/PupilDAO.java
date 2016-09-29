package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.roxoft.hierarchy.dao.IDAOPupil;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.human.Pupil;

public class PupilDAO extends GeneralSystemsDAO implements IDAOPupil {
	
	public PupilDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void record(Pupil pupil, int schoolId) {

		final String SQL_INSERT = 
				"INSERT INTO pupils(name, surname, school_id) "
				+ "VALUES(?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);
		
		try {
			
			preparedStatement.setString(1, pupil.getName());
			preparedStatement.setString(2, pupil.getSurname());
			preparedStatement.setInt(3, schoolId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}
	
	public void deleteByName(Pupil pupil) {
		
		final String SQL_DELETE = 
				"DELETE FROM pupils WHERE name = ? AND surname =?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setString(1, pupil.getName());
			preparedStatement.setString(2, pupil.getSurname());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public void deleteById(int id) {
		
		final String SQL_DELETE = 
				"DELETE FROM pupils WHERE pupil_id = ?";
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
				"DELETE FROM pupils WHERE school_id = ?";
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
	
	public Pupil findById(int id) {
		
		Pupil pupil = new Pupil();
		final String SQL_SELECT = "SELECT name, surname FROM pupils "
				+ "WHERE pupil_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				pupil.setName(resultSet.getString("name"));
				pupil.setSurname(resultSet.getString("surname"));
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
		
		return pupil;
		
	}
	
	public ArrayList<Pupil> getAllData() {
		
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		
		final String SQL_SELECT = "SELECT name, surname FROM pupils";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pupil pupil = new Pupil();
				pupil.setName(resultSet.getString("name"));
				pupil.setSurname(resultSet.getString("surname"));
				pupils.add(pupil);
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
		
		return pupils;
		
	}

	public ArrayList<Pupil> findAllBySchoolId(int schoolId) {
		
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		
		final String SQL_SELECT = "SELECT name, surname FROM pupils "
				+ "WHERE school_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, schoolId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pupil pupil = new Pupil();
				pupil.setName(resultSet.getString("name"));
				pupil.setSurname(resultSet.getString("surname"));
				pupils.add(pupil);
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
		
		return pupils;
		
	}

	public int findSchoolIdByName(Pupil pupil) {
		
		int schoolId = 0;
		final String SQL_SELECT = "SELECT school_id FROM pupils "
				+ "WHERE name = ? AND surname = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setString(1, pupil.getName());
			preparedStatement.setString(2, pupil.getSurname());
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
