package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.hierarchy.dao.IDAOProject;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.projects.Project;

public class ProjectDAO extends GeneralSystemsDAO implements IDAOProject {
	
	private static final Logger LOGGER = LogManager.getLogger(ProjectDAO.class);

	public ProjectDAO(Connection connection) {
		this.connection = connection;
	}
		
	public void record(Project project, int companyId) {	
		final String SQL_INSERT = 
				"INSERT INTO projects(name, company_id) VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);		
		try {		
			preparedStatement.setString(1, project.getName());
			preparedStatement.setInt(2, companyId);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.record(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void recordProjectsAndSpecialities(int projectId, int specialityId) {		
		final String SQL_INSERT = 
				"INSERT INTO projects_has_specialities"
				+ "(project_id, speciality_id) VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);	
		try {
				preparedStatement.setInt(1, projectId);
				preparedStatement.setInt(2, specialityId);
				preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.recordProjectsAndSpecialities(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}		
	}

	public void deleteByName(Project project) {		
		final String SQL_DELETE = 
				"DELETE FROM projects WHERE name = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);	
		try {			
			preparedStatement.setString(1, project.getName());
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.deleteByName(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	public void deleteById(int id) {		
		final String SQL_DELETE = 
				"DELETE FROM projects WHERE project_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		try {	
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();		
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.deleteById(): ", e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	public int findIdByName(Project project) {		
		int id = 0;
		final String SQL_SELECT = "SELECT project_id FROM projects "
				+ "WHERE name = ?";	
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;	
		try {	
			preparedStatement.setString(1, project.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getInt("project_id");				
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.findIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.findIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}
		return id;
	}

	public Project findById(int id) {		
		Project project = new Project();
		final String SQL_SELECT = "SELECT name FROM projects "
				+ "WHERE project_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		try {
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				project.setName(resultSet.getString("name"));								
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.findById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.findById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}
		return project;
	}

	public ArrayList<Project> getAllData() {	
		ArrayList<Project> projects = new ArrayList<Project>();
		final String SQL_SELECT = "SELECT name FROM projects";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {	
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				projects.add(project);
            }	
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.getAllData(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.getAllData(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}
		return projects;
	}

	public ArrayList<Project> findAllByCompanyId(int companyId) {	
		ArrayList<Project> projects = new ArrayList<Project>();
		final String SQL_SELECT = "SELECT name FROM projects "
				+ "WHERE company_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setInt(1, companyId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				projects.add(project);
            }						
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.findAllByCompanyId(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.findAllByCompanyId(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}		
		return projects;		
	}

	public ArrayList<Integer> findSpecialitiesById(int id) {		
		ArrayList<Integer> specialities = new ArrayList<Integer>();
		final String SQL_SELECT = "SELECT speciality_id FROM projects_has_specialities "
				+ "WHERE project_id = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;	
		try {		
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				specialities.add(resultSet.getInt("speciality_id"));
			}										
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.findSpecialitiesById(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.findSpecialitiesById(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}				
		return specialities;		
	}

	public int findCompanyIdByName(Project project) {	
		int companyId = 0;
		final String SQL_SELECT = "SELECT company_id FROM projects "
				+ "WHERE name = ?";		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;		
		try {			
			preparedStatement.setString(1, project.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				companyId = resultSet.getInt("company_id");										
		} catch (SQLException e) {
			LOGGER.error("SQLException in ProjectDAO.findCompanyIdByName(): ", e);
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					LOGGER.error("SQLException in ProjectDAO.findCompanyIdByName(): ", e);
				}
			closePreparedStatement(preparedStatement);
		}	
		return companyId;	
	}

}
