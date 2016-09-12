package com.roxoft.hierarchy.dao.systems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.roxoft.hierarchy.dao.IDAOAddress;
import com.roxoft.hierarchy.dao.JeneralSystemsDAO;
import com.roxoft.hierarchy.models.address.Address;

public class AddressDAO extends JeneralSystemsDAO implements IDAOAddress {
	
	public AddressDAO(Connection connection) {
		this.connection = connection;
	}

	public void record(Address address) {
		
		final String SQL_INSERT = 
				"INSERT INTO addresses(postcode, city, street, house) "
				+ "VALUES(?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);
		
		try {
			
			preparedStatement.setString(1, address.getPostcode());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getHouse());	
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}		
		
	}

	public void delete(Address address) {
		
		final String SQL_DELETE = 
				"DELETE FROM addresses WHERE postcode = ? AND city = ? "
				+ "AND street = ? AND house = ?";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_DELETE);
		
		try {
			
			preparedStatement.setString(1, address.getPostcode());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getHouse());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	public void deleteById(int id) {
		
		final String SQL_DELETE = 
				"DELETE FROM addresses WHERE address_id = ?";
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

	public Address findById(int id) {
		
		Address address = new Address();
		final String SQL_SELECT = "SELECT postcode, city, street, house "
				+ "FROM addresses WHERE address_id = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				address.setPostcode(resultSet.getString("postcode"));
				address.setCity(resultSet.getString("city"));
				address.setStreet(resultSet.getString("street"));
				address.setHouse(resultSet.getString("house"));		
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
		
		return address;
		
	}

	public ArrayList<Address> getAllData() {
		
		ArrayList<Address> addresses = new ArrayList<Address>();
		
		final String SQL_SELECT = "SELECT postcode, city, street, house"
				+ " FROM addresses WHERE address_id = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = getPreparedStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Address address = new Address();
				address.setPostcode(resultSet.getString("postcode"));
				address.setCity(resultSet.getString("city"));
				address.setStreet(resultSet.getString("street"));
				address.setHouse(resultSet.getString("house"));
				addresses.add(address);
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
		
		return addresses;
		
	}

	public int findId(Address address) {
		
		int id = 0;
		final String SQL_SELECT = "SELECT address_id FROM addresses "
				+ "WHERE postcode = ? AND city = ? AND street = ? AND house = ?";
		
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement.setString(1, address.getPostcode());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getHouse());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getInt("address_id");
										
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
	
}
