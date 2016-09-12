package com.roxoft.hierarchy.models.human;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.institutions.School;

@XmlRootElement(name = "pupil")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pupil", propOrder = {})
public class Pupil extends Human {
	
	private School school;
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
