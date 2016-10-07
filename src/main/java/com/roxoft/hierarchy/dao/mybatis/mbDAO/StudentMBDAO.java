package com.roxoft.hierarchy.dao.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Student;

public class StudentMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.dao.mybatis.mappers.StudentMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public StudentMBDAO(final SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Student> getAllData() {		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Student> students = new ArrayList<Student>();		
		try
		{
			final String query = NAMESPACE + ".getAllData";
			students.addAll(session.selectList(query));
			return students;
		} finally {
			session.close();
		}		
	}
	
	public ArrayList<Student> getStudentsByUniversityId(int universityId){		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Student> students = new ArrayList<Student>();	
		try
		{
			final String query = NAMESPACE + ".getStudentsByUniversityId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("university_id", universityId);
			students.addAll(session.selectList(query, args));
			return students;
		} finally {
			session.close();
		}		
	}
	
	public ArrayList<Student> getAllDataWithUniversitiesNames() {		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Student> students = new ArrayList<Student>();		
		try
		{
			final String query = NAMESPACE + ".getAllDataWithUniversitiesNames";
			students.addAll(session.selectList(query));
			return students;
		} finally {
			session.close();
		}		
	}

}
