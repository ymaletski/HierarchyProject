package com.roxoft.hierarchy.models.institutions;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.roxoft.hierarchy.models.projects.Project;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "company", propOrder = {"projects"})
public class Company extends Institute {
	
	@XmlElement(name  ="project", required = true)
	@XmlElementWrapper (name = "projects", required = true)
	private ArrayList<Project> projects;

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

}
