package com.roxoft.hierarchy.io.printers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.School;

public class SchoolSystemPrinter {
	
	private static final Logger schoolSystemLogger = LogManager.getLogger(SchoolSystemPrinter.class);
	
	public void purePrint(ArrayList<Pupil> pupils, ArrayList<Teacher> teachers, 
			ArrayList<School> schools){		
		schoolSystemLogger.info("\nSchool System\n");
		schoolSystemLogger.info("Number of pupils: "+pupils.size()+".");
		for (Pupil pupil : pupils)
			schoolSystemLogger.info("Pupil "+(pupils.indexOf(pupil)+1)+": "+
					pupil.getName()+" "+pupil.getSurname()+", school: "+
					pupil.getSchool().getName()+".");
		schoolSystemLogger.info("Number of teachers: "+teachers.size()+".");
		for (Teacher teacher : teachers)
			schoolSystemLogger.info("Teacher "+(teachers.indexOf(teacher)+1)+": "+
					teacher.getName()+" "+teacher.getSurname()+", school: "+
					teacher.getSchool().getName()+".");
		schoolSystemLogger.info("Number of schools: "+schools.size()+".");
		for (School school : schools)
			schoolSystemLogger.info("School "+(schools.indexOf(school)+1)+": "+
					school.getName()+"; address: "+school.getAddress().getStreet()+", "+
					school.getAddress().getHouse()+", "+
					school.getAddress().getPostcode()+", "+
					school.getAddress().getCity()+".");
	}
	
	public void printSchoolSystem(ArrayList<School> schools){
		int numberOfTeachers = 0, numberOfPupils = 0;
		schoolSystemLogger.info("\nSchool System");
		
		for (School school : schools){
			schoolSystemLogger.info("\nSchool: "+school.getName()+".");
			schoolSystemLogger.info("Address: "+school.getAddress().getStreet()+", "+
					school.getAddress().getHouse()+", "+
					school.getAddress().getPostcode()+", "+
					school.getAddress().getCity()+".");
			for (Teacher teacher : school.getTeachers()){
				schoolSystemLogger.info("Teacher: "+teacher.getName()+" "+
						teacher.getSurname()+".");
				numberOfTeachers++;
			}
			for (Pupil pupil : school.getPupils()){
				schoolSystemLogger.info("Pupil: "+pupil.getName()+" "+
						pupil.getSurname()+".");
				numberOfPupils++;
			}
			schoolSystemLogger.info("School "+school.getName()+": number of teachers = "+
					numberOfTeachers+ ", number of pupils = "+numberOfPupils+".");
			numberOfTeachers = 0;
			numberOfPupils = 0;
		}
	}

}
