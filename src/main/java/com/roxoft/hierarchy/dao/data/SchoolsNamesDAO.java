package com.roxoft.hierarchy.dao.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.roxoft.hierarchy.dao.IDAOData;
import com.roxoft.hierarchy.dao.GeneralDataDAO;
import com.roxoft.hierarchy.data.Schools;

public class SchoolsNamesDAO extends GeneralDataDAO implements IDAOData {
	
	public SchoolsNamesDAO(Connection connection) {
		this.connection = connection;
	}
		
	public ArrayList<String> getAllData() {
		
		ArrayList<String> names = new ArrayList<String>();
		
		final String SQL_SELECT = "SELECT `name` FROM `schools`";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
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
		
		return names;
		
	}

	public void fillTable() {

		ArrayList<String> names = new ArrayList<String>(Arrays.asList(
				(new Schools()).getSchools()));
		
		final String SQL_INSERT = 
				"INSERT INTO `schools`(`name`) VALUES(?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);
		
		try {
			for (String str: names){
				preparedStatement.setString(1, str);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}
	
}
