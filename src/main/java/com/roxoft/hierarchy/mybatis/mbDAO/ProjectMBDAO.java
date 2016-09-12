package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.projects.Project;

public class ProjectMBDAO {
	
	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.ProjectMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public ProjectMBDAO(final SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Project> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Project> projects = new ArrayList<Project>();
		
		try
		{
			final String query = NAMESPACE + ".getAllData";
			projects.addAll(session.selectList(query));
			return projects;
		} finally {
			session.close();
		}
		
	}
	
	public Project getProjectByCompanyId(int companyId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		Project project = new Project();
		
		try
		{
			final String query = NAMESPACE + ".getProjectByCompanyId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("company_id", companyId);
			project = session.selectOne(query, args);
			return project;
		} finally {
			session.close();
		}
		
	}
	
	public ArrayList<Project> getAllDataWithCompaniesNames() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Project> projects = new ArrayList<Project>();
		
		try
		{
			final String query = NAMESPACE + ".getAllDataWithCompaniesNames";
			projects.addAll(session.selectList(query));
			return projects;
		} finally {
			session.close();
		}
		
	}

}
