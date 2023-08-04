package com.bhim.npci.genericutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bhim.npci.pomrepository.HomePage;
import com.bhim.npci.pomrepository.PasscodePage;

/**
 * @author Priyanka
 * This class is used to capture the run time events
 */
public class ListenerImpClass implements ITestListener{
	ExtentReports report;
	ExtentTest test;

	/**
	 * @author Priyanka
	 * This method is used to create the execution report and login to application in the start of test
	 */
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		UtilityObjectClass.setExtentTest(test);
		PasscodePage passCode=new PasscodePage();
		passCode.verifyPasscodePageHeader();
		passCode.loginToApp(UtilityObjectClass.getFileUtility().readDataFromPropertyFile("passCode"));
		HomePage home = new HomePage();
		WebDriverWait wait = new WebDriverWait(UtilityObjectClass.getDriver(), Duration.ofSeconds(10));
		home.verifyHomePage();
		Reporter.log(testName + "--------TEST SCRIPT EXECUTION STARTED");
	}

	/**
	 * @author Priyanka
	 * This method is used to log pass status in execution report on test success
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.PASS, testName + "---------PASS");
		Reporter.log(testName + "--------EXECUTED SUCCESSFULLY");
		
	}

	/**
	 * @author Priyanka
	 * This method is used to log fail status and attach a screenshot to extent report on test failure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.FAIL, testName + "---------FAIL");
		test.log(Status.INFO, result.getThrowable());
		Reporter.log(testName + "--------EXECUTION FAILED");
		TakesScreenshot ts = (TakesScreenshot) UtilityObjectClass.getDriver();
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(
				"./errorShots/" + testName + LocalDateTime.now().toString().replace(":", "_") + ".png");
		try {
			FileUtils.copyFile(src, dest);
			test.addScreenCaptureFromPath(dest.getAbsolutePath());
		} catch (Exception e) {

		}
	}

	/**
	 * @author Priyanka
	 * This method is used to log skipped status to extent report on skipped tests
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(testName + "----------EXECUTION SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	/**
	 * @author Priyanka
	 * This method is used to create the file of execution report in framework and customize that
	 */
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./Extent Reports/"+LocalDateTime.now().toString().replace(':', '_')+".html");
		htmlReport.config().setDocumentTitle("Bhim Document");
		htmlReport.config().setReportName("Bhim Extent Report");
		htmlReport.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("Platform Name", "Android");
		report.setSystemInfo("Device Name", "OnePlus 7 Pro");
	}

	/**
	 * @author Priyanka
	 * This method is used for flushing everything into the report
	 */
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
