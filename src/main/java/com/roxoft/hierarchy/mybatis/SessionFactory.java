package com.roxoft.hierarchy.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {
	
	private static SessionFactory sessionFactory;
	private SqlSessionFactory sqlSessionFactory;
	
	private SessionFactory(){
		
		String resource = "mybatis_config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
	}

	public static SessionFactory getInstance() {
		
		if (sessionFactory == null) {	
			sessionFactory = new SessionFactory();    		
            return sessionFactory;
        } else {
        	return sessionFactory;
        }
	
	}

	public SqlSessionFactory getSqlSessionFactory() {
				
		return sqlSessionFactory;
		
	}
		
}
