package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SpecialityMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.SpecialityMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public SpecialityMBDAO(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<String> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<String> specialities = new ArrayList<String>();
		
		try {
			final String query = NAMESPACE + ".getAllData";
			specialities.addAll(session.selectList(query));
			return specialities;
		} finally {
			session.close();
		}
		
	}
	
	public String getSpecialityById(int specialitylId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		String speciality = new String();
		
		try {
			final String query = NAMESPACE + ".getSpecialityById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("speciality_id", specialitylId);
			speciality = session.selectOne(query, args);
			return speciality;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<String> getSpecialitiesByUniversityId(int universitylId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<String> specialities = new ArrayList<String>();
		
		try {
			final String query = NAMESPACE + ".getSpecialitiesByUniversityId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", universitylId);
			specialities.addAll(session.selectList(query, args));
			return specialities;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<String> getSpecialitiesByProjectId(int projectlId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<String> specialities = new ArrayList<String>();
		
		try {
			final String query = NAMESPACE + ".getSpecialitiesByUniversityId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", projectlId);
			specialities.addAll(session.selectList(query, args));
			return specialities;
		} finally {
			session.close();
		}
		
	}

}
