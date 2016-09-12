package com.roxoft.hierarchy.models.institutions;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.human.Pupil;
import com.roxoft.hierarchy.models.human.Teacher;

@XmlRootElement(name="school")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "school", propOrder = {"pupils", "teachers"})
public class School extends Institute {
	
	@XmlElement(name="pupil", required = true)
	@XmlElementWrapper (name = "pupils", required = true)
	private ArrayList<Pupil> pupils;
	
	@XmlElement(name="teacher", required = true)
	@XmlElementWrapper (name = "teachers", required = true)
	private ArrayList<Teacher> teachers;
	
	public ArrayList<Pupil> getPupils() {
		return pupils;
	}
	public void setPupils(ArrayList<Pupil> pupils) {
		this.pupils = pupils;
	}
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}
		
}
