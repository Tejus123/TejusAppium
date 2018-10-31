package com.folksam.selenium.infrastructure.basepages;


import org.apache.log4j.Logger;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

/**
 * BasePage and all descendants must enforce the pattern that constructor must call
 * the waitForPagLoad to verify whether the page is loaded.If the page is not loaded,
 * wait for the time out duration and throw page not loaded exception.
 * @author KSGA03
 */

public abstract class WebBasePage extends BasePage {

    protected final WebDriver driver;
    private  Logger LOGGER = null;

    /**
     * Constructor for WebBasePage that does the check to make sure page has been loaded
     * @param driver
     */
    public WebBasePage(WebDriver driver)  {
    	super(driver);

        LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        this.driver = driver;
        if(!waitForPageLoad())
        {
            LOGGER.error("Page is not loaded");
            throw new NotFoundException(Thread.currentThread().getStackTrace()[2].getClassName()+" is not loaded"+" with URL "+driver.getCurrentUrl());

        }
    }

    /**
     * This Abstract method is how each page determines whether it has been loaded
     * It is called in the base page constructor
     * @return True if page has loaded, otherwise False
     */
    public abstract  boolean waitForPageLoad();
}
