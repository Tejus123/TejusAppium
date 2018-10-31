package com.folksam.selenium.infrastructure.utils;

import org.testng.ITestContext;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * class to load the properties file
 *
 * @author KSGA03
 */
public class AutomationConfigurator {

    private final static Logger LOGGER = Logger.getLogger(AutomationConfigurator.class.getName());
    Properties prop = new Properties();

    InputStream input = null;
    String projectpath = System.getProperty("user.dir");

    //Parameters in AutomationConfigurator Properties file
    private static final String ENVIRONMENT_KEY = "Environment";
    private static final String TIMEOUT_KEY = "TimeOut";
    private static final String BROWSER_KEY = "Browser";

    /**
     * constructor to load properties file
     * @throws IOException
     */
    public AutomationConfigurator() throws IOException {

        try {
            input = new FileInputStream(projectpath+"/AutomationConfigurator.properties");
            prop.load(input);
        }catch (IOException e)
        {
            LOGGER.info("AutomationConfigurator.properties file doesn't exist at "+projectpath);

        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     * Method to get the environment
     * @return Environment - Passed through Jenkins Job/ Properties file
     */
    public  String getEnvironment(){

        String env = null;
        try {
            env = System.getProperty("env");
            if(env !=null)
                return env;
            else
                return prop.getProperty(ENVIRONMENT_KEY);

        }catch(Exception e)
        {
            return prop.getProperty(ENVIRONMENT_KEY);
        }
    }

    /**
     * Method to get the browser
     * @return Browser which is passed through Jenkins Job/ Properties file
     */
    public  String getBrowser() {
        String browser = null;

        try
        {
            browser = System.getProperty("browser");
            if(browser !=null)
                return browser;
            else
                return prop.getProperty(BROWSER_KEY);


        }catch(Exception e)
        {
            return prop.getProperty(BROWSER_KEY);
        }


    }

    /**
     * Method to get the Time out
     * @return
     */
    public  String getTimeOut() {
        return prop.getProperty(TIMEOUT_KEY);
    }

}
