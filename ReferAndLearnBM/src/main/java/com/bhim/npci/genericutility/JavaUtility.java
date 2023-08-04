package com.bhim.npci.genericutility;

import java.util.Date;

import io.appium.java_client.android.AndroidDriver;

/**
 * @author piyus 
 * This class contains common java utilities
 */
public class JavaUtility {
	

	/**
	 * @author piyus 
	 * This method is used to return formatted date
	 * @return
	 */
	public String formattedDate() {
		Date date = new Date();
		String[] cDate = date.toString().split(" ");
		String formattedDate = cDate[0] + " " + cDate[2];
		return formattedDate;
	}
}
