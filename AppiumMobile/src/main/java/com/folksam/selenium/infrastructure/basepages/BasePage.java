package com.folksam.selenium.infrastructure.basepages;

import com.folksam.selenium.infrastructure.utils.AutomationConfigurator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Base page contains the reusable methods which can be used across all the pages
 *
 * @author Dragan Spiric
 */
public class BasePage {

	private int default_timeout =30;
	private  WebDriver driver;
	private  Logger LOGGER = null;

	public BasePage(WebDriver driver) {
		LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
		this.driver = driver;
	}
	
	/**
	 * Method to get the explicit wait time from Properties file: default value =30 sec
	 * @return TimeOut
	 * @throws IOException
	 */

	public int explicitWait(){
		String time_out = null;
		try {
			time_out = new AutomationConfigurator().getTimeOut();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (time_out == null){
			return default_timeout;
		}
		else
		{
			return Integer.parseInt(time_out);
		}
	}

	/**
	 * |This method is used to wait for an element to be present
	 * @param locator which should locate the element
	 * @param timeOut The maximum time to wait for an element
	 * @return The located WebElement
	 * @throws IllegalArgumentException when the driver param is null
	 * @throws NoSuchElementException when element is couldn't be located
	 */
	public WebElement waitForElement(By locator,  int timeOut)
	{
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
	}

	/**
	 * This method is used to wait for an element to be present with default wait time 30sec
	 * @param locator which should locate the element
	 * @return The located WebElement
	 * @throws IllegalArgumentException when the driver param is null
	 * @throws NoSuchElementException when element couldn't be located
	 * @throws IOException when Automation configuration file is not present
	 */
	public WebElement waitForElement(By locator) {
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}
		return waitForElement(locator,explicitWait());
	}

