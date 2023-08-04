package com.bhim.npci.genericutility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

/**
 * @author piyus
 * This class is used to creating getter and setter methods for all generic utilities 
 */
public class UtilityObjectClass {
	private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<AndroidDriver>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentReports> report = new ThreadLocal<ExtentReports>();
	private static ThreadLocal<ExcelUtility> excelUtility = new ThreadLocal<ExcelUtility>();
	private static ThreadLocal<FileUtility> fileUtility = new ThreadLocal<FileUtility>();
	private static ThreadLocal<GestureUtility> gestureUtility = new ThreadLocal<GestureUtility>();
	private static ThreadLocal<JavaUtility> javaUtility = new ThreadLocal<JavaUtility>();
	private static ThreadLocal<WebDriverUtility> webUtility = new ThreadLocal<WebDriverUtility>();

	/**
	 * @author piyus
	 * This is the getter method for AndroidDriver
	 * @return
	 */
	public static AndroidDriver getDriver() {
		return driver.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for AndroidDriver
	 * @param actDriver
	 */
	public static void setDriver(AndroidDriver actDriver) {
		driver.set(actDriver);
	}

	/**
	 * @author piyus
	 * This is the getter method for ExtentReports
	 * @return
	 */
	public static ExtentReports getReport() {
		return report.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for ExtentReports
	 * @param reports
	 */
	public static void setReport(ExtentReports reports) {
		report.set(reports);
	}

	/**
	 * @author piyus
	 * This is the getter method for ExtentTest
	 * @return
	 */
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for ExtentTest
	 * @param extent
	 */
	public static void setExtentTest(ExtentTest extent) {
		extentTest.set(extent);
	}

	/**
	 * @author piyus
	 * This is the getter method for ExcelUtility
	 * @return
	 */
	public static ExcelUtility getExcelUtility() {
		return excelUtility.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for ExcelUtility
	 * @param actExcelUtility
	 */
	public static void setExcelUtility(ExcelUtility actExcelUtility) {
		excelUtility.set(actExcelUtility);
	}

	/**
	 * @author piyus
	 * This is the getter method for FileUtility
	 * @return
	 */
	public static FileUtility getFileUtility() {
		return fileUtility.get();
	}
	
	/**
	 * @author piyus
	 * This is the setter method for FileUtility
	 * @param actFileUtility
	 */
	public static void setFileUtility(FileUtility actFileUtility) {
		fileUtility.set(actFileUtility);
	}
	
	/**
	 * @author piyusThis is the getter method for GestureUtility
	 * @return
	 */
	public static GestureUtility getGestureUtility() {
		return gestureUtility.get();
	}

	/**
	 * @author piyus
	 * This is the  setter method for GestureUtility
	 * @param actGestureUtility
	 */
	public static void setGestureUtility(GestureUtility actGestureUtility) {
		gestureUtility.set(actGestureUtility);
	}
	
	/**
	 * @author piyus
	 * This is the getter method for JavaUtility
	 * @return
	 */
	public static JavaUtility getJavaUtility() {
		return javaUtility.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for JavaUtility
	 * @param actJavaUtility
	 */
	public static void setJavaUtility(JavaUtility actJavaUtility) {
		javaUtility.set(actJavaUtility);
	}
	
	/**
	 * @author piyus
	 * This is the getter method for WebDriverUtility
	 * @return
	 */
	public static WebDriverUtility getWebDriverUtility() {
		return webUtility.get();
	}

	/**
	 * @author piyus
	 * This is the setter method for WebDriverUtlity
	 * @param actWebDriverUtility
	 */
	public static void setWebDriverUtility(WebDriverUtility actWebDriverUtility) {
		webUtility.set(actWebDriverUtility);
	}
}