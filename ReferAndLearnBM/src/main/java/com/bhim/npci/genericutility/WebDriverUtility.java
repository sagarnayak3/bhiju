package com.bhim.npci.genericutility;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
/**
 * @author Priyanka
 * This class is used for web driver related operations
 */
public class WebDriverUtility {

	private WebDriverWait wait;

	/**
	 * @author Priyanka
	 * This method is used to wait for element to be clickable
	 * @param wait
	 * @param element
	 */
	public void explicitWaitForClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(UtilityObjectClass.getDriver(), Duration.ofSeconds(PathConstants.implicitWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * @author Priyanka
	 * This method is used to wait for element to be visible
	 * @param wait
	 * @param element
	 */
	public void explicitWaitForVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(UtilityObjectClass.getDriver(), Duration.ofSeconds(PathConstants.implicitWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * @author Priyanka
	 * This method is used for statleness of element
	 * @param element
	 */
	public void explicitWaitForStaleness(WebElement element) {
		WebDriverWait wait = new WebDriverWait(UtilityObjectClass.getDriver(), Duration.ofSeconds(PathConstants.implicitWaitDuration));
		wait.until(ExpectedConditions.stalenessOf(element));
	}
}
