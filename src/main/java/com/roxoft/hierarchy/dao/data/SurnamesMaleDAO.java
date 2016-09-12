package com.roxoft.hierarchy.dao.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.roxoft.hierarchy.dao.IDAOData;
import com.roxoft.hierarchy.dao.JeneralDataDAO;
import com.roxoft.hierarchy.data.SurnamesMale;

public class SurnamesMaleDAO extends JeneralDataDAO implements IDAOData {
	
	public SurnamesMaleDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<String> getAllData() {
		
		ArrayList<String> names = new ArrayList<String>();
		
		final String SQL_SELECT = "SELECT `surname` FROM `surnames_male`";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.add(resultSet.getString("surname"));
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
				(new SurnamesMale()).getSurnames()));
		
		final String SQL_INSERT = 
				"INSERT INTO `surnames_male`(`surname`) VALUES(?)";
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
