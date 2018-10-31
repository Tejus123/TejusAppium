package amazon.assignment.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class KindlePaperWhiteHomePage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By QUANTITY=By.id("quantity");
	private final By ADDTOCART=By.id("add-to-cart-button");
	
	private final By CLOSE=By.xpath("//button[@aria-label='Close']");
	public KindlePaperWhiteHomePage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}
	
	/**
	 * method to select Quantity Dropdown
	 * @return
	 * @throws IOException
	 */
	public KindlePaperWhiteHomePage selectQuantity() throws IOException {
		Select select=new Select(webDriver.findElement(QUANTITY));
		select.selectByVisibleText(HelperPage.readNumData(testName, "QUANTITY"));
		return this;
	}	
	
	/**
	 * method to click Add To Cart
	 * @return
	 * @throws IOException
	 */
	public KindlePaperWhiteHomePage clickAddToCart() throws IOException {
		webDriver.findElement(ADDTOCART).click();
		return this;
	}
	
	/**
	 * method to click Close
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public KindlePaperWhiteHomePage clickClose() throws IOException, InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor)webDriver;
		WebElement element=webDriver.findElement(CLOSE);
		js.executeScript("arguments[0].click();", element);
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(ADDTOCART);
	}
	
}
