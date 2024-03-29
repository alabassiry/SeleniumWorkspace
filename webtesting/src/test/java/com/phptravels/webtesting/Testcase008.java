package com.phptravels.webtesting;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Commons;

import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class Testcase008 {
	private URL url;
	private HttpURLConnection httpcon;
	private ExtentTest test;
	private ExtentReports report;
	private Commons commons;
	
	@Test
	public void testingHttpResponse() throws IOException, IllegalArgumentException{ 
		InputStream in = httpcon.getInputStream();
		String encoding = httpcon.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		FileUtils.writeStringToFile(new File("./HTTPResponses/Testcase008.xml"), body);
	}

	@BeforeTest
	public void setup() throws FileNotFoundException, IOException {
		url  = new URL("https://www.phptravels.net/register");
		httpcon = (HttpURLConnection) url.openConnection(); 
		httpcon.addRequestProperty("User-Agent", "Mozilla/4.76"); 
	}

	@AfterMethod(alwaysRun = true)
	public void cleanup(ITestResult testResult) throws IOException {
		commons = new Commons();
		report = new ExtentReports(System.getProperty("user.dir") + "//reports//" + this.getClass().getSimpleName() + "-" + "_" + commons.getRandomString(5) + ".html");
		test = report.startTest(this.getClass().getSimpleName());
		if (testResult.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, testResult.getMethod().getMethodName());
		}
		else{
			test.log(LogStatus.PASS, testResult.getMethod().getMethodName());
		}
		report.endTest(test);
		report.flush();
	}
	
}
