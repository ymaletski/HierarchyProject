
package com.roxoft.hierarchy.models.systems;

import java.util.ArrayList;
import java.util.Random;

import com.roxoft.hierarchy.models.address.Address;
import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.School;
import com.roxoft.hierarchy.randomFillers.RandomNameAndSurnameFiller;
import com.roxoft.hierarchy.randomFillers.RandomSchoolFiller;

public class SchoolSystem {
	
	private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<School> schools = new ArrayList<School>();
	
	public void fillSchoolSystem(int numberOfSchools, 
			int numberOfTeachers, int numberOfPupils){
		
		if (!((pupils.isEmpty())&(teachers.isEmpty())&(schools.isEmpty())))
			clearAllData();
		
		fillPupilsNames(numberOfPupils);
		fillTeachersNames(numberOfTeachers);
		fillSchoolsNames(numberOfSchools);
		
		fillSchoolsAddresses();
		
		fillSchoolsWithPupils();
		fillSchoolsWithTeachers();		
		
	}
	
	private void fillPupilsNames(int numberOfPupils) {
		
		RandomNameAndSurnameFiller rnsf = new RandomNameAndSurnameFiller();
		ArrayList<String[]> pupilsNamesAndSurnames = new ArrayList<String[]>();
		pupilsNamesAndSurnames.addAll(rnsf.getNamesAndSurnames(numberOfPupils));
		
		for (String[] str : pupilsNamesAndSurnames){
			Pupil pupil = new Pupil();
			pupil.setName(str[0]);
			pupil.setSurname(str[1]);
			pupils.add(pupil);
		}
		
	}
	
	private void fillTeachersNames(int numberOfTeachers) {
		
		RandomNameAndSurnameFiller rnsf = new RandomNameAndSurnameFiller();
		ArrayList<String[]> teachersNamesAndSurnames = new ArrayList<String[]>();
		teachersNamesAndSurnames.addAll(rnsf.getNamesAndSurnames(numberOfTeachers));
		
		for (String[] str : teachersNamesAndSurnames){
			Teacher teacher = new Teacher();
			teacher.setName(str[0]);
			teacher.setSurname(str[1]);
			teachers.add(teacher);
		}
		
	}
	
	private void fillSchoolsNames(int numberOfSchools){
		
		RandomSchoolFiller rsf = new RandomSchoolFiller();
		ArrayList<String> schoolsNames = new ArrayList<String>();
		schoolsNames.addAll(rsf.getNames(numberOfSchools));
		
		for (String str : schoolsNames){
			School school = new School();
			school.setName(str);
			schools.add(school);
		}
				
	}
	
	private void fillSchoolsAddresses(){
		for (School school : schools){
			Address address = new Address();
			switch (school.getName()){
				case "SCHOOL_N1":{
					address.setPostcode("220039");
					address.setCity("Minsk");
					address.setStreet("Chkalov st.");
					address.setHouse("3A");
					school.setAddress(address);
					break;
				}
				case "SCHOOL_N6":{
					address.setPostcode("220019");
					address.setCity("Minsk");
					address.setStreet("Jankowski st.");
					address.setHouse("23");
					school.setAddress(address);
					break;
				}
				case "SCHOOL_N4":{
					address.setPostcode("220030");
					address.setCity("Minsk");
					address.setStreet("Red Army st.");
					address.setHouse("11");
					school.setAddress(address);
					break;
				}
				case "SCHOOL_N21":{
					address.setPostcode("220029");
					address.setCity("Minsk");
					address.setStreet("Kuibyshev st.");
					address.setHouse("19");
					school.setAddress(address);
					break;
				}
				case "SCHOOL_N50":{
					address.setPostcode("220029");
					address.setCity("Minsk");
					address.setStreet("Kiselev st.");
					address.setHouse("9");
					school.setAddress(address);
					break;
				}
			}
		}
	}
	
	private void fillSchoolsWithPupils(){
		
		Random rand = new Random();
		int random = 0, size = schools.size();
				
		for (Pupil pupil : pupils){
			random = rand.nextInt(size);
			pupil.setSchool(schools.get(random));
		}
				
		for (School school : schools){
			ArrayList<Pupil> pupilsOfTheSameSchool = new ArrayList<Pupil>();
			for (Pupil pupil : pupils){
				if (pupil.getSchool().getName().equals(school.getName()))
					pupilsOfTheSameSchool.add(pupil);
			}
			school.setPupils(pupilsOfTheSameSchool);
		}
		
	}
	
	private void fillSchoolsWithTeachers(){
		
		Random rand = new Random();
		int random = 0, size = schools.size();
				
		for (Teacher teacher : teachers){
			random = rand.nextInt(size);
			teacher.setSchool(schools.get(random));
		}
		
		for (School school : schools){
			ArrayList<Teacher> teachersOfTheSameSchool = new ArrayList<Teacher>();
			for (Teacher teacher : teachers){
				if (teacher.getSchool().getName().equals(school.getName()))
					teachersOfTheSameSchool.add(teacher);
			}
			school.setTeachers(teachersOfTheSameSchool);
		}
		
	}
	
	private void clearAllData(){
		
		pupils.clear();
		teachers.clear();
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
