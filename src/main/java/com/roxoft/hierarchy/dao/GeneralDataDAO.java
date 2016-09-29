package com.roxoft.hierarchy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class GeneralDataDAO {
	
	private static final Logger rootLogger = LogManager.getRootLogger();
	
	protected Connection connection;
	
	protected PreparedStatement getPreparedStatement(String sql) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			rootLogger.error("SQLException", e);
		}
		
		return preparedStatement;
		
	}

	protected void closePreparedStatement(PreparedStatement preparedStatement) {
		
		try {	
			if (preparedStatement != null){ 
				preparedStatement.close();
			}
		} catch (SQLException e) {
			rootLogger.error("SQLException", e);
		}
			
	}	

}
