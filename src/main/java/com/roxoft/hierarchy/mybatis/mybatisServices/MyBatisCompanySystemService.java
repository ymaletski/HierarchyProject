package com.roxoft.hierarchy.mybatis.mybatisServices;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;
import com.roxoft.hierarchy.mybatis.mappers.CompanyMapper;
import com.roxoft.hierarchy.mybatis.mappers.ProjectMapper;
import com.roxoft.hierarchy.mybatis.mbDAO.CompanyMBDAO;
import com.roxoft.hierarchy.mybatis.mbDAO.ProjectMBDAO;

public class MyBatisCompanySystemService {
	
	private ArrayList<Company> companies = new ArrayList<Company>();
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	public void createCompanySystem(SqlSessionFactory sqlSessionFactory){
		
		SqlSession session = sqlSessionFactory.openSession();
		
		clearAllData();
		
		try {
			
			ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
			projects.addAll(projectMapper.getAllDataWithCompaniesNames());	
						
			CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
			companies.addAll(companyMapper.getAllData());	
			
		} finally {
		  session.close();
		}
		
	}
	
	public void createCompanySystemAlternatively(SqlSessionFactory sqlSessionFactory){
		
		clearAllData();
		
		ProjectMBDAO projectMBDAO = new ProjectMBDAO(sqlSessionFactory);
		projects.addAll(projectMBDAO.getAllDataWithCompaniesNames());
			
		CompanyMBDAO companyMBDAO = new CompanyMBDAO(sqlSessionFactory);
		companies.addAll(companyMBDAO.getAllData());
		
	}
	
	private void clearAllData(){
		
		if (!(companies.isEmpty()))
			companies.clear();
		if (!(projects.isEmpty()))
			projects.clear();
			
	}
	
	public ArrayList<Company> getCompanies() {
		return companies;
	}
	public ArrayList<Project> getProjects() {
		return projects;
	}

}
