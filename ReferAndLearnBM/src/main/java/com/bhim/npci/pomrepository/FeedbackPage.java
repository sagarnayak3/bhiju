package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Priyanka
 * This class contains the locators and business libraries for feedback page
 */
public class FeedbackPage {
	AndroidDriver driver;

	public FeedbackPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Send Feedback']")
	private WebElement sendFeedbackText;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='SEND']")
	private WebElement sendButton;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement feedbackTextField;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/snackbar_text']")
	private WebElement toastElement;
	
	
}
