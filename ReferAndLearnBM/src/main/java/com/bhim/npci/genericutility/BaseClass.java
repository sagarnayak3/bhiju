package com.bhim.npci.genericutility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.bhim.npci.pomrepository.HomePage;
import com.bhim.npci.pomrepository.PasscodePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

@Listeners(com.bhim.npci.genericutility.ListenerImpClass.class)
/**
 * @author piyus
 * This class contains all configuration annotattion annotated methods
 */
public class BaseClass {
	public WebDriverWait wait;
	public PasscodePage passCode;
	public HomePage home;
	AppiumDriverLocalService service;
	
	/**
	 * @author piyus
	 * This method is used to start the appium server and also save the log
	 * @throws IOException
	 */
	@BeforeSuite
	public void config_BS() throws IOException {
		File file = new File(PathConstants.mainJS);
		FileUtility fileUtils = new FileUtility();
		service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress(fileUtils.readDataFromPropertyFile("IPAddress"))
				.usingPort(Integer.parseInt(fileUtils.readDataFromPropertyFile("Port")))
				.withTimeout(Duration.ofSeconds(300)).withLogFile(new File(PathConstants.logFilePath)).build();
		service.start();
	}

	/**
	 * @author piyus
	 * This method is used to pass all desired capabilities required to create an android session
	 */
	@BeforeClass
	public void config_BC() {

		UtilityObjectClass.setFileUtility(new FileUtility());
		UtilityObjectClass.setGestureUtility(new GestureUtility());
		UtilityObjectClass.setJavaUtility(new JavaUtility());
		UtilityObjectClass.setWebDriverUtility(new WebDriverUtility());
		UtilityObjectClass.setExcelUtility(new ExcelUtility());

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("PLATFORM_NAME"));
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("DEVICE_NAME"));
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("AUTOMATION_NAME"));
		dc.setCapability(MobileCapabilityType.UDID,
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("UDID"));
		dc.setCapability("appPackage", UtilityObjectClass.getFileUtility().readDataFromPropertyFile("appPackage"));
		dc.setCapability("appActivity", UtilityObjectClass.getFileUtility().readDataFromPropertyFile("appActivity"));
		dc.setCapability("ignoreHiddenApiPolicyError",
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("ignoreHiddenApiPolicyError"));
		dc.setCapability("autoGrantPermissions",
				UtilityObjectClass.getFileUtility().readDataFromPropertyFile("autoGrantPermissions"));
		dc.setCapability("noReset", UtilityObjectClass.getFileUtility().readDataFromPropertyFile("noReset"));

		URL url=null;
		try {
			url = new URL(UtilityObjectClass.getFileUtility().readDataFromPropertyFile("url"));
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		AndroidDriver driver = new AndroidDriver(url, dc);
		UtilityObjectClass.setDriver(driver);
		UtilityObjectClass.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(PathConstants.implicitWaitDuration));

		
		passCode = new PasscodePage();
		home = new HomePage();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	/**
	 * @author piyus
	 * This method is used to logout from the application
	 * @throws Throwable
	 */
	@AfterMethod
	public void config_AM() throws Throwable {
		home.verifyHomePage();
		home.logoutFromApplication();
		passCode.verifyPasscodePageHeader();
	}

	/**
	 * @author piyus
	 * This method is used for closing the entire session
	 */
	@AfterClass
	public void config_AC() {
		UtilityObjectClass.getDriver().quit();
	}

	/**
	 * @author piyus
	 * This method is used for closing the appium server
	 */
	@AfterSuite
	public void config_AS() {
		service.stop();
	}
}
