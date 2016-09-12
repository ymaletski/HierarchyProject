package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.institutions.University;

public class UniversityMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.UniversityMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public UniversityMBDAO(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<University> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<University> universities = new ArrayList<University>();
		
		try {
			final String query = NAMESPACE + ".getAllData";
			universities.addAll(session.selectList(query));
			return universities;
		} finally {
			session.close();
		}
		
	}
	
	public University getUniversityById(int universityId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		University university = new University();
		
		try {
			final String query = NAMESPACE + ".getUniversityById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", universityId);
			university = session.selectOne(query, args);
			return university;
		} finally {
			session.close();
		}
		
	}
	
	public University getPureUniversityById(int universityId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		University university = new University();
		
		try {
			final String query = NAMESPACE + ".getPureUniversityById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", universityId);
			university = session.selectOne(query, args);
			return university;
		} finally {
			session.close();
		}
		
	}

}
