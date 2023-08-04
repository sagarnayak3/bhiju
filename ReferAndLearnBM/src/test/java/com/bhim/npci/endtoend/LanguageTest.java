package com.bhim.npci.endtoend;

import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;

/**
 * @author Priyanka
 * This class contains all methods associated with the Language module
 */
public class LanguageTest extends BaseClass {

	/**
	 * SelectLanguage->Verify
	 */
	@Test
	public void verifyLanguageTest() {
		home.goToChangeLanguagePageAndVerify()
			.selectingLanguageAndProceed()
			.verifyLanguageChange()
			.selectingPrimaryLanguageAndProceed();
	}
}
