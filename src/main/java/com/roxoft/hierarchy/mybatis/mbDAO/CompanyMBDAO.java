package com.roxoft.hierarchy.mybatis.mbDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.roxoft.hierarchy.models.institutions.Company;

public class CompanyMBDAO {

	protected static final String NAMESPACE = "com.roxoft.hierarchy.mybatis.mappers.CompanyMapper";
	protected SqlSessionFactory sqlSessionFactory;

	public CompanyMBDAO(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public ArrayList<Company> getAllData() {
		
		final SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Company> companies = new ArrayList<Company>();
		
		try {
			final String query = NAMESPACE + ".getAllData";
			companies.addAll(session.selectList(query));
			return companies;
		} finally {
			session.close();
		}
		
	}
	
	public Company getCompanyById(int companyId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		Company company = new Company();
		
		try {
			final String query = NAMESPACE + ".getCompanyById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("company_id", companyId);
			company = session.selectOne(query, args);
			return company;
		} finally {
			session.close();
		}
		
	}
	
	public Company getPureCompanyById(int companyId){
		
		final SqlSession session = sqlSessionFactory.openSession();
		Company company = new Company();
		
		try {
			final String query = NAMESPACE + ".getPureCompanyById";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("company_id", companyId);
			company = session.selectOne(query, args);
			return company;
		} finally {
			session.close();
		}
		
	}
	
}
