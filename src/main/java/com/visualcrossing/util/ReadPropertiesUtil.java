package com.visualcrossing.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesUtil {
	
	Properties properties;
	String propertiesFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
	
	public ReadPropertiesUtil(){
		
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String getUrl() {
		String baseUrl = properties.getProperty("baseUrl");
		
		if(baseUrl!=null)
			return baseUrl;
		else
			throw new RuntimeException("URL key is not specified in properties file");
	}
	
	public String getLocation() {
		String location = properties.getProperty("location");
		
		if(location!=null)
			return location;
		else
			throw new RuntimeException("Location key is not specified in properties file");
	}

}
