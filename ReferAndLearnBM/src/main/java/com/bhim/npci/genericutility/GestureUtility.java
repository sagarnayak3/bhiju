package com.bhim.npci.genericutility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
/**
 * @author piyus
 * This class is used for executing gestures
 */
public class GestureUtility{

	/**
	 * This method is used to perform drag and drop operations.
	 * @author Piyush
	 * @param driver
	 * @param element
	 * @param endX
	 * @param endY
	 */
	public void dragAndDrop(AndroidDriver driver, WebElement element, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", endX, "endY", endY));
	}

	/**
	*@author piyus
	*TThis method is used to perform drag and drop operation
	 * @param driver
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void dragAndDrop(AndroidDriver driver, int startX, int startY, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("startX", startX, "startY", startY, "endX", endX, "endY", endY));
	}

	/**
	 * @author piyus
	 * This method is used to perform long click operation
	 * @param driver
	 * @param element
	 * @param duration
	 */
	public void longClick(AndroidDriver driver, WebElement element, int duration) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", duration));
	}

	/**
	 * @author piyus
	 * This method is used to perform long click operation
	 * @param driver
	 * @param x
	 * @param y
	 * @param duration
	 */
	public void longClick(AndroidDriver driver, int x, int y, int duration) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("x", x, "y", y, "duration", duration));
	}

	/**
	 * @author piyus
	 * This method is used to perform zoom in operation
	 * @param driver
	 * @param element
	 * @param percent
	 * @param speed
	 */
	public void pinchOpen(AndroidDriver driver, WebElement element, double percent, int speed) {
		((JavascriptExecutor) driver).executeScript("mobile : pinchOpenGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", percent, "speed", speed));
	}

	/**
	 * @author piyus
	 * This method is used to perform zoom out operation
	 * @param driver
	 * @param element
	 * @param percent
	 * @param speed
	 */
	public void pinchClose(AndroidDriver driver, WebElement element, double percent, int speed) {
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", percent, "speed", speed));
	}

	/**
	 * @author piyus
	 * This method is used to perform srrolling operation
	 * @param driver
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param direction
	 * @param percent
	 */
	public void scroll(AndroidDriver driver, int left, int top, int width, int height, String direction,
			double percent) {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", left, "top", top,
				"width", width, "height", height, "direction", direction, "percent", percent));
	}

	/**
	 * @author piyus
	 * This method is used to perform scrolling operation
	 * @param driver
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param direction
	 * @param percent
	 */
	public void scrollTillEnd(AndroidDriver driver, int left, int top, int width, int height, String direction,
			double percent) {
		boolean canScrollMore;
		{
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", left, "top", top, "width", width, "height", height, "direction", direction, "percent", percent));
		}
		while (canScrollMore);
	}
	
	/**
	 * @author piyus
	 * This method is used to perform scrolling till an element operation
	 * @param driver
	 * @param text
	 */
	public void scrollTillElement(AndroidDriver driver,String text) {
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\""+text+"\"));"));
	}
	
	/**
	 * @author piyus
	 * This method is used to perform swipping operation
	 * @param driver
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param direction
	 * @param percent
	 */
	public void swipe(AndroidDriver driver, int left, int top, int width, int height, String direction,
			double percent) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "left", left, "top", top, "width", width, "height", height,
			    "direction", direction,
			    "percent", percent
			));
	}
	
	/**@author piyus
	 * This method is used to perform click operation
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void clickOnElement(AndroidDriver driver, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
				ImmutableMap.of("x", x, "y", y));
	}
}
