package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for passcode page
 */
public class PasscodePage {
	AndroidDriver driver;

	public PasscodePage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='passcode']")
	private WebElement passcodePageHeader;

	private String keyboardXpath = "//android.widget.TextView[@text='%s']";

	/**
	 * @author piyus
	 * This method is used to fetch dynamic xpath
	 * @param partialXpath
	 * @param replaceData
	 * @return
	 */
	private WebElement getWebElement(String partialXpath, String replaceData) {
		String xpath = String.format(partialXpath, replaceData);
		return UtilityObjectClass.getDriver().findElement(By.xpath(xpath));
	}

	/**
	 * @author piyus
	 * This method is used to verify passcode page
	 */
	public void verifyPasscodePageHeader() {
		try {
			Assert.assertTrue(
					passcodePageHeader.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"checkingBalanceAndVerifyTest", "passcodePageTitle")),
					"The passcode page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The passcode page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The passcode page is not displayed");
			Assert.fail("The passcode page is not displayed");
		}
	}

	/**
	 * @author piyus
	 * This method is used to login to the application by entering passcode
	 * @param passCode
	 */
	public void loginToApp(String passCode) {
		try {
			passCode = new String(Base64.getDecoder().decode(passCode.getBytes()));
			for (int i = 0; i < passCode.length(); i++) {
				getWebElement(keyboardXpath, passCode.charAt(i) + "").click();
			}
			UtilityObjectClass.getExtentTest().pass("Passcode Entered Successfullly");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("Passcode Not Entered Successfullly");
			Assert.fail();
		}
	}

}
