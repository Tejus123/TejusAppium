package amazon.assignment.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class CartVerifyPage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By PROCEEDTOCHECKOUT=By.xpath("//a[@class='hucSprite s_checkout hlb-checkout-button']");
	
	public CartVerifyPage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}
	
	/**
	 * method to click Proceed TO Checkout
	 * @return
	 * @throws IOException
	 */
	public CartVerifyPage clickProceedToCheckout() throws IOException {
		webDriver.findElement(PROCEEDTOCHECKOUT).click();
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(PROCEEDTOCHECKOUT);
	}	
}
