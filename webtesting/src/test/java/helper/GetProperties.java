package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
	private Properties properties = new Properties();
	
	public void loadPropertiesFile(String path) throws FileNotFoundException, IOException{
		properties.load(new FileReader(new File(path)));
	}
	
	public String getPropertyValue(String propertyname){
		String propertyvalue = null;
		propertyvalue = properties.getProperty(propertyname);
		return propertyvalue;
		
	}
}