	/**
	 * Method to wait for particular element with specific condition in the given time of interval
	 * @param elementExpectedCondition A valid expected condition statement
	 * @param timeOut The maximum time to wait for an element
	 * @return The located WebElement
	 * @throws IllegalArgumentException when the driver param is null
	 * @throws NoSuchElementException when element couldn't be located
	 */
	public WebElement waitForElement(final ExpectedCondition<WebElement> elementExpectedCondition,  int timeOut)
	{
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.refreshed(elementExpectedCondition));
	}

	/**
	 * Method to wait for particular element with specific condition with in the default time 30 seconds
	 * @param elementExpectedCondition A valid expected condition statement
	 * @return The located WebElement
	 * @throws IllegalArgumentException when the driver param is null
	 * @throws NoSuchElementException when element couldn't be located
	 * @throws IOException when Automation configuration file is not present
	 */
	public WebElement waitForElement(final ExpectedCondition<WebElement> elementExpectedCondition)  {

		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}

		return waitForElement(elementExpectedCondition,default_timeout);
	}



	/**
	 * Method to find element on the webpage
	 * @param locator
	 * @throws Exception
	 */


	public WebElement findElement(By locator){
		WebElement element = null;
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}

		try {
			waitForElement(locator);
			waitForElement(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
			waitForElement(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
			element = driver.findElement(locator);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new NotFoundException("Element with locator ["+ locator.toString() +"] was not found.");
		}
		return element;
	}

	
	
	/**
	 * Method to find element on the webpage
	 * @param locator
	 * @throws Exception
	 */


	public WebElement findElementClickable(By locator){
		WebElement element = null;
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}

		try {

			waitForElement(locator);
			waitForElement(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
			element = driver.findElement(locator);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			return element;

		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new NotFoundException("Element with locator ["+ locator.toString() +"] was not found.");

		}
	}



	
	/**
	 * Method to verify whether an element is displayed
	 * @param locator
	 * @return
	 */
	public  boolean isElementDisplayed(By locator) {
		try {
			findElement(locator);
		}catch(Exception e)
		{
			LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
			return false;
		}
		return true;
	}

	
    /**
     * Method to enter text into text box
     * @param locator
     * @param text
     * @throws Exception
     */
    public void enterText(final By locator, String text){
        WebElement element = findElementClickable(locator);
		try {
        	        	
            if(text.contains("@"))
            {
                element.clear();
                waitForElement(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
                String[] parts=text.split("@");
                String selectAll = Keys.chord(Keys.CONTROL,Keys.ALT,"2");
                for(int i=0; i<parts.length;i++) {
                	driver.findElement(locator).sendKeys(parts[i]);

                    if(i!=(parts.length-1)) {
                        driver.findElement(locator).sendKeys(selectAll);

                    }
                }

            }else {
                driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL,"a"),text);
            }
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");

        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while entering "+text+" using locator "+locator);
		
        }



    }

    /**
     * Method to enter text to text box using java script
     * @param locator
     * @param text
     * @throws Exception
     */

    public  void enterValue(final By locator, String text){
		WebElement element = findElementClickable(locator);

        try {
        	element.clear();
            element = driver.findElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].value='256';", element);
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");

        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            
				throw new NotFoundException("Exception "+ e +" thrown while entering "+text+" using locator "+locator);
		
        }
    }

    


    /**
     * Method to click on Link or button or check box or radio button
     * @param locator
     * @throws Exception
     */
    public  void click(By locator){
		WebElement element = findElementClickable(locator);

		try {

            try {
                element.click();
            }catch(StaleElementReferenceException e)
            {
				element = findElementClickable(locator);
				element.click();
            }
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while clicking  on object using locator "+locator);
			
				
				
			}
        }

	/**
	 * Method to click on a element using javascript excecutor
	 * @param locator
	 */

	public void click_JS(By locator)
		{
			WebElement element = findElementClickable(locator);
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				LOGGER.info("Step : " + Thread.currentThread().getStackTrace()[2].getMethodName() + ": Pass");
			}catch(Exception e)
			{
				LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
				throw new NotFoundException("Exception "+ e +" thrown while clicking  on object using locator "+locator);

			}

		}


    /**
     * Method to double click on a particular element
     * @param locator
     * @throws Exception
     */
    public  void doubleClick(By locator){

		WebElement element = findElementClickable(locator);
        try {

            try {
                Actions action = new Actions(driver);
                action.moveToElement(element).doubleClick().perform();
            }catch(StaleElementReferenceException e)
            {
				element = findElementClickable(locator);
                Actions action = new Actions(driver);
                action.moveToElement(element).doubleClick().perform();
            }
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while double clicking on object using locator "+locator);
		
        }

    }

    /**
     * Method to select the check box
     * @param locator
     * @throws Exception
     */
    public  void selectCheckBox(By locator,String option){

		WebElement element = findElementClickable(locator);
        try {

            if(option.equalsIgnoreCase("ON")) {
                if (!element.isSelected()) {
                    element.click();
                }
            }
            else if(option.equalsIgnoreCase("OFF"))
            {
                if(element.isSelected())
                {
                    element.click();
                }
            }
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while setting  the checkbox  "+option+" using locator "+locator);

			}
        }

    

    /**
     * Method to select a value from drop down by index
     * @param locator
     * @param index
     * @throws Exception
     */
    public void selectByindex(By locator,int index)  {

		WebElement element = findElementClickable(locator);

        try {

            Select select=new Select(element);
            select.selectByIndex(index);
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while selecting  the value by index  "+index+" using locator "+locator);
	
        }


    }

    /**
     * Method to select a value from drop down by visible text
     * @param locator
     * @param text
     * @throws Exception
     */
    public  void selectByText(final By locator,String text){
		WebElement element = findElementClickable(locator);

        try {

            try {
                Select select = new Select(element);
                select.selectByVisibleText(text);
            }catch(StaleElementReferenceException e){

            	element = findElementClickable(locator);
                Select select = new Select(element);
                select.selectByVisibleText(text);

            }

            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("Exception "+ e +" thrown while selecting  the value by text  "+text+" using locator "+locator);
	
        }


    }

    /**
     * Method to select a value from drop down
     * @param locator
     * @param value
     * @throws Exception
     */
    public  void selectByValue(By locator,String value){

		WebElement element = findElementClickable(locator);

        try {

            Select select=new Select(element);
            select.selectByValue(value);
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("There is an error while selecting the value "+value+": with locator "+locator+". Exception "+e);
	
				
				
			}
        }


    

    /**
     * Method to select a value from drop down by providing partial text
     * @param locator
     * @param partialText
     * @throws Exception
     */

    public  void selectByPartialText(By locator,String partialText){
		WebElement element = findElementClickable(locator);

        try {

            Select select=new Select(element);
            List<WebElement> optionsList = select.getOptions();
            for (int i=0; i<optionsList.size(); i++)
            {
                if(optionsList.get(i).getText().contains(partialText))
                {
                    optionsList.get(i).click();
                }
            }
            LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass");
        }catch(Exception e)
        {
            LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail");
            //e.printStackTrace();
            throw new NotFoundException("There is an error while selecting the text "+partialText+": with locator "+locator+". Exception "+e);
			
        }


    }

    /**
     * Method to get the options from dropdown
     * @param locator
     * @throws Exception
     */

    public  ArrayList<String> getOptions(By locator){
        ArrayList<String> options = new ArrayList<String>();
		WebElement element = findElementClickable(locator);
        try {

            Select select = new Select(element);
            List<WebElement> optionsList = select.getOptions();
            if(optionsList.size() == 1)
            	options.add(optionsList.get(0).getText().trim());
            else {
				for (int i = 1; i < optionsList.size(); i++) {
					options.add(optionsList.get(i).getText().trim());
				}
			}
            LOGGER.info("Step : " + Thread.currentThread().getStackTrace()[2].getMethodName() + ": Pass");
            return options;

        } catch (Exception e) {
            LOGGER.error("Step : " + Thread.currentThread().getStackTrace()[2].getMethodName() + ": Fail");
            //e.printStackTrace();
            throw new NotFoundException("There is an error while getting the options: with locator " + locator + ". Exception " + e);
	
        }


    }

	/**
	 * Method to get the specific text from webpage
	 * @param locator
	 * @return Text from WebPage
	 * @throws Exception
	 */
	public String getText(By locator){
		String reqText = null;
		WebElement element = findElement(locator);

		try {
			reqText = element.getText();
			LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass and value = "+reqText);
			return reqText;

		} catch (Exception e) {
			LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail ");
			  //e.printStackTrace();
			  throw new NotFoundException("Element is not present with locator: "+locator);
		}
	}

	/**
	 * Method to get the attribute of an object
	 * @param locator
	 * @param attribute
	 * @return attribute value
	 * @throws Exception
	 */

	public  String getAttribute(By locator, String attribute){
		String reqAttribute = null;

		WebElement element = findElement(locator);
		try {

			reqAttribute = element.getAttribute(attribute);
			LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass and value = "+reqAttribute);
			return reqAttribute;

		} catch (Exception e) {
			LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail ");
			//e.printStackTrace();
			throw new NotFoundException("Element is not present with locator: "+locator);
	
		}

	}

    /**
     * Method to enter down arrow
     */
    public void keyDown() {

        driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
        driver.switchTo().activeElement().sendKeys( Keys.ENTER);


    }

    /**
     * Method to click on TAB
     */
    public  void keyTab(){

        try {
            driver.switchTo().activeElement().sendKeys(Keys.TAB);
			LOGGER.info("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Pass ");

        }catch(Exception e)
        {
			LOGGER.error("Step : "+Thread.currentThread().getStackTrace()[2].getMethodName()+": Fail ");
			//e.printStackTrace();
			throw new NotFoundException("Exception while clicking on Tab button");
		
        }
    }

    /**
     * WaitFor particular attribute
     * @param locator
     * @param attribute
     * @throws Exception 
     */
    public void waitForAttributeNotEmpty(final By locator, final String attribute)
    {
        WebElement element = findElement(locator);
        WebDriverWait wait=new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.attributeToBeNotEmpty(element,attribute)));

    }

    /**
	 * Method to switch to frame using index
	 * @param index
	 */
	public void switchToFrame(int index){

		try {
			driver.switchTo().frame(index);
			LOGGER.info("Step : Switched to Frame with index "+index+" :Pass");
		}catch(Exception e)
		{
			LOGGER.error("Step : Switched to Frame with index "+index+" :Fail");
			//e.printStackTrace();
			throw new NotFoundException("Exception while switching to frame");
			}
		}


	/**
	 * Method to switch to frame using name or id
	 * @param name
	 */

	public void switchToFrame(String name){

		try {
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
			LOGGER.info("Step : Switched to the frame with name"+name+" :Pass");
			}catch(Exception e)
		{
			LOGGER.error("Step : Switched to Frame frame with name"+name+" :Fail");
			//e.printStackTrace();
			throw new NotFoundException("Exception while switching to frame");
			}
		}


	/**
	 * Method to switch to frame using locator
	 * @param locator
	 */
	public void switchToFrame(By locator){

		try {
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			LOGGER.info("Step : Switch to the frame with locator"+locator+" :Pass");
		}catch(Exception e)
		{
			LOGGER.error("Step : Switch to  the frame with locator"+locator+" :Fail");
			//e.printStackTrace();
			throw new NotFoundException("Exception while switching to frame");
		}
	}

	/**
	 * Method to return the current active frame in page
	 * @return Frame name
	 */
	public String getCurrentFrameName()
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		return (jsExecutor.executeScript("return self.name")).toString();
	}

	/**
	 * Method to switch to default frame
	 */
	public void switchToDefault()
	{
		driver.switchTo().defaultContent();
		LOGGER.info("Step : Switched to default frame");
	}

	/**
	 * Method to switch to a recently openend window
	 */
	public  void switchToNewlyOpenedWindow(){

		try {
			Thread.sleep(1000);
			driver.getWindowHandles();
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
			}
			LOGGER.info("Step : Switching to New Window : Pass");
		} catch (InterruptedException e) {
			LOGGER.error("Step : Switching to New Window : Fail");
			//e.printStackTrace();
			throw new NotFoundException("Exception while switching to newly opened window");
		}
	}

	/**
	 * Method to switch to Window by passing window handle
	 * @param handle
	 */
	public void switchToWindow(String handle)
	{
		try {
			driver.switchTo().window(handle);
		}catch(Exception e)
		{
			LOGGER.error("Step : Switching to New Window : Fail");
			//e.printStackTrace();
			throw new NotFoundException("Exception while switching to window");

		}
	}


	/**
	 * Method to return current window handle
	 * @return
	 */
	public  String getCurrentHandle()
	{
		return driver.getWindowHandle();
	}


	/**
	 * Method to take the screenshots
	 * @param message
	 */
	public  void takeScreenshot(String message) {
		File scrFile = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/target/screenshots/" + message + ".png"));
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * Method to get the page title
	 * @return
	 */
	public String getPageTitle()
	{
		return driver.getTitle();
	}

	/**
	 * Wait For element to be invisible
	 * @param locator
	 * @param timeOut
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NoSuchElementException
	 */
	public boolean waitForInvisibleElement(By locator, int timeOut)
	{
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(locator)));
	}
	/**
	 * Wait for element to be clickable
	 * @param locator
	 * @param timeOut
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NoSuchElementException
	 */
	public WebElement waitForClickable(By locator, int timeOut)
	{
		if(driver == null){
			throw new IllegalArgumentException("WebDriver cannot be null");
		}
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
	}

}

