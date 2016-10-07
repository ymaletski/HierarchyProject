package com.roxoft.hierarchy.services.systems;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.dao.mybatis.mappers.LecturerMapper;
import com.roxoft.hierarchy.dao.mybatis.mappers.StudentMapper;
import com.roxoft.hierarchy.dao.mybatis.mappers.UniversityMapper;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.LecturerMBDAO;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.StudentMBDAO;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.UniversityMBDAO;
import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.University;

public class MyBatisUniversitySystemService {
	
	public ArrayList<University> createUniversitySystem(SqlSessionFactory sqlSessionFactory){
		ArrayList<University> universities = new ArrayList<University>();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		ArrayList<Student> students = new ArrayList<Student>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = session.getMapper(StudentMapper.class);
			students.addAll(studentMapper.getAllDataWithUniversitiesNames());
			LecturerMapper lecturerMapper = session.getMapper(LecturerMapper.class);
			lecturers.addAll(lecturerMapper.getAllDataWithUniversitiesNames());
			UniversityMapper universityMapper = session.getMapper(UniversityMapper.class);
			universities.addAll(universityMapper.getAllData());		
		} finally {
		  session.close();
		}
		return universities;
	}
	
	public ArrayList<University> createUniversitySystemAlternatively(SqlSessionFactory sqlSessionFactory){
		ArrayList<University> universities = new ArrayList<University>();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		ArrayList<Student> students = new ArrayList<Student>();		
		StudentMBDAO studentMBDAO = new StudentMBDAO(sqlSessionFactory);
		students.addAll(studentMBDAO.getAllDataWithUniversitiesNames());	
		LecturerMBDAO lecturerMBDAO = new LecturerMBDAO(sqlSessionFactory);
		lecturers.addAll(lecturerMBDAO.getAllDataWithUniversitiesNames());	
		UniversityMBDAO universityMBDAO = new UniversityMBDAO(sqlSessionFactory);
		universities.addAll(universityMBDAO.getAllData());
		return universities;	
	}

}
