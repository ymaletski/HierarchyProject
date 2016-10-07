package com.roxoft.hierarchy.main;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.roxoft.hierarchy.dao.mybatis.SessionFactory;
import com.roxoft.hierarchy.httpClientExample.HttpClientExample;
import com.roxoft.hierarchy.instituteHandler.InstituteHandlerStAX;
import com.roxoft.hierarchy.instituteHandlerJackson.InstituteHandlerJackson;
import com.roxoft.hierarchy.instituteHandlerJaxb.InstituteHandlerJaxb;
import com.roxoft.hierarchy.io.printers.CompanySystemPrinter;
import com.roxoft.hierarchy.io.printers.SchoolSystemPrinter;
import com.roxoft.hierarchy.io.printers.UniversitySystemPrinter;
import com.roxoft.hierarchy.logics.FromSchoolToCompany;
import com.roxoft.hierarchy.logics.TeamForProject;
import com.roxoft.hierarchy.models.systems.CompanySystem;
import com.roxoft.hierarchy.models.systems.SchoolSystem;
import com.roxoft.hierarchy.models.systems.UniversitySystem;
import com.roxoft.hierarchy.services.data.DataService;
import com.roxoft.hierarchy.services.systems.CompanySystemService;
import com.roxoft.hierarchy.services.systems.MyBatisCompanySystemService;
import com.roxoft.hierarchy.services.systems.MyBatisSchoolSystemService;
import com.roxoft.hierarchy.services.systems.MyBatisUniversitySystemService;
import com.roxoft.hierarchy.services.systems.SchoolSystemService;
import com.roxoft.hierarchy.services.systems.UniversitySystemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main { 
	
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
		
	public static void main(String[] args){		
						
		SchoolSystem ss = new SchoolSystem();
		ss.fillSchoolSystem(4, 8, 24);
		SchoolSystemPrinter ssp = new SchoolSystemPrinter();
		//ssp.purePrint(ss.getPupils(), ss.getTeachers(), ss.getSchools());
		//ssp.printSchoolSystem(ss.getSchools());
		
		UniversitySystem us = new UniversitySystem();
		us.fillUniversitySystem(4, 12, 36);
		UniversitySystemPrinter usp = new UniversitySystemPrinter();
		//usp.purePrint(us.getStudents(), us.getLecturers(), us.getUniversities());
		//usp.printUniversitySystem(us.getUniversities());
		
		CompanySystem cs = new CompanySystem();
		cs.fillCompanySystem(3, 6);
		CompanySystemPrinter csp = new CompanySystemPrinter();
		//csp.purePrint(cs.getProjects(), cs.getCompanies());
		//csp.printCompanySystem(cs.getCompanies());
		
		
		DataService dataService = new DataService();
		dataService.fillAllData();
		dataService.printData();
		
		
		SchoolSystemService sss = new SchoolSystemService();
		sss.recordSchoolSystem(ss.getSchools(), ss.getTeachers(), ss.getPupils());
		LOGGER.info("\nOutput school system from DB");
		ssp.printSchoolSystem(sss.createSchoolSystem());
				
		UniversitySystemService uss = new UniversitySystemService();
		uss.recordUniversitySystem(us.getUniversities(), us.getLecturers(), us.getStudents());
		LOGGER.info("\nOutput university system from DB");
		usp.printUniversitySystem(uss.createUniversitySystem());
		
		CompanySystemService css = new CompanySystemService();
		css.recordCompanySystem(cs.getCompanies(),cs.getProjects());
		LOGGER.info("\nOutput company system from DB");
		csp.printCompanySystem(css.createCompanySystem());
		
		/*
		LOGGER.info("\nMyBatis");
		LOGGER.info("====================");
		
		MyBatisSchoolSystemService mbsss = new MyBatisSchoolSystemService();		
		ssp.printSchoolSystem(mbsss.createSchoolSystem(SessionFactory.getInstance().getSqlSessionFactory()));
		ssp.printSchoolSystem(mbsss.createSchoolSystemAlternatively(
				SessionFactory.getInstance().getSqlSessionFactory()));
		
		MyBatisUniversitySystemService mbuss = new MyBatisUniversitySystemService();
		usp.printUniversitySystem(mbuss.createUniversitySystem(SessionFactory.getInstance().getSqlSessionFactory()));
		usp.printUniversitySystem(mbuss.createUniversitySystemAlternatively(
				SessionFactory.getInstance().getSqlSessionFactory()));
		
		MyBatisCompanySystemService mbcss = new MyBatisCompanySystemService();
		csp.printCompanySystem(mbcss.createCompanySystem(SessionFactory.getInstance().getSqlSessionFactory()));
		csp.printCompanySystem(mbcss.createCompanySystemAlternatively(
				SessionFactory.getInstance().getSqlSessionFactory()));
		
				
		FromSchoolToCompany fstc = new FromSchoolToCompany();
		fstc.showTheWayFromSchoolToCompany(cs.getCompanies(), us.getUniversities());
		TeamForProject tfp = new TeamForProject();
		tfp.SelectTeamForProject(cs.getCompanies(), us.getStudents());
		*/
		/*
		LOGGER.info("\nStAX");
		LOGGER.info("====================");
		
		InstituteHandlerStAX instituteHandlerStAX = new InstituteHandlerStAX();
		instituteHandlerStAX.fillInstituteSystem();
				
		SchoolSystemPrinter ssp3 = new SchoolSystemPrinter();
		ssp3.purePrint(instituteHandlerStAX.getPupils(), instituteHandlerStAX.getTeachers(),
		instituteHandlerStAX.getSchools());
		ssp3.printSchoolSystem(instituteHandlerStAX.getSchools());
		
		UniversitySystemPrinter usp3 = new UniversitySystemPrinter();
		usp3.purePrint(instituteHandlerStAX.getStudents(), instituteHandlerStAX.getLecturers(),
		instituteHandlerStAX.getUniversities());
		usp3.printUniversitySystem(instituteHandlerStAX.getUniversities());
		
		CompanySystemPrinter csp3 = new CompanySystemPrinter();
		csp3.purePrint(instituteHandlerStAX.getProjects(), instituteHandlerStAX.getCompanies());
		csp3.printCompanySystem(instituteHandlerStAX.getCompanies());
		*/
		/*
		LOGGER.info("\nJaxb");
		LOGGER.info("====================");
				
		try {
			JAXBContext jc = JAXBContext.newInstance(InstituteHandlerJaxb.class);
			Unmarshaller unm = jc.createUnmarshaller();
			FileReader reader = new FileReader("src\\main\\resources\\DataFile.xml");
			InstituteHandlerJaxb instituteHandlerJaxb = (InstituteHandlerJaxb) unm.unmarshal(reader);
						
			SchoolSystemPrinter ssp1 = new SchoolSystemPrinter();
			ssp1.printSchoolSystem(instituteHandlerJaxb.getSchools());
						
			UniversitySystemPrinter usp1 = new UniversitySystemPrinter();
			usp1.printUniversitySystem(instituteHandlerJaxb.getUniversities());
						
			CompanySystemPrinter csp1 = new CompanySystemPrinter();
			csp1.printCompanySystem(instituteHandlerJaxb.getCompanies());
			
		} catch (JAXBException e) {
			LOGGER.error("JAXBException in Main: ", e);
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException in Main: ", e);
		}
		*/
		/*
		LOGGER.info("\nJackson");
		LOGGER.info("====================");
			
		//read json file data to String
		byte[] jsonData;
			
		try {
			jsonData = Files.readAllBytes(Paths.get("src\\main\\resources\\DataFile.json"));
				
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
					
			//convert json string to object
			InstituteHandlerJackson instituteHandlerJackson = objectMapper.readValue(jsonData, 
					InstituteHandlerJackson.class);
				
			SchoolSystemPrinter ssp2 = new SchoolSystemPrinter();
			ssp2.printSchoolSystem(instituteHandlerJackson.getSchools());
				
			UniversitySystemPrinter usp2 = new UniversitySystemPrinter();
			usp2.printUniversitySystem(instituteHandlerJackson.getUniversities());
				
			CompanySystemPrinter csp2 = new CompanySystemPrinter();
			csp2.printCompanySystem(instituteHandlerJackson.getCompanies());
				
		} catch (IOException e) {
			LOGGER.error("IOException in Main: ", e);
		}
		*/
		/*
		HttpClientExample httpExample = new HttpClientExample();
		httpExample.getListOfCountries();
		httpExample.getCountry("ZW");		
		httpExample.sendPost();
		*/
	}
	
}
