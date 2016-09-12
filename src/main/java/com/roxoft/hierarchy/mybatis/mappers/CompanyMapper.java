package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import com.roxoft.hierarchy.models.institutions.Company;

public interface CompanyMapper {
	
	ArrayList<Company> getAllData();
	
	Company getCompanyById(int companyId);
	
	Company getPureCompanyById(int companyId);

}
