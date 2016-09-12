package com.roxoft.hierarchy.mybatis.mybatisServices;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.School;
import com.roxoft.hierarchy.mybatis.mappers.PupilMapper;
import com.roxoft.hierarchy.mybatis.mappers.SchoolMapper;
import com.roxoft.hierarchy.mybatis.mappers.TeacherMapper;
import com.roxoft.hierarchy.mybatis.mbDAO.PupilMBDAO;
import com.roxoft.hierarchy.mybatis.mbDAO.SchoolMBDAO;
import com.roxoft.hierarchy.mybatis.mbDAO.TeacherMBDAO;

public class MyBatisSchoolSystemService {
	
	private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<School> schools = new ArrayList<School>();
	
	public void createSchoolSystem(SqlSessionFactory sqlSessionFactory){
		
		SqlSession session = sqlSessionFactory.openSession();
		
		clearAllData();
		
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
		
	}
	
	public void createSchoolSystemAlternatively(SqlSessionFactory sqlSessionFactory){
		
		clearAllData();
					
		PupilMBDAO pupilMBDAO = new PupilMBDAO(sqlSessionFactory);
		pupils.addAll(pupilMBDAO.getAllDataWithSchoolsNames());
		
		TeacherMBDAO teacherMBDAO = new TeacherMBDAO(sqlSessionFactory);
		teachers.addAll(teacherMBDAO.getAllDataWithSchoolsNames());
			
		SchoolMBDAO schoolMBDAO = new SchoolMBDAO(sqlSessionFactory);
		schools.addAll(schoolMBDAO.getAllData());
			
	}
	
	private void clearAllData(){
		
		if (!(pupils.isEmpty()))
			pupils.clear();
		if (!(teachers.isEmpty()))
			teachers.clear();
		if (!(schools.isEmpty()))
			schools.clear();
			
	}
	
	public ArrayList<Pupil> getPupils() {
		return pupils;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public ArrayList<School> getSchools() {
		return schools;
	}

}
