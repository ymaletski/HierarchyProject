package com.roxoft.hierarchy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JeneralSystemsDAO {
	
	protected Connection connection;
	
	protected PreparedStatement getPreparedStatement(String sql) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return preparedStatement;
		
	}

	protected void closePreparedStatement(PreparedStatement preparedStatement) {
		
		try {	
			if (preparedStatement != null){ 
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
}
