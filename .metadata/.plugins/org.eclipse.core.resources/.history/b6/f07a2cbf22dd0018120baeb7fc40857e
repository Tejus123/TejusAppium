package amazon.assignment.flow;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import amazon.assignment.pages.AmazonHomePage;
import amazon.assignment.pages.AmazonLoginPage;
import amazon.assignment.pages.CartVerifyPage;
import amazon.assignment.pages.KindleHomePage;
import amazon.assignment.pages.KindlePaperWhiteHomePage;
import amazon.assignment.pages.ShopByDepartmentPage;

public class KindleFlow {
	private WebDriver webDriver;
	private String testName = "";

	public KindleFlow(WebDriver webDriver, String testName) {
		this.webDriver = webDriver;
		this.testName = testName;
	}
	
	/**
	 * Method for Kindle PaperWhite add to cart flow 
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void kindlePaperWhiteFlow() throws IOException, InterruptedException{
		AmazonHomePage homepage=new AmazonHomePage(webDriver, testName);
		homepage.clickShopByDepartment();
		ShopByDepartmentPage department=new ShopByDepartmentPage(webDriver, testName);
		department.clickKindle();
		KindleHomePage kindlePage=new KindleHomePage(webDriver, testName);
		kindlePage.clickKindlePaperWhite();
		KindlePaperWhiteHomePage kindlePaperWhitePage=new KindlePaperWhiteHomePage(webDriver, testName);
		kindlePaperWhitePage.selectQuantity().clickAddToCart().clickClose();
		CartVerifyPage cart=new CartVerifyPage(webDriver, testName);
		cart.clickProceedToCheckout();
		AmazonLoginPage login=new AmazonLoginPage(webDriver, testName);
		login.verifySigninText().verifySigninBox();
	}


}
