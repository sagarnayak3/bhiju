package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for the authentication page
 */
public class AuthenticationPage {
	AndroidDriver driver;

	public AuthenticationPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='in.org.npci.upiapp:id/form_item_input']")
	private WebElement enterPinTextField;
	
	@AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[4]/android.widget.ImageView[2]")
	private WebElement approveButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/form_item_title']")
	private WebElement enterPINText;

	private String keyBoardXpath = "//android.widget.TextView[@text='%s']";
	
	private WebElement getWebElement(String partialXpath, String replaceData) {
		String xpath = String.format(partialXpath, replaceData);
		return UtilityObjectClass.getDriver().findElement(By.xpath(xpath));
		
	}
	/**
	 * @author piyus
	 * This method is used to enter the upi pin and click on submit button
	 */
	public void enteringPIN() {
		String pin = UtilityObjectClass.getFileUtility().readDataFromPropertyFile("pin");
		try {
			pin = new String(Base64.getDecoder().decode(pin.getBytes()));
			for (int i = 0; i < pin.length(); i++) {
				getWebElement(keyBoardXpath, pin.charAt(i) + "").click();
			}
			UtilityObjectClass.getExtentTest().pass("Pin Entered Successfullly");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("Pin Not Entered Successfullly");
			Assert.fail();
		}
		approveButton.click();
	}
	
	/**
	 * @author piyus
	 * This method is used to enter the wrong upi pin and click on submit button
	 */
	public void enteringWrongPIN() {
		String wrongPin = UtilityObjectClass.getFileUtility().readDataFromPropertyFile("wrongPin");
		try {
			wrongPin = new String(Base64.getDecoder().decode(wrongPin.getBytes()));
			for (int i = 0; i < wrongPin.length(); i++) {
				getWebElement(keyBoardXpath, wrongPin.charAt(i) + "").click();
			}
			UtilityObjectClass.getExtentTest().pass("Pin Entered Successfullly");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("Pin Not Entered Successfullly");
			Assert.fail();
		}
		approveButton.click();
	}
}
