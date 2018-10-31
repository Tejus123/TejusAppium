package amazon.assignment.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class AmazonLoginPage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By LABELTEXT=By.xpath("//label[@for='ap_email']");
	private final By SIGNINBOX=By.id("ap_email");
	
	public AmazonLoginPage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}

	
	
	/**
	 * method to verify Sign in Label
	 * @return
	 */
	public  AmazonLoginPage  verifySigninText()  {
		try
		{
			Assert.assertEquals("E-mail address or mobile phone number", webDriver.findElement(LABELTEXT).getText().trim());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	
	/**
	 * method to verify Sign in Box
	 * @return
	 */
	public  AmazonLoginPage  verifySigninBox()  {
		try
		{
			Assert.assertTrue("Verify Sign Box is not displayed", webDriver.findElement(SIGNINBOX).isDisplayed());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(SIGNINBOX);
	}
	
}
