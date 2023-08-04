package com.bhim.npci.endtoend;

import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;
/**
 * @author Priyanka
 * This class contains the methods associated with the home module
 */
public class HomeTest extends BaseClass {

	/**
	 * Profiles->Accounts->Balance->Accounts->Verify
	 */
	@Test
	public void checkingBalanceAndVerifyTest() {
		home.clickOnProfileMenuAndVerify()
			.clickOnAccountsAndVerify()
			.checkAndInsertBalanceIntoExcel()
			.clickOnAccountAndVerify()
			.verifyingBalanceAndBackToHome();
	}
}
