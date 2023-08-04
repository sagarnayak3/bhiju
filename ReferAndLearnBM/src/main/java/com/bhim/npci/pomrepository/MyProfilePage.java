package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.ExcelUtility;
import com.bhim.npci.genericutility.GestureUtility;
import com.bhim.npci.genericutility.UtilityObjectClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for my profile page
 */
public class MyProfilePage {
	AndroidDriver driver;

	public MyProfilePage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to view all accounts')]")
	private WebElement accountsMenu;

	@AndroidFindBy(accessibility = "Favourites, double tap to view or modify favourites")
	private WebElement favouritesMenu;

	@AndroidFindBy(xpath = "//android.view.View[@text='My Profile']")
	private WebElement myProfile;

	/**
	 * @author piyus
	 * This method is used to scroll the page and click on accounts
	 * @param gestureUtility
	 * @param eUtils
	 * @return 
	 * @throws Throwable
	 * @throws IOException
	 */
	public BankAccountsPage clickOnAccountsAndVerify() {
//		UtilityObjectClass.getGestureUtility().scrollTillElement(driver,
//				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "elementText"));
		accountsMenu.click();
		BankAccountsPage accounts =new BankAccountsPage();
		accounts.verifyBankAccountsPage();
		return accounts;
	}

	/**
	 * @author piyus
	 * This method is used to verify the my profile page
	 */
	public void verifyProfilePage() {
		try {
			Assert.assertTrue(
					myProfile.getText().equals(
							UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "profilePageTitle")),
					"My profile page is not displayed");
			UtilityObjectClass.getExtentTest().pass("My profile page is displayed");
		} catch (Exception e) {
			UtilityObjectClass.getExtentTest().fail("My profile page is not displayed");
			Assert.fail();
		}
	}
	
	/**
	 * @author piyus
	 * This method is used to click on favourites and verify that page
	 * @return
	 */
	public FavouritesPage clickOnFavouritesAndVerify() {
		UtilityObjectClass.getGestureUtility().scrollTillElement(driver,
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "addingFavourites", "elementText"));
		favouritesMenu.click();
		FavouritesPage favourites = new FavouritesPage();
		favourites.verifyFavouritesPage();
		return favourites;
	}
}
