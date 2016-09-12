package com.roxoft.hierarchy.instituteHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.roxoft.hierarchy.models.address.Address;
import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Student;
import com.roxoft.hierarchy.models.human.Teacher;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.institutions.School;
import com.roxoft.hierarchy.models.institutions.University;
import com.roxoft.hierarchy.models.projects.Project;

public class InstituteHandlerStAX implements XMLStreamConstants {
	
	private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<School> schools = new ArrayList<School>();
	
	private ArrayList<University> universities = new ArrayList<University>();
	private ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	private ArrayList<Student> students = new ArrayList<Student>();
	
	private ArrayList<Company> companies = new ArrayList<Company>();
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	Address address = new Address();
	
	private School school;
	private Pupil pupil;
	private Teacher teacher;
	private ArrayList<Pupil> pupilsOfTheSameSchool;
	private ArrayList<Teacher> teachersOfTheSameSchool;
	
	private University university;
	private Student student;
	private Lecturer lecturer;
	private ArrayList<Student> studentsOfTheSameUniversity;
	private ArrayList<Lecturer> lecturersOfTheSameUniversity;
	private ArrayList<String> universitySpecialities;
	
	private Project project;
	private Company company;
	private ArrayList<Project> projectsOfTheSameCompany;
	private ArrayList<String> projectSpecialities;
	
	private boolean inStreet;
	private boolean inHouse;
	private boolean inCity;
	private boolean inPostcode;
	
	private boolean inSchoolName;
	private boolean inSchools;
	
	private boolean inUniversities;
	private boolean inUniversityName;
	private boolean inUniversitySpeciality;
	
	private boolean inCompanies;
	private boolean inCompanyName;
	private boolean inProjectSpeciality;
	
	public void fillInstituteSystem(){
		
		XMLInputFactory  inputFactory;
		FileInputStream inputStream = null;
		XMLStreamReader streamReader = null; 
		
		inputFactory = XMLInputFactory.newInstance();
		
		try {
			inputStream = new FileInputStream("src\\main\\resources\\DataFile.xml");
			streamReader = inputFactory.createXMLStreamReader(inputStream);
			
			int event;
			while (streamReader.hasNext()) {
				event = streamReader.next();
				switch (event){
					case START_ELEMENT:
						processElement(streamReader);
						break;
					case CHARACTERS:
						processText(streamReader.getText());
						break;
					case END_ELEMENT:
						finishElement(streamReader.getLocalName());
				}
			}	
		} catch (FileNotFoundException e) {
			System.out.println("File DataFile.xml not found "+e);
		} catch (XMLStreamException e) {
			System.out.println("StAX parsing error "+e.getMessage());
		}  finally {
			try {
				if (inputStream != null){
					inputStream.close();
				} 
			} catch (IOException e) {
				System.out.println("Impossible close file DataFile.xml "+e);
			}
			
		}
		
	}
	
	private void processElement (XMLStreamReader element) throws XMLStreamException{
		
		switch (element.getLocalName()){
		
			case "schools":
				inSchools = true;
				break;
				
				case "school":
					school = new School();
					pupilsOfTheSameSchool = new ArrayList<Pupil>();
					teachersOfTheSameSchool = new ArrayList<Teacher>();
					break;
										
					case "pupil":
						pupil = new Pupil();
						pupil.setName(element.getAttributeValue(0));
						pupil.setSurname(element.getAttributeValue(1));
						pupil.setSchool(school);
						break;
						
					case "teacher":
						teacher = new Teacher();
						teacher.setName(element.getAttributeValue(0));
						teacher.setSurname(element.getAttributeValue(1));
						teacher.setSchool(school);
						break;
				
			case "universities":
				inUniversities = true;
				break;
				
				case "university":{
					university = new University();
					studentsOfTheSameUniversity = new ArrayList<Student>();
					lecturersOfTheSameUniversity = new ArrayList<Lecturer>();
					universitySpecialities = new ArrayList<String>();
					break;
				}
					
					case "student":
						student = new Student();
						student.setName(element.getAttributeValue(0));
						student.setSurname(element.getAttributeValue(1));
						student.setSpeciality(element.getAttributeValue(2));
						student.setUniversity(university);
						break;
						
					case "lecturer":
						lecturer = new Lecturer();
						lecturer.setName(element.getAttributeValue(0));
						lecturer.setSurname(element.getAttributeValue(1));
						lecturer.setUniversity(university);
						break;				
				
			case "companies":
				inCompanies = true;
				break;
							
				case "company": 
					company = new Company();
					break;
					
					case "projects": 
						projectsOfTheSameCompany = new ArrayList<Project>(); 
						break;
						
						case "project":
							project = new Project();
							project.setName(element.getAttributeValue(0));
							projectSpecialities = new ArrayList<String>();
							break;
			
			case "name":
				if (inSchools){
					inSchoolName = true;
				} else if (inUniversities){
					inUniversityName = true;
				} else if (inCompanies){
					inCompanyName = true;
				}
				break;
						
			case "speciality":
				if (inUniversities){
					inUniversitySpeciality = true;
				} else if (inCompanies){
					inProjectSpeciality = true;
				}
				break;
					
			case "address":
				address = new Address();
				break;
			
				case "street":
					inStreet = true;
					break;
				
				case "house":
					inHouse = true;
					break;
					
				case "city":
					inCity = true;
					break;
					
				case "postcode":
					inPostcode = true;
					break;
		}
		
	}
	
