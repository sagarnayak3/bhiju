package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Priyanka 
 * This class contains the locators and business libraries for scan and pay page
 */
public class ScanAndPayPage {
	AndroidDriver driver;

	public ScanAndPayPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='load image from gallery']")
	private WebElement loadFromGallery;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']/following-sibling::android.view.View")
	private WebElement scanAndPayText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeName']")
	private WebElement payeeName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeUpiID']")
	private WebElement payeeUPIId;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.oneplus.gallery:id/albumset_title']")
	private List<WebElement> allFolderNames;

	@AndroidFindBy(xpath = "//com.oplus.gallery.business_lib.ui.view.SlotView[@resource-id='com.oneplus.gallery:id/base_album_item_img']")
	private WebElement imageToSelect;

	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='00.00']")
	private WebElement amountTextField;

	@AndroidFindBy(accessibility = "Send")
	private WebElement sendButton;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Remarks')]//android.widget.EditText")
	private WebElement remarksTextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='UPILiteText']")
	private WebElement upiLiteText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='checkBoxText']/preceding-sibling::android.view.ViewGroup[3]")
	private WebElement checkBox;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Later']/android.widget.TextView")
	private WebElement laterButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Money sent!']")
	private WebElement moneySentText;

	@AndroidFindBy(accessibility = "Home")
	private WebElement homeButton;

	/**
	 * @author Priyanka 
	 * This method is used for send money by scanning the QR code
	 * @param sendMoney
	 * @param eUtils
	 * @param contact
	 * @return 
	 * @throws Throwable
	 * @throws Throwable
	 */
	public ScanAndPayPage sendingMoneyAndVerify() {
		amountTextField.sendKeys(
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "payThroughScanner", "amount"));
		remarksTextField.sendKeys(
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "payThroughScanner", "remark"));
		sendButton.click();
		checkBox.click();
		laterButton.click();
		AuthenticationPage auth = new AuthenticationPage();
		auth.enteringPIN();
		verifySuccessTest();
		return this;
	}

	/**
	 * @author Priyanka
	 * This method is used for scanning the qr code form gallery
	 * @return
	 */
	public ScanAndPayPage selectQRFromGallery() {
		loadFromGallery.click();
		for (WebElement folderName : allFolderNames) {
			if (folderName.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
					"payThroughScanner", "folderName"))) {
				folderName.click();
				break;
			}
		}
		imageToSelect.click();
		verifyPayeeNameAndUpiId();
		return this;
	}

	/**
	 * @author Priyanka
	 * This method is used for verification of the scan and pay page
	 */
	public void verifyScanAndPayPage() {
		try {
			Assert.assertTrue(
					scanAndPayText.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"payThroughScanner", "scanAndPayPageTitle")),
					"The scan and pay page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The scan and pay page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The scan and pay page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author Priyanka
	 * This method is used to verify the payee name and upi id
	 */
	public void verifyPayeeNameAndUpiId() {
		try {
			Assert.assertTrue(payeeName.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "payThroughScanner", "payeeName")), "The payee name is not correct");
			Assert.assertTrue(payeeUPIId.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "payThroughScanner", "payeeUPIId")),
					"The payee UPI Id is not correct");
			UtilityObjectClass.getExtentTest().pass("The payee name and upi id are correct");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The payee name and upi id are not correct");
			Assert.fail();
		}
	}

	/**
	 * @author Priyanka
	 * This method is used for verification of the success message
	 */
	public void verifySuccessTest() {
		try {
			Assert.assertTrue(moneySentText.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "payThroughScanner", "successText")),
					"The money sent page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The money sent page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The money sent page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author Priyanka
	 * This method is used to click on home button and verify that page
	 * @return
	 */
	public HomePage clickOnHomeButtonAndVerify() {
		homeButton.click();
		HomePage home = new HomePage();
		home.verifyHomePage();
		return home;
	}
}
