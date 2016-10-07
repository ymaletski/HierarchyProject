package com.roxoft.hierarchy.dao.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Pupil;

public class PupilMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.dao.mybatis.mappers.PupilMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public PupilMBDAO(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Pupil> getAllData() {		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();		
		try {
			final String query = NAMESPACE + ".getAllData";
			pupils.addAll(session.selectList(query));
			return pupils;
		} finally {
			session.close();
		}		
	}
	
	public ArrayList<Pupil> getPupilsBySchoolId(int schoolId){		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();		
		try {
			final String query = NAMESPACE + ".getPupulsBySchoolId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("school_id", schoolId);
			pupils.addAll(session.selectList(query, args));
			return pupils;
		} finally {
			session.close();
		}		
	}
	
	public ArrayList<Pupil> getAllDataWithSchoolsNames(){		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();		
		try {
			final String query = NAMESPACE + ".getAllDataWithSchoolsNames";
			pupils.addAll(session.selectList(query));
			return pupils;
		} finally {
			session.close();
		}		
	}
	
}
