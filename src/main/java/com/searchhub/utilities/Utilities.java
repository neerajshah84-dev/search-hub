package com.searchhub.utilities;

import java.io.InputStream;
import java.util.Properties;

public enum Utilities {
	INSTANCE;
	
	private static final Properties properties = new Properties();
	
	static {

		try (InputStream iStream = 
				Utilities.class.getClassLoader().getResourceAsStream("config.properties")){
			
			if(iStream == null) throw new RuntimeException("config file not found!!");
			
			properties.load(iStream);
			
			
		}catch(Exception e) {
			throw new RuntimeException("Filed to load config properties: " , e);
		}

		
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
}
