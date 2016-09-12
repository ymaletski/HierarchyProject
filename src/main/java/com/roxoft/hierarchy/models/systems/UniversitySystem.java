package com.roxoft.hierarchy.models.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.roxoft.hierarchy.models.address.Address;
import com.roxoft.hierarchy.data.Universities;
import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.institutions.University;
import com.roxoft.hierarchy.randomFillers.RandomNameAndSurnameFiller;

public class UniversitySystem {
	
	private ArrayList<University> universities = new ArrayList<University>();
	private ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public void fillUniversitySystem(int numberOfUniversities, 
			int numberOfLecturers, int numberOfStudents){
		
		if (!((students.isEmpty())&(lecturers.isEmpty())&(universities.isEmpty())))
			clearAllData();
		
		fillStudentsNames(numberOfStudents);
		fillLecturersNames(numberOfLecturers);
		fillUniversitiesNames(numberOfUniversities);
		
		fillUniversitiesAddresses();
		
		fillUniversitiesSpecialities();
		fillUniversitiesWithStudents();
		fillStudentsSpecialities();
		fillUniversitiesWithLecturers();
			
	}	
	
	private void fillStudentsNames(int numberOfStudents) {
		
		RandomNameAndSurnameFiller rnsf = new RandomNameAndSurnameFiller();
		ArrayList<String[]> studentsNamesAndSurnames = new ArrayList<String[]>();
		studentsNamesAndSurnames.addAll(rnsf.getNamesAndSurnames(numberOfStudents));
		
		for (String[] str : studentsNamesAndSurnames){
			Student student = new Student();
			student.setName(str[0]);
			student.setSurname(str[1]);
			students.add(student);
		}
		
	}
	
	private void fillLecturersNames(int numberOfLecturers) {
		
		RandomNameAndSurnameFiller rnsf = new RandomNameAndSurnameFiller();
		ArrayList<String[]> lecturersNamesAndSurnames = new ArrayList<String[]>();
		lecturersNamesAndSurnames.addAll(rnsf.getNamesAndSurnames(numberOfLecturers));
		
		for (String[] str : lecturersNamesAndSurnames){
			Lecturer lecturer = new Lecturer();
			lecturer.setName(str[0]);
			lecturer.setSurname(str[1]);
			lecturers.add(lecturer);
		}
		
	}
	
	private void fillUniversitiesNames(int numberOfUniversities){
		
		Universities dataUniversitiesNames = new Universities();
		ArrayList<String> universitiesNames = new ArrayList<String>(Arrays.asList(
				dataUniversitiesNames.getUniversities()));
		
		for (String str : universitiesNames){
			University university = new University();
			university.setName(str);				
			universities.add(university);
		}
				
	}
	
	private void fillUniversitiesAddresses(){
		for (University university : universities){
			Address address = new Address();
			switch (university.getName()){
				case "BNTU":{
					address.setPostcode("220013");
					address.setCity("Minsk");
					address.setStreet("Independence Av.");
					address.setHouse("65");
					university.setAddress(address);
					break;
				}
				case "BSEU":{
					address.setPostcode("220070");
					address.setCity("Minsk");
					address.setStreet("Partisan Av.");
					address.setHouse("26");
					university.setAddress(address);
					break;
				}
				case "BSU":{
					address.setPostcode("220030");
					address.setCity("Minsk");
					address.setStreet("Independence Av.");
					address.setHouse("4");
					university.setAddress(address);
					break;
				}
				case "BSUIR":{
					address.setPostcode("220013");
					address.setCity("Minsk");
					address.setStreet("P.Brovki st.");
					address.setHouse("4");
					university.setAddress(address);
					break;
				}
			}
		}
	}
	
	private void fillUniversitiesSpecialities(){
		
		for (University university : universities){
			switch (university.getName()){
				case "BNTU":{
					university.setSpecialities(new ArrayList<String>(Arrays.asList(
							"ENGINEER-BUILDER", "ENGINEER-TECHNOLOGIST", 
							"PETROLEUM-ENGINEER")));
					break;
				}
				case "BSEU":{
					university.setSpecialities(new ArrayList<String>(Arrays.asList(
							"ECONOMIST", "ACCOUNTANT")));
					break;
				}
				case "BSU":{
					university.setSpecialities(new ArrayList<String>(Arrays.asList(
							"ENGINEER-PROGRAMMER", "LOGIST")));
					break;
				}
				case "BSUIR":{
					university.setSpecialities(new ArrayList<String>(Arrays.asList(
							"ENGINEER-PROGRAMMER", "ECONOMIST")));
					break;
				}
			}
		}
		
	}
	
	private void fillUniversitiesWithStudents(){
		
		Random rand = new Random();
		int random = 0, size = universities.size();
				
		for (Student student : students){
			random = rand.nextInt(size);
			student.setUniversity(universities.get(random));
		}
		
		for (University university : universities){
			ArrayList<Student> studentsOfTheSameUniversity = new ArrayList<Student>();
			for (Student student : students){
				if (student.getUniversity().getName().equals(university.getName()))
					studentsOfTheSameUniversity.add(student);
			}
			university.setStudents(studentsOfTheSameUniversity);
		}
		
	}
	
	private void fillStudentsSpecialities(){
		
		Random rand = new Random();
		int random = 0, size = 0;
		String speciality;
				
		for (Student student : students){
			size = student.getUniversity().getSpecialities().size();
			random = rand.nextInt(size);
			speciality = student.getUniversity().getSpecialities().get(random);
			student.setSpeciality(speciality);
		}
		
	}

	private void fillUniversitiesWithLecturers(){
		
		Random rand = new Random();
		int random = 0, size = universities.size();
				
		for (Lecturer lecturer : lecturers){
			random = rand.nextInt(size);
			lecturer.setUniversity(universities.get(random));
		}
		
		for (University university : universities){
			ArrayList<Lecturer> lecturersOfTheSameUniversity = new ArrayList<Lecturer>();
			for (Lecturer lecturer : lecturers){
				if (lecturer.getUniversity().getName().equals(university.getName()))
					lecturersOfTheSameUniversity.add(lecturer);
			}
			university.setLecturers(lecturersOfTheSameUniversity);
		}
		
	}
	
	private void clearAllData(){
		
		students.clear();
		lecturers.clear();
		universities.clear();
			
	}

	public ArrayList<University> getUniversities() {
		return universities;
	}

	public ArrayList<Lecturer> getLecturers() {
		return lecturers;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
	
}
