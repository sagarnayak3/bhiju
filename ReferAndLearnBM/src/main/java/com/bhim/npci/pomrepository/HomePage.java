package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Priyanka 
 * This class contains the locators and business libraries for home page
 */
public class HomePage {
	AndroidDriver driver;

	public HomePage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to change')]")
	private WebElement selectBankAccount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='bankName']")
	private WebElement bankName;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='More Options, Double tap to Explore']")
	private WebElement moreOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
	private WebElement logout;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ಲಾಗ್ ಔಟ್']")
	private WebElement logoutKannada;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='Manage']")
	private WebElement manageOption;

	@AndroidFindBy(accessibility = "Send Money, Double tap to send money")
	private WebElement sendMoneyOption;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Scan a QR code, Double tap to scan a QR code']")
	private WebElement qrScanner;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']/parent::android.view.ViewGroup")
	private WebElement profileMenu;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Change Language']")
	private WebElement changeLanguageMenu;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ಭಾಷೆ ಆಯ್ಕೆಮಾಡಿ']")
	private WebElement changeLanguageKan;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='favouriteItem']/android.widget.TextView")
	private List<WebElement> allFavourites;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Send Feedback']")
	private WebElement sendFeedback;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Transactions']/parent::android.view.ViewGroup")
	private WebElement transactionMenu;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bill Pay']")
	private WebElement billPay;
	
	
	/**
	 * @author Priyanka
	 * This method is used to verify home page is displayed or not
	 */
	public void verifyHomePage() {
		try {
			Assert.assertTrue(billPay.getText().contains(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "billPayText")),
					"The home page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The home page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The home page is not displayed");
			Assert.fail();
		}
	}
	

	/**
	 * @author Priyanka
	 * This methos is used to navigate to change language page and verify that page
	 * @return
	 */
	public PreferredLanguagePage goToChangeLanguagePageAndVerify() {
		moreOption.click();
		changeLanguageMenu.click();
		PreferredLanguagePage lang = new PreferredLanguagePage();
		lang.verifyChangeLanguagePage();
		return lang;
	}

	
	/**
	 * @author Priyanka
	 * This methos is used to navigate to change language page and verify that page
	 * @return
	 */
	public PreferredLanguagePage goToChangeLanguagePageAndVerifyKan() {
		moreOption.click();
		changeLanguageKan.click();
		PreferredLanguagePage lang = new PreferredLanguagePage();
		lang.verifyChangeLanguagePage();
		return lang;
	}
	
	
	/**
	 * @author Priyanka
	 * This method is used to verify whether language is changed or not
	 * @return 
	 */
	public PreferredLanguagePage verifyLanguageChange() {
		try {
			String changedTextToVerify = "changedText" + UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "verifyLanguageTest", "language");
			WebElement changedText = driver.findElement(
					AppiumBy.xpath("//android.widget.TextView[@text='" + UtilityObjectClass.getExcelUtility()
							.getDataFromExcel("Test Data", "verifyLanguageTest", changedTextToVerify) + "']"));
			Assert.assertTrue(changedText.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "verifyLanguageTest", changedTextToVerify)),
					"The language is not changed");
			UtilityObjectClass.getExtentTest().pass("The language is changed");
		} catch (Exception e) {
			UtilityObjectClass.getExtentTest().fail("The language is not changed");
			Assert.fail();
		}
		UtilityObjectClass.getWebDriverUtility().explicitWaitForClickable(moreOption);
		moreOption.click();
		changeLanguageKan.click();
		PreferredLanguagePage lang = new PreferredLanguagePage();
		lang.verifyChangeLanguagePageKan();
		return lang;
	}

	/**
	 * @author Priyanka
	 * This method is used to logout from the application
	 */
	public void logoutFromApplication() {
		moreOption.click();
		logout.click();
	}
	

	/**
	 * @author Priyanka
	 * This method is used to click on the profile menu and then verify that page
	 * @return
	 */
	public MyProfilePage clickOnProfileMenuAndVerify() {
		profileMenu.click();
		MyProfilePage profile = new MyProfilePage();
		profile.verifyProfilePage();
		return profile;
	}

	/**
	 * @author Priyanka
	 * This method is used to navigate to home page
	 */
	public void backNavigationTwice() {
		UtilityObjectClass.getDriver().navigate().back();
		UtilityObjectClass.getDriver().navigate().back();
	}

	/**
	 * @author Priyanka
	 * This method is used to navigate to home page
	 */
	public void backNavigation() {
		UtilityObjectClass.getDriver().navigate().back();
	}

	/**
	 * @author Priyanka
	 * This method is used to click on accounts and verify that page
	 * @return
	 */
	public BankAccountsPage clickOnAccountAndVerify() {
		selectBankAccount.click();
		BankAccountsPage accounts = new BankAccountsPage();
		accounts.verifyBankAccountPage();
		return accounts;
	}

	/**
	 * @author Priyanka
	 * This method is used to click on the send money optionand verify that page
	 * @return
	 */
	public SendMoneyToContactPage clickOnSendMoneyOption() {
		sendMoneyOption.click();
		SendMoneyToContactPage contact = new SendMoneyToContactPage();
		contact.verifySendMoneyPage();
		return contact;
	}

	/**
	 * @author Priyanka
	 * This method is used to click on QRScanner and verify that page
	 * @return
	 */
	public ScanAndPayPage clickOnQRScannerAndVerify() {
		qrScanner.click();
		ScanAndPayPage scan = new ScanAndPayPage();
		scan.verifyScanAndPayPage();
		return scan;
	}
}