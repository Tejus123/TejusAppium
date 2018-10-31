package com.folksam.selenium.infrastructure.basetests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AppiumMobile.Appium.URL;
import amazon.assignment.flow.KindleFlow;
import amazon.assignment.util.HelperPage;
import io.appium.java_client.android.AndroidDriver;

public class KindleTest_Mobile{
	private static AndroidDriver driver;
	String testName="KINDLEAMANZON";
	boolean testStatus=false;
	
	@BeforeTest
	public void initateDriver() throws IOException {
		String Broswer = HelperPage.readData(testName,"BROWSER");
		webDriver=HelperPage.setEnvironment(webDriver,Broswer);
		webDriver.manage().window().maximize();
		String url=HelperPage.readData(testName,"URL");
		webDriver.get(url);
		
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/Apps/Amazon/");
		File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Micromax A311");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}

	@Test
	public void kindleTest() throws Exception {
		try {
		
			
 
			
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.quit();
 
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
