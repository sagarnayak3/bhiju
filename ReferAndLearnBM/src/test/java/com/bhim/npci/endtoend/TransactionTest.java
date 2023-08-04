package com.bhim.npci.endtoend;

import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;

/**
 * @author Piyush
 * This class contains the methods associated with Transaction module
 */
public class TransactionTest extends BaseClass {

	/**
	 *SendMoneyOption->Select Contact->Send Invalid Amount 
	 */
	@Test(priority = 3)
	public void sendInvalidAmountTest() {
		home.clickOnSendMoneyOption()
			.searchingAndSelectingContact()
			.sendingInvalidAmount();
		home.backNavigationTwice();
	}

	/**
	 * SendMoneyOption->Select Contact->Send Amount->Wrong PIN
	 */
	@Test(priority = 2)
	public void enteringWrongPinWhileSendingMoneyTest() {
		home.clickOnSendMoneyOption()
			.searchingAndSelectingContact()
			.sendingMoneyWithWrongPin()
			.verifyFailureMessageAndGoToHome();
	}

	/**
	 * Profile->Accounts->CheckBalance->Send Amount->Verify Balance
	 */
	@Test(priority = 1)
	public void sendingMoneyToContactAndValidateBalanceTest() {
		home.clickOnProfileMenuAndVerify()
			.clickOnAccountsAndVerify()
			.checkAndInsertBalanceIntoExcel()
			.clickOnSendMoneyOption()
			.searchingAndSelectingContact()
			.sendingMoneyAndVerify()
			.clickOnHomeButtonAndVerify()
			.clickOnAccountAndVerify()
			.verifyBalanceAfterDeduction();
	}

	/**
	 * QRScanner->Select Image->Send Amount
	 */
	//@Test
	public void payThroughScanner() {
		home.clickOnQRScannerAndVerify()
		.selectQRFromGallery()
		.sendingMoneyAndVerify()
		.clickOnHomeButtonAndVerify();
	}
}
