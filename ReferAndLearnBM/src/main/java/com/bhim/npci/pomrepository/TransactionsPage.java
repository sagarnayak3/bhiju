package com.bhim.npci.pomrepository;

import static com.bhim.npci.genericutility.UtilityObjectClass.getDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**
 * @author piyus
 * This class contains the locators and business libraries for transactions page
 */
public class TransactionsPage {
	AndroidDriver driver;

	public TransactionsPage() {
		this.driver = getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Transactions']")
	private WebElement transactionText;
	
	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='More Options, Double tap to Explore'])[2]")
	private WebElement threeDotOption;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Download Transactions']")
	private WebElement download;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='From']/following-sibling::android.view.ViewGroup//android.widget.TextView")
	private WebElement fromDate;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='To']/following-sibling::android.view.ViewGroup//android.widget.TextView")
	private WebElement toDate;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='DOWNLOAD']")
	private WebElement downloadButton;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Previous month']")
	private WebElement previousButton;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,' ')]")
	private List<WebElement> allDates;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private WebElement okButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chrome']/ancestor::android.widget.LinearLayout[@resource-id='oplus:id/resolver_item_layout']")
	private WebElement chromeOption;
	
	@FindBy(xpath = "//u[text()='Transaction History']")
	private WebElement transactionHistoryText;
	
	@AndroidFindBy(xpath = "//android.view.View[@checked='true' or @checked='false']")
	private WebElement checkedDate;

}
