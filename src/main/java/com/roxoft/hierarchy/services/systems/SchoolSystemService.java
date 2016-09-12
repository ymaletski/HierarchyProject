package com.roxoft.hierarchy.services.systems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.roxoft.hierarchy.dao.systems.AddressDAO;
import com.roxoft.hierarchy.dao.systems.PupilDAO;
import com.roxoft.hierarchy.dao.systems.SchoolDAO;
import com.roxoft.hierarchy.dao.systems.TeacherDAO;
import com.roxoft.hierarchy.dbcp.DataSource;
import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.School;

public class SchoolSystemService {
	
	private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<School> schools = new ArrayList<School>();
	
	public void recordSchoolSystem(ArrayList<School> schools, ArrayList<Teacher> teachers, 
			ArrayList<Pupil> pupils){
		
		Connection connection = null;
		
		int schoolId = 0;
		
		try {
			
			connection = DataSource.getInstance("systems").getConnection();
					
			SchoolDAO schoolDAO = new SchoolDAO(connection);
			PupilDAO pupilDAO = new PupilDAO(connection);
			TeacherDAO teacherDAO = new TeacherDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			
			for (School school: schools){
	
				addressDAO.record(school.getAddress());
				schoolDAO.record(school, addressDAO.findId(school.getAddress()));
				
				schoolId = schoolDAO.findIdByName(school);
				
				if(schoolId != 0){
					for (Pupil pupil : school.getPupils())
						pupilDAO.record(pupil, schoolId);
									
					for (Teacher teacher : school.getTeachers())
						teacherDAO.record(teacher, schoolId);
				}
				schoolId = 0;
				
			}					
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
	}
	
	public void createSchoolSystem(){
		
		int addressId = 0;
		
		clearAllData();
		
		Connection connection = null;
		
		try {
			
			connection = DataSource.getInstance("systems").getConnection();
					
			SchoolDAO schoolDAO = new SchoolDAO(connection);
			PupilDAO pupilDAO = new PupilDAO(connection);
			TeacherDAO teacherDAO = new TeacherDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			
			schools.addAll(schoolDAO.getAllData());
			pupils.addAll(pupilDAO.getAllData());
			teachers.addAll(teacherDAO.getAllData());
			
			for (Pupil pupil : pupils)
				pupil.setSchool(schoolDAO.findById(pupilDAO.findSchoolIdByName(pupil)));
			
			for (Teacher teacher : teachers)
				teacher.setSchool(schoolDAO.findById(teacherDAO.findSchoolIdByName(teacher)));
						
			for (School school : schools){
				
				ArrayList<Teacher> teachersOfTheSameSchool = new ArrayList<Teacher>();
				for (Teacher teacher : teachers){
					if (teacher.getSchool().getName().equals(school.getName()))
						teachersOfTheSameSchool.add(teacher);
				}
				school.setTeachers(teachersOfTheSameSchool);
				
				ArrayList<Pupil> pupilsOfTheSameSchool = new ArrayList<Pupil>();
				for (Pupil pupil : pupils){
					if (pupil.getSchool().getName().equals(school.getName()))
						pupilsOfTheSameSchool.add(pupil);
				}
				school.setPupils(pupilsOfTheSameSchool);
				
				addressId = schoolDAO.findAddressIdByName(school);
				if (addressId != 0)
					school.setAddress(addressDAO.findById(addressId));
				addressId = 0;
				
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
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
