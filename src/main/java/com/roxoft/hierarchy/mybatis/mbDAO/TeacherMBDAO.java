package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Teacher;

public class TeacherMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.TeacherMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public TeacherMBDAO(final SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Teacher> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try
		{
			final String query = NAMESPACE + ".getAllData";
			teachers.addAll(session.selectList(query));
			return teachers;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<Teacher> getPupilsBySchoolId(int schoolId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try
		{
			final String query = NAMESPACE + ".getTeachersBySchoolId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("school_id", schoolId);
			teachers.addAll(session.selectList(query, args));
			return teachers;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<Teacher> getAllDataWithSchoolsNames() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try
		{
			final String query = NAMESPACE + ".getAllDataWithSchoolsNames";
			teachers.addAll(session.selectList(query));
			return teachers;
		} finally {
			session.close();
		}
		
	}

}
