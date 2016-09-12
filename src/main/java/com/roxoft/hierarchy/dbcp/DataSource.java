package com.roxoft.hierarchy.dbcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {
	
	private static DataSource dataSource;
    private BasicDataSource basicDataSource;
           
    private DataSource(String schemaType){
    	
    	basicDataSource = new BasicDataSource();
    	Properties properties = new Properties();
		FileInputStream fileInputStream = null;	
		
		try {			
			fileInputStream = new FileInputStream("src\\main\\resources\\db.properties");
			properties.load(fileInputStream);
			
			basicDataSource.setDriverClassName(properties.getProperty("MYSQL_DB_DRIVER_CLASS"));
			basicDataSource.setUsername(properties.getProperty("MYSQL_DB_USERNAME"));
			basicDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
            
            if("initial_data".equals(schemaType)){
            	basicDataSource.setUrl(properties.getProperty("MYSQL_DB_URL_FOR_INITIAL_DATA_SCHEMA"));
            } 
            else if("systems".equals(schemaType)){
            	basicDataSource.setUrl(properties.getProperty("MYSQL_DB_URL_FOR_SYSTEMS_SCHEMA"));
            }
            else {
            	System.out.println("58tyujghj");
            }
            
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(20);
            basicDataSource.setMaxOpenPreparedStatements(180); 
            
		} catch(IOException e) {			
			e.printStackTrace();			
		}
    	
    }
    
    public static DataSource getInstance(String schemaType){
    	/*    	
    	if (dataSource == null) {	
    		dataSource = new DataSource();    		
            return dataSource;
        } else {
        	return dataSource;
        }
    	*/
    	
    	dataSource = new DataSource(schemaType);
    	
    	return dataSource;
    	
    }
	
    public Connection getConnection() throws SQLException{
    	
    	return this.basicDataSource.getConnection();
		
    }
	
}
