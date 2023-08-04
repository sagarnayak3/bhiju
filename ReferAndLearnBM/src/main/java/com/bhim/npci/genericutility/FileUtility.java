package com.bhim.npci.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * @author piyus
 * This class is used to perform property file related operations
 */
public class FileUtility {
	
	/**
	 * @author piyus
	 * This methos is used to read data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(PathConstants.commonDataFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = prop.getProperty(key);
		return value;
	}
}
