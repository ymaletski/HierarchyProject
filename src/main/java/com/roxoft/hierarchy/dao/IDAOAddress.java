package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.address.Address;

public interface IDAOAddress {
		
	void record(Address address);
	
	void delete(Address address);
	void deleteById(int id);
	
	Address findById(int id);
	
	int findId(Address address);
	
	ArrayList<Address> getAllData();

}
