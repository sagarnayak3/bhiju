package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus 
 * This class contains the locators and business libraries for send money to contact page
 */
public class SendMoneyToContactPage {
	AndroidDriver driver;

	public SendMoneyToContactPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Send Money']")
	private WebElement sendMoneyPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeName']")
	private WebElement payeeName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeUpiID']")
	private WebElement payeeUPIId;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Contacts, tab']")
	private WebElement contactsTab;

	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement searchTextField;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'double tap to select contact')]/android.widget.TextView")
	private List<WebElement> allElements;

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

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Could not send money!']")
	private WebElement couldNotSendMoneyText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Money sent!']")
	private WebElement moneySentText;

	@AndroidFindBy(accessibility = "Home")
	private WebElement homeButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/snackbar_text']")
	private WebElement toastElement;

	/**
	 * @author piyus 
	 * This method is used for searching and selecting a particular contact and verify name and upi
	 * @param eUtils
	 * @return 
	 * @throws Throwable
	 * @throws IOException
	 */
	public SendMoneyToContactPage searchingAndSelectingContact() {
		contactsTab.click();
		searchTextField.sendKeys(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
				"sendingMoneyToContactAndValidateBalanceTest", "contactName"));
		for (WebElement oneContact : allElements) {
			if (oneContact.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
					"sendingMoneyToContactAndValidateBalanceTest", "contactNumber"))) {
				oneContact.click();
				break;
			}
		}
		verifyNameAndUpiId();
		return this;
	}

	/**
	 * @author piyus 
	 * This method is used for sending the money to any particular contact with wrong pin
	 * @param sendMoney
	 * @param eUtils
	 * @return 
	 * @throws Throwable
	 * @throws Throwable
	 */
	public SendMoneyToContactPage sendingMoneyWithWrongPin() {
		amountTextField.sendKeys(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
				"sendingMoneyToContactAndValidateBalanceTest", "amount"));
		remarksTextField.sendKeys(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
				"sendingMoneyToContactAndValidateBalanceTest", "remark"));
		sendButton.click();
		checkBox.click();
		laterButton.click();
		AuthenticationPage auth = new AuthenticationPage();
		auth.enteringWrongPIN();
		return this;
	}

	/**
	 * @author piyus
	 * This method is use for trying to send money by entering invalid amount
	 * @param sendMoney
	 * @param eUtils
	 * @param auth
	 * @return 
	 * @throws Throwable
	 * @throws Throwable
	 */
	public SendMoneyToContactPage sendingInvalidAmount() {
		amountTextField.sendKeys(
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "sendingInvalidAmount", "amount"));
		remarksTextField.sendKeys(
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "sendingInvalidAmount", "remark"));
		sendButton.click();
		verifyToastMessage();
		return this;
	}

	/**
	 * @author piyus
	 * This method is used to verify the send money page
	 */
	public void verifySendMoneyPage() {
		try {
			Assert.assertTrue(
					sendMoneyPageTitle.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"sendingInvalidAmount", "sendMoneyPageTitle")),
					"The send money page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The send money page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The send money page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author piyus
	 * This method is used for verifying name and upi id
	 */
	public void verifyNameAndUpiId() {
		try {
			UtilityObjectClass.getWebDriverUtility().explicitWaitForVisibility(payeeName);
			Assert.assertTrue(payeeName.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "sendingInvalidAmount", "payeeName")),
					"The payee name is not correct");
			Assert.assertTrue(payeeUPIId.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "sendingInvalidAmount", "payeeUPIId")),
					"The payee UPI Id is not correct");
			UtilityObjectClass.getExtentTest().pass("The payee name and upi id are correct");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The payee name and upi id are not correct");
			Assert.fail();
		}
	}

	/**
	 * @author piyus
	 * This method is used for verifying the toast message
	 */
	public void verifyToastMessage() {
		try {
			Assert.assertTrue(toastElement.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "sendingInvalidAmount", "failureText")),
					"The toast message received is wrong");
			UtilityObjectClass.getExtentTest().pass("The toast message received is correct");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The toast message received is wrong");
			Assert.fail();
		}
	}

	/**
	 * @author piyus
	 * This method is used for verifying the transaction failure message
	 */
	public void verifyFailureMessageAndGoToHome() {
		try {
			Assert.assertTrue(
					couldNotSendMoneyText.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"enteringWrongPinWhileSendingMoneyTest", "failureText")),
					"The failure message received is wrong");
			UtilityObjectClass.getExtentTest().pass("The failure message received is correct");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The failure message received is wrong");
			Assert.fail();
		}
		homeButton.click();
	}

	/**
	 * @author piyus
	 * This method is used to navigate to home page and verify that
	 * @return
	 */
	public HomePage clickOnHomeButtonAndVerify() {
		homeButton.click();
		HomePage home = new HomePage();
		home.verifyHomePage();
		return home;
	}

	/**
	 * @author piyus
	 * his method is used for verifying the transaction success message
	 */
	public void verifySuccessMessage() {
		try {
			Assert.assertTrue(
					moneySentText.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"sendingMoneyToContactAndValidateBalanceTest", "successText")),
					"The money sent page is not displayed");
			UtilityObjectClass.getExtentTest().pass("The money sent page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The money sent page is not displayed");
			Assert.fail();
		}
	}
	
	/**
	 * @author piyus
	 * This method is used for sending money and verifying success message
	 * @return
	 */
	public SendMoneyToContactPage sendingMoneyAndVerify() {
		amountTextField.sendKeys(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
				"sendingMoneyToContactAndValidateBalanceTest", "amount"));
		remarksTextField.sendKeys(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
				"sendingMoneyToContactAndValidateBalanceTest", "remark"));
		sendButton.click();
		checkBox.click();
		laterButton.click();
		AuthenticationPage auth = new AuthenticationPage();
		auth.enteringPIN();
		verifySuccessMessage();
		return this;
	}
}
