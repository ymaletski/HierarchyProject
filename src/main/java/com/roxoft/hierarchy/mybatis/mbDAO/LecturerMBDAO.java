package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Lecturer;

public class LecturerMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.LecturerMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public LecturerMBDAO(final SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Lecturer> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		
		try
		{
			final String query = NAMESPACE + ".getAllData";
			lecturers.addAll(session.selectList(query));
			return lecturers;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<Lecturer> getLecturersByUniversityId(int universityId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		
		try
		{
			final String query = NAMESPACE + ".getLecturersByUniversityId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", universityId);
			lecturers.addAll(session.selectList(query, args));
			return lecturers;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<Lecturer> getAllDataWithUniversitiesNames() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		
		try
		{
			final String query = NAMESPACE + ".getAllDataWithUniversitiesNames";
			lecturers.addAll(session.selectList(query));
			return lecturers;
		} finally {
			session.close();
		}
		
	}

}
