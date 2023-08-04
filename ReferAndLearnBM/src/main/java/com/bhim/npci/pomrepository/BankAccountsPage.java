package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.ExcelUtility;
import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus 
 * This class contains the locators and business libraries for
 *         bank accounts page
 */
public class BankAccountsPage {
	AndroidDriver driver;

	public BankAccountsPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Bank Accounts']")
	private WebElement bankAccount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Check Balance']")
	private WebElement checkBalanceOption;

	@AndroidFindBy(accessibility = "Double tap to expand")
	private WebElement expandOption;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='NOT NOW']")
	private WebElement feedBackDecline;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='actualBalance']")
	private WebElement actualBalanceText;

	/**
	 * @author piyus 
	 * This method is used to insert actual balance generated to excel sheet
	 * @param auth
	 * @param eUtils
	 * @return 
	 * @throws Throwable
	 * @throws IOException
	 */
	public HomePage checkAndInsertBalanceIntoExcel() {
		checkBalanceOption.click();
		AuthenticationPage auth = new AuthenticationPage();
		auth.enteringPIN();
		String actualBalance = actualBalanceText.getText();
		UtilityObjectClass.getExcelUtility().setDataIntoExcel("Test Data", "checkingBalanceAndVerifyTest",
				"actualBalance", actualBalance);
		HomePage home = new HomePage();
		home.backNavigationTwice();
		home.verifyHomePage();
		return home;
	}

	/**
	 * @author piyus 
	 * This method is used to check the balance by enetering pin
	 * @param accounts
	 * @param auth
	 */
	public void checkBalance(AuthenticationPage auth) {
		checkBalanceOption.click();
		auth.enteringPIN();
	}

	/**
	 * @author piyus
	 * @author piyus This method is used to verify the balance after the deduction
	 * @param eUtils
	 * @param auth
	 * @param actualBalanceBeforeTransaction
	 * @throws Throwable
	 * @throws IOException
	 */
	public void verifyBalanceAfterDeduction() {
		try {
				checkBalanceOption.click();
				AuthenticationPage auth = new AuthenticationPage();
				auth.enteringPIN();
				String actualBalanceAfterTransaction = actualBalanceText.getText();
				String actualBalanceAfterTransactionModified = actualBalanceAfterTransaction.replace("₹ ", "").replace(",", "");
				String actualBalanceBeforeTransaction = UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest",
				"actualBalance");
				String actualBalanceBeforeTransactionModified = actualBalanceBeforeTransaction.replace("₹ ", "").replace(",", "");
				double expectedBalanceAfterTransactionModified = Double.parseDouble(actualBalanceBeforeTransactionModified)
						- Double.parseDouble(
								UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "amount")
										.replace("₹ ", ""));
				String expectedBalanceAfterTransaction = String.valueOf(expectedBalanceAfterTransactionModified);
				Assert.assertEquals(actualBalanceAfterTransactionModified, expectedBalanceAfterTransaction);
				UtilityObjectClass.getExtentTest().pass("The expected balance and actual balance are same");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The expected balance and actual balance are not same");
			Assert.fail();
		}
		driver.navigate().back();
	}

	/**
	 * @author piyus
	 * This method is used to verify the accounts page is displayed or not
	 */
	public void verifyBankAccountPage() {
		try {
			Assert.assertTrue(
					bankAccount.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"checkingBalanceAndVerifyTest", "accountsPageTitle")),
					"Bank Accounts Page is not displayed");
			UtilityObjectClass.getExtentTest().pass("Bank Accounts Page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("Bank Accounts Page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author piyus
	 * This method is used to verify the balance and redirect to home page
	 */
	public void verifyingBalanceAndBackToHome() {
		checkBalanceOption.click();
		AuthenticationPage auth = new AuthenticationPage();
		auth.enteringPIN();
		try {
			Assert.assertTrue(
					actualBalanceText.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"checkingBalanceAndVerifyTest", "actualBalance")),
					"The actual balance and expected balance are not same");
			UtilityObjectClass.getExtentTest().pass("The actual balance and expected balance are same");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The actual balance and expected balance are not same");
			Assert.fail();
		}
		HomePage home = new HomePage();
		home.backNavigation();
	}
	
	/**
	 * @author piyus
	 * This method is used to verify the bank accounts page
	 */
	public void verifyBankAccountsPage() {
		try {
			Assert.assertTrue(
					bankAccount.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
							"sendingMoneyToContactAndValidateBalanceTest", "accountsPageTitle")),
					"Bank Accounts Page is not displayed");
			UtilityObjectClass.getExtentTest().pass("Bank Accounts Page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("Bank Accounts Page is not displayed");
			Assert.fail();
		}
	}

}
