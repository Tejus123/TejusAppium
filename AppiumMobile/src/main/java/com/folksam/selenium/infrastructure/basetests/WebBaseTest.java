package com.folksam.selenium.infrastructure.basetests;

import com.folksam.selenium.infrastructure.handler.ExcelDataHandler;
import com.folksam.selenium.infrastructure.utils.AutomationConfigurator;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * WebBaseTest which contains reusable methods to be used in Tests like getDriver etc..
 *
 * @author KSGA03
 */
public abstract  class WebBaseTest {

    private static final int default_implicit_wait = 10;

    private static final String DEFAULT_IE_DRIVER_PATH = "../SELENIUM_RESOURCES/drivers/IEDriverServer.exe";
    private static final String DEFAULT_CHROME_DRIVER_PATH = "../SELENIUM_RESOURCES/drivers/chromedriver.exe";
    private static final String DEFAULT_GECKO_DRIVER_PATH="../SELENIUM_RESOURCES/drivers/geckodriver.exe";

    private static final String IE_SYSTEM_PROPERTY = "webdriver.ie.driver" ;
    private static final String CHROME_SYSTEM_PROPERTY = "webdriver.chrome.driver" ;
    private static final String FIREFOX_SYSTEM_PROPERTY = "webdriver.gecko.driver";


    //declare the driver
    WebDriver driver;

    //declare the exceldatahandler, rowCount and rowNo
    ExcelDataHandler excelDataHandler;

    //declare environment variable
    String environment = null;