	private void processText (String text){
		
		if (inSchoolName){
			school.setName(text);
			inSchoolName = false;
		} else if (inUniversityName){
			university.setName(text);
			inUniversityName = false;
		} else if (inCompanyName){
			company.setName(text);
			inCompanyName = false;
		} else if (inStreet){
			address.setStreet(text);
			inStreet = false;
		} else if (inHouse){
			address.setHouse(text);
			inHouse = false;
		} else if (inCity){
			address.setCity(text);
			inCity = false;
		} else if (inPostcode){
			address.setPostcode(text);
			inPostcode = false;
		} else if (inUniversitySpeciality){
			universitySpecialities.add(text);
		} else if (inProjectSpeciality){
			projectSpecialities.add(text);
		}
		
	}
	
	private void finishElement(String name){
		
		switch (name){
		
			case "pupil":
				pupils.add(pupil);
				pupilsOfTheSameSchool.add(pupil);
				pupil = null;
				break;
				
			case "teacher":
				teachers.add(teacher);
				teachersOfTheSameSchool.add(teacher);
				teacher = null;
				break;
				
			case "address":
				if (inSchools){
				school.setAddress(address);
				address = null;
				} else if (inUniversities){
					university.setAddress(address);
					address = null;
				} else if (inCompanies){
					company.setAddress(address);
					address = null;
				}
				break;
				
			case "school":
				school.setPupils(pupilsOfTheSameSchool);
				school.setTeachers(teachersOfTheSameSchool);
				pupilsOfTheSameSchool = null;
				teachersOfTheSameSchool = null;
				schools.add(school);
				school = null;
				break;
				
			case "schools":
				inSchools = false;
				break;
			
			case "student":
				students.add(student);
				studentsOfTheSameUniversity.add(student);
				student = null;
				break;
				
			case "lecturer":
				lecturers.add(lecturer);
				lecturersOfTheSameUniversity.add(lecturer);
				lecturer = null;
				break;
				
			case "speciality":
				if (inUniversities){
					university.setSpecialities(universitySpecialities);
					inUniversitySpeciality = false;
				} else if (inCompanies){
					project.setSpecialities(projectSpecialities);
					inProjectSpeciality = false;	
				}	
				break;
				
			case "university":
				university.setStudents(studentsOfTheSameUniversity);
				university.setLecturers(lecturersOfTheSameUniversity);
				studentsOfTheSameUniversity = null;
				lecturersOfTheSameUniversity = null;
				universitySpecialities = null;
				universities.add(university);
				university = null;
				break;
				
			case "universities":
				inUniversities = false;
				break;
				
			case "project":
				project.setCompany(company);
				projectsOfTheSameCompany.add(project);
				projects.add(project);
				projectSpecialities = null;
				break;
				
			case "projects":
				company.setProjects(projectsOfTheSameCompany);
				projectsOfTheSameCompany = null;
				break;
				
			case "company":
				companies.add(company);
				company = null;
				break;
				
			case "companies":
				inCompanies = false;
				break;	
		}	
		
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
	
	public ArrayList<University> getUniversities() {
		return universities;
	}
	
	public ArrayList<Lecturer> getLecturers() {
		return lecturers;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public ArrayList<Company> getCompanies() {
		return companies;
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
		
}
