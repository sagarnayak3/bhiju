/*
 * //package com.bhim.npci.endtoend; // //import org.testng.Assert; //import
 * org.testng.annotations.Listeners; //import org.testng.annotations.Test; //
 * //import com.bhim.npci.genericutility.BaseClass;
 * //@Listeners(com.bhim.npci.genericutility.ListenerImpClass.class) // //public
 * class FeedbackTest extends BaseClass { // // @Test // public void
 * sendFeedback() throws Throwable { //
 * Assert.assertTrue(home.getBankName().getText().equals(eUtils.
 * readDataFromExcel("Home", 0, 1)), // "The homepage is not displayed"); //
 * home.getMoreOption().click(); // home.getSendFeedback().click(); //
 * Assert.assertTrue(feedback.getSendFeedbackText().getText().equals(eUtils.
 * readDataFromExcel("Feedback", 0, 1)), // "Feedback page is not displayed");
 * //
 * feedback.getFeedbackTextField().sendKeys(eUtils.readDataFromExcel("Feedback",
 * 1, 1)); // feedback.getSendButton().click(); //
 * webUtils.explicitWaitForVisibility(wait, favourites.getToastElement()); //
 * Assert.assertTrue(favourites.getToastElement().getText().equals(eUtils.
 * readDataFromExcel("Feedback", 2, 1)), //
 * "The toast message is not received"); // } //} package com;
 * 
 * 
 */