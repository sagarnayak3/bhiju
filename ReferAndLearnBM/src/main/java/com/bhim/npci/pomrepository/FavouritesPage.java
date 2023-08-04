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
 * This class contains the locators and business libraries for favourites page
 */
public class FavouritesPage {
	AndroidDriver driver;

	public FavouritesPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Favourites']")
	private WebElement favourites;

	@AndroidFindBy(accessibility = "Add New Favorite")
	private WebElement addNewFavouritesButton;

	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement enterUPIIdTextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='9668440298']")
	private WebElement contactToAddToFavourites;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='confirmToDeclineHeader']")
	private WebElement addToFavouritesDialogueBox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='name']")
	private WebElement displayedName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='vpa']")
	private WebElement displayedNumber;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD']")
	private WebElement addToFavouriteButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SAGAR  SAHOO']")
	private WebElement addedFavourite;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/snackbar_text']")
	private WebElement toastElement;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Delete']")
	private WebElement deleteButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you want to proceed with?']")
	private WebElement proceedPopup;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'double tap to select contact')]/android.widget.TextView")
	private List<WebElement> allContacts;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']")
	private WebElement backButton;

	/**
	 * @author Priyanka
	 * This method is used to verify the favourites page
	 */
	public void verifyFavouritesPage() {
		try {
			Assert.assertTrue(favourites.getText().equals(UtilityObjectClass.getExcelUtility()
					.getDataFromExcel("Test Data", "addingFavourites", "favouritePageTitle")),
					"Favourites page is not displayed");
			UtilityObjectClass.getExtentTest().pass("Favourites page is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().pass("Favourites page is not displayed");
			Assert.fail();
		}
	}

	/**
	 * @author Priyanka
	 * This method is used to verify search for a contact and select that contact
	 * @return
	 */
	public FavouritesPage searchAndSelectContact() {
		addNewFavouritesButton.click();
		enterUPIIdTextField.sendKeys(
				UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "addingFavourites", "contactName"));
		for (WebElement oneContact : allContacts) {
			if (oneContact.getText().contains(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
					"addingFavourites", "contactNumber"))) {
				oneContact.click();
				break;
			}
		}
		return this;
	}

	/**
	 * @author Priyanka
	 * This method is used to verify the add to favourites dialogue box and also payee name and number
	 * @return
	 */
	public FavouritesPage verifyAddToFavouriteDialogueBoxAndAddFavourite() {
		try {
			Assert.assertTrue(
					addToFavouritesDialogueBox.getText()
							.equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data",
									"addingFavourites", "dialogueBoxText")),
					"The add to favourites dialogue box is not displayed");
			UtilityObjectClass.getExtentTest().pass("The add to favourites dialogue box is displayed");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The add to favourites dialogue box is not displayed");
			Assert.fail();
		}
		verifyDisplayedNameAndNumber();
		addToFavouriteButton.click();
		return this;
	}

	/**
	 * @author Priyanka
	 * This method is used to verify the name and number of the payee
	 */
	public void verifyDisplayedNameAndNumber() {
		try {
			Assert.assertTrue(displayedName.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "addingFavourites", "visibleName")),
					"The name displayed is wrong");
			Assert.assertTrue(
					displayedNumber.getText().equals(UtilityObjectClass.getExcelUtility().getDataFromExcel("Test Data", "addingFavourites", "contactNumber")),
					"The number displayed is wrong");
			UtilityObjectClass.getExtentTest().pass("The number and name displayed is correct");
		} catch (Error e) {
			UtilityObjectClass.getExtentTest().fail("The number and name displayed is wrong");
			Assert.fail();
		}
	}
	
	/**
	 * @author Priyanka
	 * This methos is used to navigate to home page
	 */
	public void backNavigation() {
		backButton.click();
		backButton.click();
	}
	
	public void verifyToastMessage() {
			try {
				Assert.assertTrue(toastElement.getText().equals(UtilityObjectClass.getExcelUtility()
						.getDataFromExcel("Test Data", "addingFavourites", "toastMessage")),
						"The toast message received is wrong");
				UtilityObjectClass.getExtentTest().pass("The toast message received is correct");
			} catch (Error e) {
				UtilityObjectClass.getExtentTest().fail("The toast message received is wrong");
				Assert.fail();
			}
		}
}