    //declare the logger for the testclass
    private Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());



    /**
     * Method to get the driver based on the browser from testng xml or properties file
     * @return WebDriver based on the browser passed from testngxml/properties file
     */
    public WebDriver getDriver(){

        try {

            AutomationConfigurator configurator=new AutomationConfigurator();
            String browser= configurator.getBrowser();
            
            if(browser.equalsIgnoreCase("IE"))
            {
                driver = getIEDriver();
            }
            else if(browser.equalsIgnoreCase("CHROME"))
            {
                driver = getChromeDriver();
            }
            else if(browser.equalsIgnoreCase("FIREFOX"))
            {
                driver = getFirefoxDriver();
            }
            else
            {
                driver = getIEDriver();
            }
            driver.manage().timeouts().implicitlyWait(default_implicit_wait, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return driver;
    }

    /**
     * Method to get the driver based on the browser from testng xml or properties file
     * @param driverPath
     * @return WebDriver based on the browser passed from testngxml/properties file
     */
    public WebDriver getDriver(String driverPath){

        try {

            AutomationConfigurator configurator=new AutomationConfigurator();
            String browser= configurator.getBrowser();



            if(browser.equalsIgnoreCase("IE"))
            {
                driver = getIEDriver(driverPath);
            }
            else if(browser.equalsIgnoreCase("CHROME"))
            {
                driver = getChromeDriver(driverPath);
            }
            else if(browser.equalsIgnoreCase("FIREFOX"))
            {
                driver = getFirefoxDriver(driverPath);
            }
            else
            {
                driver = getIEDriver(driverPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return driver;
    }

    /**
     * implicit wait function
     */
    public void implicitWait()
    {
        driver.manage().timeouts().implicitlyWait(default_implicit_wait, TimeUnit.SECONDS);

    }

    /**
     * maximize the browser
     */
    public void maximizeBrowser()
    {
        driver.manage().window().maximize();

    }

    /**
     * Method to get the Environment URL by providing environment
     * @return URL for specific environment
     */
    public String appURL(String Environment) throws IOException {

        String env = null;

        if(Environment.equalsIgnoreCase("STST"))
        {
            env = "http://uappt3.intern.folksam.se:9083/prweb/PRWebLDAP1";
        }
        else if(Environment.equalsIgnoreCase("AUTOTEST"))
        {
            env = "http://uappt4.intern.folksam.se:9080/prweb/PRWebLDAP1";
        }
        else if(Environment.equalsIgnoreCase("TEST"))
        {
            env = "http://uappt4.intern.folksam.se:9081/prweb/PRWebLDAP1/YyL_FNsjRcwHXZqmHrXRZlodpncKY-PYq9tGpuH4rqc%5B*/!STANDARD?";
        }
        else if(Environment.equalsIgnoreCase("BTES"))
        {
            env = "http://uappt4.intern.folksam.se:9082/prweb/PRWebLDAP1/YyL_FNsjRcwHXZqmHrXRZlodpncKY-PYq9tGpuH4rqc%5B*/!STANDARD?";
        }
        else if(Environment.equalsIgnoreCase("ATST"))
        {
            env = "http://haloatst.intern.folksam.se/prweb/PRWebLDAP1/YyL_FNsjRcwHXZqmHrXRZlodpncKY-PYq9tGpuH4rqc%5B*/!STANDARD?";
        }
        else if(Environment.equalsIgnoreCase("DEV"))
        {
            env = "http://uappt8.intern.folksam.se:9080/prweb/PRWebLDAP1";
        }
        else if(Environment.equalsIgnoreCase("STAGE"))
        {
            env = "http://uappt8.intern.folksam.se:9081/prweb/PRWebLDAP1";
        }
        else if(Environment.equalsIgnoreCase("JOUR"))
        {
            env = "http://uappt3.intern.folksam.se:9085/prweb/PRWebLDAP1";
        }
        else if(Environment.equalsIgnoreCase("STSTCRM"))
        {
            env = "https://pegastst.intern.folksam.se/prweb/PRServletContainerAuth/_Xtmwx0Z87M%5B*/!STANDARD";
        }
        else if(Environment.equalsIgnoreCase("BTESCRM"))
        {
            env = "https://pegabtes.intern.folksam.se/prweb/PRServletContainerAuth/_Xtmwx0Z87M%5B*/!STANDARD";
        }
        else if(Environment.equalsIgnoreCase("ATSTCRM"))
        {
            env = "http://pegaatst.intern.folksam.se";
        }
        else if(Environment.equalsIgnoreCase("TESTCRM"))
        {
            env = "https://pegatest.intern.folksam.se/prweb/PRServletContainerAuth/_Xtmwx0Z87M%5B*/!STANDARD";
        }
        else
        {
            env = "http://uappt3.intern.folksam.se:9083/prweb/PRWebLDAP1";
        }
        LOGGER.info("Picked up the URL : "+env);
        return env;
    }

    /**
     * Method to launch the app with new URLs
     * @param environment
     * @return URL for specific environment
     */
    public String appNewURL(String environment)
    {
        String env = null;

        if(environment != null) {
            environment = environment.toLowerCase();
            env = "https://pega" + environment + ".intern.folksam.se/prweb/PRServlet/";
            return env;
        }
        else
        {
            return "https://pegastst.intern.folksam.se/prweb/PRServlet/";
        }



    }

      /**
     * Method to shut down the driver
     * @param driver
     */
    public  void driverShutDown(WebDriver driver)
    {
        try {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }catch(Exception e)
        {

        }

    }


    /**
     * Method to close the browser
     * @param driver
     */
    public void closeBrowser(WebDriver driver)
    {
        if(driver!=null)
        {
            driver.close();
        }
    }

    /**
     * Method to get the IE Driver
     * @return
     * @throws FileNotFoundException
     */
    public WebDriver getIEDriver() throws FileNotFoundException {
        return getIEDriver(DEFAULT_IE_DRIVER_PATH);
    }

    /**
     * Method to get the IE driver by providing the IEDriver path
     * @param driverPath
     * @return
     */
    public WebDriver getIEDriver(String driverPath)
    {
        File file = new File(driverPath);
        System.setProperty(IE_SYSTEM_PROPERTY, file.getAbsolutePath());
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        driver = new InternetExplorerDriver(options);
        implicitWait();
        maximizeBrowser();
        return driver;

    }

    /**
     * Method to get the chrome driver
     * @return
     * @throws FileNotFoundException
     */
    public WebDriver getChromeDriver() throws FileNotFoundException {
        return getChromeDriver(DEFAULT_CHROME_DRIVER_PATH);
    }

    /**
     * Method to get the Chrome driver by providing the driver path
     * @param driverPath
     * @return
     */
    public WebDriver getChromeDriver(String driverPath)
    {
        File file = new File(driverPath);
        System.setProperty(CHROME_SYSTEM_PROPERTY, file.getAbsolutePath());
        driver = new ChromeDriver();
        implicitWait();
        maximizeBrowser();
        return driver;

    }


    /**
     * Method to get the firefox driver
     * @return WebDriver
     */
    public WebDriver getFirefoxDriver()  {

        return getFirefoxDriver(DEFAULT_GECKO_DRIVER_PATH);

    }

    /**
     * Method to get the Firefox driver by providing driver path
     * @param driverPath
     * @return
     */
    public WebDriver getFirefoxDriver(String driverPath)  {

        File file = new File(driverPath);
        System.setProperty(FIREFOX_SYSTEM_PROPERTY, file.getAbsolutePath());
        driver = new FirefoxDriver();
        implicitWait();
        maximizeBrowser();
        return driver;

    }


    /**
     * This method prepares the environment to be run (compares the environment passed from jenkins against environment in Test Data sheet)
     * @param rowNumber
     * @param excelDataHandler
     * @return
     */
    
    public String prepareTestEnvironmentFromExcelFile(int rowNumber , ExcelDataHandler excelDataHandler) throws Exception {

                //Get the environment from Jenkins Job/ Properties file
			    String env = new AutomationConfigurator().getEnvironment();

               //Get the Environment from excel sheet .
                String environment = excelDataHandler.getCellData("Login",rowNumber,"Environment");

               //Print the environment, browser and Row Number
               System.out.println("-------------------------------------------------------------------------------");
               LOGGER.info("Environement : "+environment+"; Browser : "+ new AutomationConfigurator().getBrowser() +"; Row Number : " + rowNumber);
               System.out.println("-------------------------------------------------------------------------------");

               //skip the test execution if there is no match with environment parameter passed from jenkins job/properties file with excel sheet
               //environment
               if(!env.equalsIgnoreCase(environment)&& (!env.equalsIgnoreCase("ALL"))) {
                   LOGGER.info("Skipped the Test Execution");
                   throw new SkipException("Environment mismatch for the row "+rowNumber+" skipping the test execution");
               }
               
   			return environment;
    }


    /**
     * This method creates Row Iteration List
     * @param rowCount
     * @return
     */

    public Object[][] createRowIterationList(int rowCount)  {

    	 Object[][] object = new Object[rowCount-1][1];

         for(int itr=1;itr < rowCount;itr++)
         {
             object[itr-1][0] = itr;

         }
         return object;

    }

    /**
     * Method to get the browser level authentication
     * @param userName
     * @param password
     * @throws AWTException
     * @throws InterruptedException
     */
    public void browserLevelAuthentication(String userName, String password) throws AWTException, InterruptedException {

        Thread.sleep(1000);
        // create robot for keyboard operations
        Robot rb = new Robot();

        // Enter user name in username field
        StringSelection username = new StringSelection(userName);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username,null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);


        // press tab to move to password field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);

        //Enter password in password field
        StringSelection pwd = new StringSelection(password);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);

        //press enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }
    

}
