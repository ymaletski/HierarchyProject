package com.roxoft.hierarchy.services.systems;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.dao.mybatis.mappers.PupilMapper;
import com.roxoft.hierarchy.dao.mybatis.mappers.SchoolMapper;
import com.roxoft.hierarchy.dao.mybatis.mappers.TeacherMapper;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.PupilMBDAO;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.SchoolMBDAO;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.TeacherMBDAO;
import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.School;

public class MyBatisSchoolSystemService {
	
	public ArrayList<School> createSchoolSystem(SqlSessionFactory sqlSessionFactory){
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		ArrayList<School> schools = new ArrayList<School>();
		SqlSession session = sqlSessionFactory.openSession();	
		try {			
			PupilMapper pupilMapper = session.getMapper(PupilMapper.class);
			pupils.addAll(pupilMapper.getAllDataWithSchoolsNames());
			TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
			teachers.addAll(teacherMapper.getAllDataWithSchoolsNames());
			SchoolMapper schoolMapper = session.getMapper(SchoolMapper.class);
			schools.addAll(schoolMapper.getAllData());					
		} finally {
		  session.close();
		}
		return schools;
	}
	
	public ArrayList<School> createSchoolSystemAlternatively(SqlSessionFactory sqlSessionFactory){
		ArrayList<Pupil> pupils = new ArrayList<Pupil>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		ArrayList<School> schools = new ArrayList<School>();			
		PupilMBDAO pupilMBDAO = new PupilMBDAO(sqlSessionFactory);
		pupils.addAll(pupilMBDAO.getAllDataWithSchoolsNames());
		TeacherMBDAO teacherMBDAO = new TeacherMBDAO(sqlSessionFactory);
		teachers.addAll(teacherMBDAO.getAllDataWithSchoolsNames());	
		SchoolMBDAO schoolMBDAO = new SchoolMBDAO(sqlSessionFactory);
		schools.addAll(schoolMBDAO.getAllData());
		return schools;	
	}

}
