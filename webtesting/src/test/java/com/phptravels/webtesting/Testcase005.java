package com.phptravels.webtesting;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Commons;
import helper.GetProperties;
import helper.LoadXLSX;
import helper.RegisterPageFile;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class Testcase005 {
	private WebDriver driver;
	private LoadXLSX excel;
	private RegisterPageFile rpf;
	private GetProperties getproperty;
	private Commons commons;
	private ExtentTest test;
	private ExtentReports report;

	@DataProvider(name = "loginData")
	private Object[][] dataprovider() {
		Object[][] testData = excel.getTestData("Invalid_Email");
		return testData;
	}

	@Test(dataProvider = "loginData")
	public void testingInvalidEmailAddress(String firstname, String lastname, String password, String email, String phone)
			throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		rpf = new RegisterPageFile(driver);
		boolean check = false;
		driver.get("https://www.phptravels.net/register");
		rpf.sendKeysToLocator(firstname, rpf.firstNameLocator);
		rpf.sendKeysToLocator(lastname, rpf.lastNameLocator);
		rpf.sendKeysToLocator(password, rpf.passwordLocator);
		rpf.sendKeysToLocator(password, rpf.confirmPasswordLocator);
		rpf.sendKeysToLocator(email, rpf.emailLocator);
		rpf.sendKeysToLocator(phone, rpf.phoneLocator);
		rpf.clickSubmit();
		check = rpf.errorMessageFor("email");
		Assert.assertEquals(check, true);
	}

	@BeforeTest
	public void setup() throws FileNotFoundException, IOException {
		commons = new Commons();
		report = new ExtentReports(System.getProperty("user.dir") + "//reports//" + this.getClass().getSimpleName() + "-" + "_" + commons.getRandomString(5) + ".html");
		test = report.startTest(this.getClass().getSimpleName());
		getproperty = new GetProperties();
		getproperty.loadPropertiesFile("resources/test.properties");
		excel = new LoadXLSX();
		excel.loadXlsx();
		System.setProperty("webdriver.chrome.driver", getproperty.getPropertyValue("webdriver.chrome.driver"));

	}

	@AfterMethod(alwaysRun = true)
	public void cleanup(ITestResult testResult) throws IOException {
		commons = new Commons();
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String filename = this.getClass().getSimpleName() + "-" + testResult.getMethod().getMethodName() + "_" + commons.getRandomString(5) + ".png";
			String directory = System.getProperty("user.dir") + "//screenshots//";
			File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(directory + filename));
			String imagepath = test.addScreenCapture(directory + filename);
			test.log(LogStatus.FAIL, testResult.getMethod().getMethodName(), imagepath);
		}
		else{
			test.log(LogStatus.PASS, testResult.getMethod().getMethodName());
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
