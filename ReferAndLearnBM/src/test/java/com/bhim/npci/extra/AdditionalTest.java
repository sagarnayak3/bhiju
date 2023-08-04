//package com.bhim.npci.extra;
//
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import io.appium.java_client.AppiumBy;
//
//public class AdditionalTest {
//	@Test(enabled = false)
//	public void viewSatement() throws Throwable {
//		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
//				"The homepage is not displayed");
//		home.getTransactionMenu().click();
//		Assert.assertTrue(
//				transactions.getTransactionText().getText().equals(eUtils.readDataFromExcel("Statement", 0, 1)),
//				"The transactions page is not displayed");
//		transactions.getThreeDotOption().click();
//		transactions.getDownload().click();
//		transactions.getFromDate().click();
//		// List<WebElement> allDates = transactions.getAllDates();
//		WebElement dateToClick = driver.findElement(AppiumBy
//				.xpath("//android.view.View[@content-desc='" + eUtils.readDataFromExcel("Statement", 1, 1) + "']"));
//		for (;;) {
//			try {
//				dateToClick.click();
//				break;
//			} catch (Exception e) {
//				transactions.getPreviousButton().click();
//			}
//		}
//	}
//	@Test
//	public void addingFavouriteTest() throws Throwable {
//		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
//				"The homepage is not displayed");
//		home.getManageOption().click();
//		Assert.assertTrue(favourites.getFavourites().getText().equals("Favourites"),
//				"Favourites page is not displayed");
//		
//		Assert.assertTrue(favourites.getAddToFavouritesDialogueBox().getText().equals("Add to favourites"),
//				"The add to favourites dialogue box is not displayed");
//		Assert.assertTrue(favourites.getDisplayedName().getText().equals(eUtils.readDataFromExcel("Favourites", 2, 1)),
//				"The name displayed is wrong");
//		Assert.assertTrue(
//				favourites.getDisplayedNumber().getText().equals(eUtils.readDataFromExcel("Favourites", 1, 1)),
//				"The number displayed is wrong");
//		favourites.getAddToFavouriteButton().click();
//		favourites.getBackButton().click();
//	}

//	@Test
//	public void removeFavourite() throws Throwable {
//		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
//				"The homepage is not displayed");
//		home.getManageOption().click();
//		Assert.assertTrue(favourites.getFavourites().getText().equals("Favourites"),
//				"Favourites page is not displayed");
//		List<WebElement> contacts = favourites.getAllContacts();
//		for (WebElement oneElement : contacts) {
//			if (oneElement.getText().equals(eUtils.readDataFromExcel("Favourites", 1, 1))) {
//				oneElement.click();
//				break;
//			}
//		}
//		Assert.assertTrue(favourites.getProceedPopup().getText().equals(eUtils.readDataFromExcel("Favourites", 7, 1)),
//				"The proceed popup text is incorrect");
//		favourites.getDeleteButton().click();
//		Assert.assertTrue(favourites.getToastElement().getText().equals(eUtils.readDataFromExcel("Favourites", 6, 1)),
//				"The toast message displayed is wrong");
//		
//		for (WebElement oneElement : contacts) {
//			if (oneElement.getText().equals(eUtils.readDataFromExcel("Favourites", 4, 1))) {
//				oneElement.click();
//				break;
//			}
//		}
//		Assert.assertTrue(favourites.getProceedPopup().getText().equals(eUtils.readDataFromExcel("Favourites", 7, 1)),
//				"The proceed popup text is incorrect");
//		favourites.getDeleteButton().click();
//		Assert.assertTrue(favourites.getToastElement().getText().equals(eUtils.readDataFromExcel("Favourites", 6, 1)),
//				"The toast message displayed is wrong");
//		driver.navigate().back();
//	}
//}
