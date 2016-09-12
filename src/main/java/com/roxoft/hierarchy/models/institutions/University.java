package com.roxoft.hierarchy.models.institutions;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.human.Lecturer;
import com.roxoft.hierarchy.models.human.Student;

@XmlRootElement(name="university")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "university", propOrder = {"specialities", "students", "lecturers"})
public class University extends Institute {
	
	@XmlElement(name="student", required = true)
	@XmlElementWrapper (name = "students", required = true)
	private ArrayList<Student> students;
	
	@XmlElement(name="lecturer", required = true)
	@XmlElementWrapper (name = "lecturers", required = true)
	private ArrayList<Lecturer> lecturers;
	
	@XmlElement(name="speciality", required = true)
	@XmlElementWrapper (name = "specialities", required = true)
	private ArrayList<String> specialities;
			
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Lecturer> getLecturers() {
		return lecturers;
	}

	public void setLecturers(ArrayList<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	public ArrayList<String> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(ArrayList<String> specialities) {
		this.specialities = specialities;
	}
	
}
