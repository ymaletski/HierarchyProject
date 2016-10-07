package com.roxoft.hierarchy.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionFactory {
	private static SessionFactory sessionFactory;
	private SqlSessionFactory sqlSessionFactory;
	private static final Logger LOGGER = LogManager.getLogger(SessionFactory.class);
	private SessionFactory(){	
		String resource = "mybatis_config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {	
			LOGGER.error("IOException in SessionFactory: ",e);
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
