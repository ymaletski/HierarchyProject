package com.roxoft.hierarchy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class GeneralDataDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(GeneralDataDAO.class);
	protected Connection connection;
	
	protected PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			LOGGER.error("SQLException in GeneralDataDAO.PreparedStatement(): ", e);
		}
		return preparedStatement;
	}

	protected void closePreparedStatement(PreparedStatement preparedStatement) {
		try {	
			if (preparedStatement != null){ 
				preparedStatement.close();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException in GeneralDataDAO.closePreparedStatement(): ", e);
		}	
	}	

}
