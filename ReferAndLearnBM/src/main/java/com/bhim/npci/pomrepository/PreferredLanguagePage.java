package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.ExcelUtility;
import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Priyanka
 * This class contains the locators and business libraries for preferred language page
 */
public class PreferredLanguagePage {
	AndroidDriver driver;

	public PreferredLanguagePage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Change Language']")
	private WebElement languagePageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=' Hello']")
	private WebElement englishOption;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to select')]/following-sibling::android.widget.TextView[2]")
	private List<WebElement> languageOptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=' (HINDI)']")
	private WebElement hindiOption;

	@AndroidFindBy(xpath = "//android.view.View[@text='ಭಾಷೆ ಆಯ್ಕೆಮಾಡಿ']")
	private WebElement languagePageTextKan;

	/**
	 * @author Priyanka 
	 * This method is used to select a particular language and redirect to home page
	 * @param eUtils
	 * @return 
	 * @throws Throwable
	 */
	public HomePage selectingLanguageAndProceed() {
		for (WebElement languageOption : languageOptions) {
			if (languageOption.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
					"verifyLanguageTest", "language"))) {
				languageOption.click();
				break;
			}
		}
		clickOnChangedNextButton();
		HomePage home = new HomePage();
		return home;
	}
	
	public void selectingPrimaryLanguageAndProceed() {
		for (WebElement languageOption : languageOptions) {
			if (languageOption.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
					"verifyLanguageTest", "primaryLanguage"))) {
				languageOption.click();
				break;
			}
		}
		clickOnChangedNextButtonPrimary();
	}

	/**
	 * @author Priyanka
	 * This method is used to verify the language is changed or not
	 */
	public void verifyChangeLanguagePage() {
		try {
			Assert.assertTrue(
					languagePageText.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"verifyLanguageTest", "languagePageTitle")),
					"The change language page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The change language page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().pass("The change language page is not displayed");
			Assert.fail();
		}
	}
	
	public void verifyChangeLanguagePageKan() {
		try {
			Assert.assertTrue(
					languagePageTextKan.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"verifyLanguageTest", "languagePageTitle (KANNADA)")),
					"The change language page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The change language page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().pass("The change language page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author Priyanka 
	 * This method is used to click on next button
	 * @param eUtils
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	public void clickOnChangedNextButton() {
		WebElement nextButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='"
				+ UtilityObjectClass.getExcelUtility()
						.getDataFromExcel("Test Data", "verifyLanguageTest", "textToVerify" + UtilityObjectClass
								.getExcelUtility().getDataFromExcel("Test Data", "verifyLanguageTest", "language"))
				+ "']/android.widget.TextView"));
		nextButton.click();
	}
	
	/**
	 * @author Priyanka
	 * This method is used to click on next button
	 */
	public void clickOnChangedNextButtonPrimary() {
		WebElement nextButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='"
				+ UtilityObjectClass.getExcelUtility()
						.getDataFromExcel("Test Data", "verifyLanguageTest", "textToVerify" + UtilityObjectClass
								.getExcelUtility().getDataFromExcel("Test Data", "verifyLanguageTest", "primaryLanguage"))
				+ "']/android.widget.TextView"));
		nextButton.click();
	}
}
