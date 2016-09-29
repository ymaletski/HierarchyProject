package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.roxoft.hierarchy.dao.IDAOCompany;
import com.roxoft.hierarchy.dao.GeneralSystemsDAO;
import com.roxoft.hierarchy.models.institutions.Company;

public class CompanyDAO extends GeneralSystemsDAO implements IDAOCompany {

	public CompanyDAO(Connection connection) {
		this.connection = connection;
	}
		
	public void record(Company company, int addressId) {
		
		final String SQL_INSERT = 
				"INSERT INTO companies(name, address_id) VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);
		
		try {
			
			preparedStatement.setString(1, company.getName());
			preparedStatement.setInt(2, addressId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public void deleteByName(Company company) {
		
		final String SQL_DELETE = 
				"DELETE FROM companies WHERE name = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setString(1, company.getName());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public void deleteById(int id) {
		
		final String SQL_DELETE = 
				"DELETE FROM companies WHERE company_id = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public int findAddressIdByName(Company company) {
		
		int addressId = 0;
		final String SQL_SELECT = "SELECT address_id FROM companies "
				+ "WHERE name = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setString(1, company.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				addressId = resultSet.getInt("address_id");
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return addressId;
		
	}

	public int findIdByName(Company company) {
		
		int id = 0;
		final String SQL_SELECT = "SELECT company_id FROM companies "
				+ "WHERE name = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setString(1, company.getName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getInt("company_id");
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return id;
		
	}

	public Company findById(int id) {
		
		Company company = new Company();
		final String SQL_SELECT = "SELECT name FROM companies "
				+ "WHERE company_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				company.setName(resultSet.getString("name"));
										
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return company;
		
	}

	public ArrayList<Company> getAllData() {
		
		ArrayList<Company> companies = new ArrayList<Company>();
		
		final String SQL_SELECT = "SELECT name FROM companies";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setName(resultSet.getString("name"));
				companies.add(company);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) 
				try { 
					resultSet.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closePreparedStatement(preparedStatement);
		}
		
		return companies;
		
	}

}
