package com.roxoft.hierarchy.models.human;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.institutions.University;

@XmlRootElement(name = "lecturer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lecturer", propOrder = {})
public class Lecturer extends Human {
	
	private University university;
		
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
