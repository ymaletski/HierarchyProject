package com.roxoft.hierarchy.mybatis.mybatisServices;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.University;
import com.roxoft.hierarchy.mybatis.mappers.LecturerMapper;
import com.roxoft.hierarchy.mybatis.mappers.StudentMapper;
import com.roxoft.hierarchy.mybatis.mappers.UniversityMapper;
import com.roxoft.hierarchy.mybatis.mbDAO.LecturerMBDAO;
import com.roxoft.hierarchy.mybatis.mbDAO.StudentMBDAO;
import com.roxoft.hierarchy.mybatis.mbDAO.UniversityMBDAO;

public class MyBatisUniversitySystemService {
	
	private ArrayList<University> universities = new ArrayList<University>();
	private ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public void createUniversitySystem(SqlSessionFactory sqlSessionFactory){
		
		SqlSession session = sqlSessionFactory.openSession();
		
		clearAllData();
		
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
		
	}
	
	public void createUniversitySystemAlternatively(SqlSessionFactory sqlSessionFactory){
		
		clearAllData();
					
		StudentMBDAO studentMBDAO = new StudentMBDAO(sqlSessionFactory);
		students.addAll(studentMBDAO.getAllDataWithUniversitiesNames());
			
		LecturerMBDAO lecturerMBDAO = new LecturerMBDAO(sqlSessionFactory);
		lecturers.addAll(lecturerMBDAO.getAllDataWithUniversitiesNames());
			
		UniversityMBDAO universityMBDAO = new UniversityMBDAO(sqlSessionFactory);
		universities.addAll(universityMBDAO.getAllData());
			
	}
	
	private void clearAllData(){
		
		if (!(students.isEmpty()))
			students.clear();
		if (!(lecturers.isEmpty()))
			lecturers.clear();
		if (!(universities.isEmpty()))
			universities.clear();
			
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Lecturer> getLecturers() {
		return lecturers;
	}

	public ArrayList<University> getUniversities() {
		return universities;
	}

}
