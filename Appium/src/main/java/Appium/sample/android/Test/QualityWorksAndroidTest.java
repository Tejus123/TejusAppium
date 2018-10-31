package Appium.sample.android.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import qualityworks.sample.Helpers;
import qualityworks.sample.android.page.AndroidHomePage;
import qualityworks.sample.android.util.AppiumAndroidTest;


public class QualityWorksAndroidTest extends AppiumAndroidTest {
	 private AppiumDriver driver;
	@BeforeTest
	public void checkHomeLabel() throws Exception {
        home.loaded();
        
        
    }
    @Test
    public void checkHomeLabel1() throws Exception {
      
    	  home = new AndroidHomePage();
          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setCapability("appium-version", "1.6.5");
          capabilities.setCapability("platformName", "android");
          capabilities.setCapability("deviceName", "emulator-5554");
          capabilities.setCapability("appPackage", "com.qualityworkscg.qualityworkssampleandroid");
          capabilities.setCapability("appActivity", ".HomeActivity");

          // Set job name
          capabilities.setCapability("name", "Quality Works Android Sample" + date);
          String appPath = System.getenv("android_app_path");
          assert appPath != null: "Path to Android app is not set";
          System.out.println("Android App path: "+ appPath);
          capabilities.setCapability("app", appPath);
          driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
          Helpers.init(driver);
        
    }
}
