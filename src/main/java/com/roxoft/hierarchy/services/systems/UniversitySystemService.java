package com.roxoft.hierarchy.services.systems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.data.SpecialitiesNamesDAO;
import com.roxoft.hierarchy.dao.systems.AddressDAO;
import com.roxoft.hierarchy.dao.systems.LecturerDAO;
import com.roxoft.hierarchy.dao.systems.SpecialityDAO;
import com.roxoft.hierarchy.dao.systems.StudentDAO;
import com.roxoft.hierarchy.dao.systems.UniversityDAO;
import com.roxoft.hierarchy.dbcp.DataSource;
import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.University;

public class UniversitySystemService {
	
	private static final Logger LOGGER = LogManager.getLogger(UniversitySystemService.class);
	
	public void recordUniversitySystem(ArrayList<University> universities, 
			ArrayList<Lecturer> lecturers, ArrayList<Student> students){
		int universityId = 0;	
		Connection connection = null;
		try {	
			connection = DataSource.getInstance("systems").getConnection();			
			UniversityDAO universityDAO = new UniversityDAO(connection);
			StudentDAO studentDAO = new StudentDAO(connection);
			LecturerDAO lecturerDAO = new LecturerDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			SpecialityDAO specialityDAO = new SpecialityDAO(connection);	
			SpecialitiesNamesDAO snDAO = new SpecialitiesNamesDAO(connection);
			snDAO.fillTable();			
			for (University university : universities){	
				addressDAO.record(university.getAddress());
				universityDAO.record(university, addressDAO.findId(
						university.getAddress()));	
				universityId = universityDAO.findIdByName(university);
				if(universityId != 0){	
					for (String speciality : university.getSpecialities())
						universityDAO.recordUniversitiesAndSpecialities(universityId, 
								specialityDAO.findIdByName(speciality));
					for (Lecturer lecturer : university.getLecturers())
						lecturerDAO.record(lecturer, universityId);
					for (Student student : university.getStudents()){
						studentDAO.record(student, universityId, 
								specialityDAO.findIdByName(student.getSpeciality()));	
					}
				universityId = 0;
				}
			}				
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversitySystemService.recordUniversitySystem(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversitySystemService.recordUniversitySystem(): ", e);
				}
		}
	}
	
	public ArrayList<University> createUniversitySystem(){
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
		ArrayList<University> universities = new ArrayList<University>();
		int addressId = 0;		
		Connection connection = null;
		try {	
			connection = DataSource.getInstance("systems").getConnection();			
			UniversityDAO universityDAO = new UniversityDAO(connection);
			StudentDAO studentDAO = new StudentDAO(connection);
			LecturerDAO lecturerDAO = new LecturerDAO(connection);
			AddressDAO addressDAO = new AddressDAO(connection);
			SpecialityDAO specialityDAO = new SpecialityDAO(connection);
			universities.addAll(universityDAO.getAllData());
			students.addAll(studentDAO.getAllData());
			lecturers.addAll(lecturerDAO.getAllData());
			for (Student student : students){
				student.setUniversity(universityDAO.findById(
						studentDAO.findUniversityIdByName(student)));
				student.setSpeciality(specialityDAO.findById(
						studentDAO.findSpecialityIdByName(student)));
			}
			for (Lecturer lecturer : lecturers)
				lecturer.setUniversity(universityDAO.findById(lecturerDAO.
						findUniversityIdByName(lecturer)));
			for (University university : universities){									
				addressId = universityDAO.findAddressIdByName(university);
				if (addressId != 0)
					university.setAddress(addressDAO.findById(addressId));
				addressId = 0;	
				ArrayList<Lecturer> lecturersOfTheSameUniversity = new ArrayList<Lecturer>();
				for (Lecturer lecturer : lecturers){
					if (lecturer.getUniversity().getName().equals(university.getName()))
						lecturersOfTheSameUniversity.add(lecturer);
				}
				university.setLecturers(lecturersOfTheSameUniversity);	
				ArrayList<Student> studentsOfTheSameUniversity = new ArrayList<Student>();
				for (Student student : students){
					if (student.getUniversity().getName().equals(university.getName()))
						studentsOfTheSameUniversity.add(student);
				}
				university.setStudents(studentsOfTheSameUniversity);	
					ArrayList<String> specialities = new ArrayList<String>();
					for (int specialityId : universityDAO.findSpecialitiesIdsById(
							universityDAO.findIdByName(university))){
						specialities.add(specialityDAO.findById(specialityId));
					}
					university.setSpecialities(specialities);
			}											
		} catch (SQLException e) {
			LOGGER.error("SQLException in UniversitySystemService.createUniversitySystem(): ", e);
		} finally {
			if (connection != null) 
				try { 
					connection.close();
				} catch (SQLException e) {
					LOGGER.error("SQLException in UniversitySystemService.createUniversitySystem(): ", e);
				}
		}
		return universities;
	}

}
