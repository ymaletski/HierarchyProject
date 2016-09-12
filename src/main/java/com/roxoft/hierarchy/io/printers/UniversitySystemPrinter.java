package com.roxoft.hierarchy.io.printers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.University;

public class UniversitySystemPrinter {
	
	private static final Logger universitySystemLogger = LogManager.getLogger(UniversitySystemPrinter.class);
	
	public void purePrint(ArrayList<Student> students, ArrayList<Lecturer> lecturers, 
			ArrayList<University> universities){
		
		/*
		System.out.println("\nUniversity System\n");
		System.out.println("Number of students: "+students.size()+".");
		for (Student student : students)
			System.out.println("Student "+(students.indexOf(student)+1)+": "+
					student.getName()+" "+student.getSurname()+", university: "+
					student.getUniversity().getName()+", speciality: "+
					student.getSpeciality()+".");
		System.out.println("Number of lecturers: "+lecturers.size()+".");
		for (Lecturer lecturer : lecturers)
			System.out.println("Lecturer "+(lecturers.indexOf(lecturer)+1)+": "+
					lecturer.getName()+" "+lecturer.getSurname()+", university: "+
					lecturer.getUniversity().getName()+".");
		System.out.println("Number of universities: "+universities.size()+".");
		for (University university : universities)
			System.out.println("University "+(universities.indexOf(university)+1)+": "+
					university.getName()+"; address: "+
					university.getAddress().getStreet()+", "+
					university.getAddress().getHouse()+", "+
					university.getAddress().getPostcode()+", "+
					university.getAddress().getCity()+".");
		*/
		
		universitySystemLogger.info("\nUniversity System\n");
		universitySystemLogger.info("Number of students: "+students.size()+".");
		for (Student student : students)
			universitySystemLogger.info("Student "+(students.indexOf(student)+1)+": "+
					student.getName()+" "+student.getSurname()+", university: "+
					student.getUniversity().getName()+", speciality: "+
					student.getSpeciality()+".");
		universitySystemLogger.info("Number of lecturers: "+lecturers.size()+".");
		for (Lecturer lecturer : lecturers)
			universitySystemLogger.info("Lecturer "+(lecturers.indexOf(lecturer)+1)+": "+
					lecturer.getName()+" "+lecturer.getSurname()+", university: "+
					lecturer.getUniversity().getName()+".");
		universitySystemLogger.info("Number of universities: "+universities.size()+".");
		for (University university : universities)
			universitySystemLogger.info("University "+(universities.indexOf(university)+1)+": "+
					university.getName()+"; address: "+
					university.getAddress().getStreet()+", "+
					university.getAddress().getHouse()+", "+
					university.getAddress().getPostcode()+", "+
					university.getAddress().getCity()+".");
		
	}
	
	public void printUniversitySystem(ArrayList<University> universities){
		
		/*
		int numberOfLecturers = 0, numberOfStudents = 0;
		
		System.out.println("\nUniversity System");
		
		for (University university : universities){
			System.out.println("\nUniversity: "+university.getName()+".");
			System.out.println("Address: "+university.getAddress().getStreet()+", "+
			university.getAddress().getHouse()+", "+
			university.getAddress().getPostcode()+", "+
			university.getAddress().getCity()+".");
			System.out.println("Specialities: "+university.getSpecialities()+".");
			for (Lecturer lecturer : university.getLecturers()){
				System.out.println("Lecturer: "+lecturer.getName()+" "+
						lecturer.getSurname()+".");
				numberOfLecturers++;
			}
			for (Student student : university.getStudents()){
				System.out.println("Student: "+student.getName()+" "+
						student.getSurname()+", spesiality: "+
						student.getSpeciality()+".");
				numberOfStudents++;	
			}
			System.out.println("University "+university.getName()+
					": number of lecturers = "+numberOfLecturers+
					", number of students = "+numberOfStudents+".");
			numberOfLecturers = 0;
			numberOfStudents = 0;
		}
		*/
		
		int numberOfLecturers = 0, numberOfStudents = 0;
		
		universitySystemLogger.info("\nUniversity System");
		
		for (University university : universities){
			universitySystemLogger.info("\nUniversity: "+university.getName()+".");
			universitySystemLogger.info("Address: "+university.getAddress().getStreet()+", "+
			university.getAddress().getHouse()+", "+
			university.getAddress().getPostcode()+", "+
			university.getAddress().getCity()+".");
			universitySystemLogger.info("Specialities: "+university.getSpecialities()+".");
			for (Lecturer lecturer : university.getLecturers()){
				universitySystemLogger.info("Lecturer: "+lecturer.getName()+" "+
						lecturer.getSurname()+".");
				numberOfLecturers++;
			}
			for (Student student : university.getStudents()){
				universitySystemLogger.info("Student: "+student.getName()+" "+
						student.getSurname()+", spesiality: "+
						student.getSpeciality()+".");
				numberOfStudents++;	
			}
			universitySystemLogger.info("University "+university.getName()+
					": number of lecturers = "+numberOfLecturers+
					", number of students = "+numberOfStudents+".");
			numberOfLecturers = 0;
			numberOfStudents = 0;
		}
		
	}
}
