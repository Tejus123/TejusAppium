package amazon.assignment.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class AmazonHomePage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By SHOPBYDEPARTMENT=By.id("nav-link-shopall");
	
	public AmazonHomePage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}
	
	/**
	 * method to click Shop By Department
	 * @return
	 * @throws IOException
	 */
	public AmazonHomePage clickShopByDepartment() throws IOException {
		webDriver.findElement(SHOPBYDEPARTMENT).click();
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(SHOPBYDEPARTMENT);
	}

	
}
