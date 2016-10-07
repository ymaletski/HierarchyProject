package com.roxoft.hierarchy.services.systems;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.dao.mybatis.mappers.CompanyMapper;
import com.roxoft.hierarchy.dao.mybatis.mappers.ProjectMapper;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.CompanyMBDAO;
import com.roxoft.hierarchy.dao.mybatis.mbDAO.ProjectMBDAO;
import com.roxoft.hierarchy.models.institutions.Company;
import com.roxoft.hierarchy.models.projects.Project;

public class MyBatisCompanySystemService {
	
	public ArrayList<Company> createCompanySystem(SqlSessionFactory sqlSessionFactory){
		ArrayList<Company> companies = new ArrayList<Company>();
		ArrayList<Project> projects = new ArrayList<Project>();
		SqlSession session = sqlSessionFactory.openSession();
		try {	
			ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
			projects.addAll(projectMapper.getAllDataWithCompaniesNames());					
			CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
			companies.addAll(companyMapper.getAllData());		
		} finally {
		  session.close();
		}
		return companies;
	}
	
	public ArrayList<Company> createCompanySystemAlternatively(SqlSessionFactory sqlSessionFactory){
		ArrayList<Company> companies = new ArrayList<Company>();
		ArrayList<Project> projects = new ArrayList<Project>();
		ProjectMBDAO projectMBDAO = new ProjectMBDAO(sqlSessionFactory);
		projects.addAll(projectMBDAO.getAllDataWithCompaniesNames());			
		CompanyMBDAO companyMBDAO = new CompanyMBDAO(sqlSessionFactory);
		companies.addAll(companyMBDAO.getAllData());	
		return companies;
	}

}
