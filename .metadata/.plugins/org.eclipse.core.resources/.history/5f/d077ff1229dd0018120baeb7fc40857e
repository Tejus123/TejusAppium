package amazon.assignment.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class ShopByDepartmentPage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By KINDLE=By.xpath("//a[text()='Kindle']");
	public ShopByDepartmentPage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}
	
	/**
	 * method to click Kindle
	 * @return
	 * @throws IOException
	 */
	public ShopByDepartmentPage clickKindle() throws IOException {
		webDriver.findElement(KINDLE).click();
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(KINDLE);
	}	
}
