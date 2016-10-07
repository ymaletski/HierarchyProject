package com.roxoft.hierarchy.dao;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.Company;

public interface IDAOCompany {
	
	void record(Company company, int addressId);
	void deleteByName(Company company);
	void deleteById(int id);
	int findAddressIdByName(Company company);
	int findIdByName(Company company);
	Company findById(int id);
	ArrayList<Company> getAllData();
	
}
