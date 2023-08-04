package com.bhim.npci.genericutility;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Priyanka 
 * This class is used to perform operations related to json file
 */
public class JSONUtility {

	/**
	 * @author Priyanka 
	 * This method is used to read data from the json file
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public String readDataFromJSON(String key) throws IOException, ParseException {
		FileReader fr = new FileReader(PathConstants.commonDataJSONFilePath);
		JSONParser jp = new JSONParser();
		Object obj = jp.parse(fr);
		HashMap hm = (HashMap) obj;
		String value = (String) hm.get(key);
		return value;
	}
}
