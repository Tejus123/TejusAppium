package amazon.assignment.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon.assignment.util.HelperPage;
import amazon.assignment.util.WebBasePage;

public class KindleHomePage extends WebBasePage{
	private WebDriver webDriver=null;
	private String testName="";
	private final By KINDLEPAPERWHITE=By.xpath("//img[@class='kfs-img']");
	public KindleHomePage(WebDriver webDriver, String testName) {
		this.webDriver=webDriver;
		this.testName=testName;
		waitForPageLoad();
	}
	
	/**
	 * method to click Kindle
	 * @return
	 * @throws IOException
	 */
	public KindleHomePage clickKindlePaperWhite() throws IOException {
		List<WebElement> ImgWhite = webDriver.findElements(KINDLEPAPERWHITE);
		String text=HelperPage.readData(testName,"KINDLETYPE");
		if (text.equalsIgnoreCase("PaperWhite"))
		{
			ImgWhite.get(1).click();
		}
		else if (text.equalsIgnoreCase("Kindle"))
		{
			ImgWhite.get(0).click();
		}
		else  
		{
			ImgWhite.get(2).click();
		}
		
		return this;
	}

	@Override
	public void waitForPageLoad() {
		HelperPage.waitForPageLoad(KINDLEPAPERWHITE);
	}	
}
