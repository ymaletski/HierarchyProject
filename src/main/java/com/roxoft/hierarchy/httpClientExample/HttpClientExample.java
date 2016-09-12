package com.roxoft.hierarchy.httpClientExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientExample {
	
	public void getListOfCountries() throws JsonParseException, JsonMappingException, IOException {
					
		//HttpClient client = new DefaultHttpClient();
		CloseableHttpClient client = HttpClients.createDefault();
		System.out.println("\nhttp://services.groupkt.com/country/get/all\n");
		HttpGet request = new HttpGet("http://services.groupkt.com/country/get/all");
		//HttpResponse response = client.execute(request);
		CloseableHttpResponse response = client.execute(request);
		
		try {
			BufferedReader rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			    
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
						
			ObjectMapper objectMapper = new ObjectMapper();
			Response resp;
			resp = objectMapper.readValue(result.toString(), Response.class);
			resp.getListOfCountries().print();
		} finally {
			response.close();
		}
	
	}
	
	public void getCountry(String doubleAlphaCode) throws ClientProtocolException, IOException{
		
		//HttpClient client = new DefaultHttpClient();
		CloseableHttpClient client = HttpClients.createDefault();
		System.out.println("\nhttp://services.groupkt.com/country/get/iso2code/" 
				+ doubleAlphaCode + "\n");
		HttpGet request = new HttpGet("http://services.groupkt.com/country/get/iso2code/" + doubleAlphaCode);
		//HttpResponse response = client.execute(request);
		CloseableHttpResponse response = client.execute(request);
		
		try {
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			
			String line = "";
					
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
					
			ObjectMapper objectMapper = new ObjectMapper();
			UnitResponse unitResp = objectMapper.readValue(result.toString(), UnitResponse.class); 
			unitResp.getUnitListOfCountries().print();
		} finally {
			response.close();
		}
	}
		
	public void sendPost() throws ClientProtocolException, IOException{
		
		//HttpClient client = new DefaultHttpClient();
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost post = new HttpPost("http://stg.caronfly.com:8080/ws/api/v1/login"); 
	 	post.setHeader("Accept-Language", "en");
	 	
	 	/*
	 	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    nameValuePairs.add(new BasicNameValuePair("email", "hursevich@gmail.com"));
	    nameValuePairs.add(new BasicNameValuePair("password", "copper"));
	    nameValuePairs.add(new BasicNameValuePair("role", "DRIVER"));
	    nameValuePairs.add(new BasicNameValuePair("version", "1.0"));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    */
	    
	 	post.setEntity(new StringEntity("{\"email\":\"hursevich@gmail.com\","
	 			+ "\"password\":\"copper\","
	 			+ "\"role\":\"DRIVER\","
	 			+ "\"version\":\"1.0\"}", 
                    ContentType.create("application/json")));
        
	 	System.out.println("\nhttp://stg.caronfly.com:8080/ws/api/v1/login\n");
	    System.out.println("{\"email\":\"hursevich@gmail.com\",\"password\":\"copper\",\"role\":\"DRIVE\",\"version\":\""+1.0+"\"}");
	 
	    //HttpResponse response = client.execute(post);
	    CloseableHttpResponse response = client.execute(post);
	    
	    try {
		    BufferedReader rd = new BufferedReader
		    		(new InputStreamReader(response.getEntity().getContent()));
		    String line = "";
		    StringBuffer result = new StringBuffer();
		    
		    while ((line = rd.readLine()) != null) {
		    	System.out.println(line);
		    	result.append(line);
		    }
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    PostObject postObject = objectMapper.readValue(result.toString(), 
		    		PostObject.class); 
		    postObject.print();
	    } finally {
			response.close();
		}
	    
	}
}
