package com.roxoft.hierarchy.dao.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.institutions.School;

public class SchoolMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.dao.mybatis.mappers.SchoolMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public SchoolMBDAO(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<School> getAllData() {		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<School> schools = new ArrayList<School>();	
		try {
			final String query = NAMESPACE + ".getAllData";
			schools.addAll(session.selectList(query));
			return schools;
		} finally {
			session.close();
		}		
	}
	
	public School getSchoolById(int schoolId){		
		final SqlSession session = sqlSessionFactory.openSession();
		School school = new School();		
		try {
			final String query = NAMESPACE + ".getSchoolById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("school_id", schoolId);
			school = session.selectOne(query, args);
			return school;
		} finally {
			session.close();
		}		
	}
	
	public School getPureSchoolById(int schoolId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		School school = new School();		
		try {
			final String query = NAMESPACE + ".getPureSchoolById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("school_id", schoolId);
			school = session.selectOne(query, args);
			return school;
		} finally {
			session.close();
		}		
	}

}
