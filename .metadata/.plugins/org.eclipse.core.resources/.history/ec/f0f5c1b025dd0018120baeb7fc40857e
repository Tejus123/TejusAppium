package amazon.assignment.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import amazon.assignment.flow.KindleFlow;
import amazon.assignment.util.HelperPage;

public class KindleTestDuplicateForParallelExecution{
	WebDriver webDriver;
	String testName="KINDLEAMANZON";
	boolean testStatus=false;
	
	@BeforeTest
	public void initateDriver() throws IOException {
		String Broswer = HelperPage.readData(testName,"BROWSER");
		webDriver=HelperPage.setEnvironment(webDriver,Broswer);
		webDriver.manage().window().maximize();
		String url=HelperPage.readData(testName,"URL");
		webDriver.get(url);
	}

	@Test
	public void kindleTest() throws Exception {
		try {
			KindleFlow flow=new KindleFlow(webDriver, testName);
			flow.kindlePaperWhiteFlow();
			testStatus=true;
		}catch(Exception e) {
			throw e;
		}
	}

	@AfterClass
	public void closeBrowser() throws IOException, InterruptedException {
		HelperPage.screenShot(testName,testStatus,webDriver);
	}

}
