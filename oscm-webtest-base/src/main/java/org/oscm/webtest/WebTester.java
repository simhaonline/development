/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2017                                           
 *                                                                                                                                 
 *  Creation Date: Feb 7, 2017                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.webtest;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class for integration web tests using selenium and java mail.
 * 
 * @author miethaner
 */
public class WebTester {
    
    protected static final Logger logger = Logger.getLogger(WebTester.class);

    public static final int IMPLICIT_WAIT = 10;

    // path schemas
    private static final String PROPERTY_PATH = "../oscm-devruntime/javares/local/%s/webtest.properties";

    // web element keys
    protected static final String ATTRIUBTE_VALUE = "value";

    protected String baseUrl = "";
    
    protected HtmlUnitDriver driver;    

    protected Properties prop;

    
    public WebTester() throws Exception {

        loadPropertiesFile();

        driver = new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT,
                TimeUnit.SECONDS);
        

    }
    
    
    
    /**
     *  Load properties from personal devruntime folder
     * 
     */
    private void loadPropertiesFile() throws Exception {

        Map<String, String> env = System.getenv();
        String localhost = env.get("HOSTNAME");
        if (StringUtils.isEmpty(localhost)) {
            localhost = InetAddress.getLocalHost().getHostName();
        }
        String filePath = String.format(PROPERTY_PATH, localhost);
        
        prop = new Properties();
        FileInputStream fis = new FileInputStream(filePath);
        prop.load(fis);
        fis.close();

    }

    /**
     * load Url
     * @param prefix
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    protected String loadUrl(String secureUrl, String httpsUrl, String httpUrl) throws NoSuchFieldException, SecurityException {
     
        boolean secure = Boolean.parseBoolean(prop.getProperty(secureUrl));
    
        if (secure) {
            return prop.getProperty(httpUrl);
        } else {
            return prop.getProperty(httpsUrl);
        }  
    }
 
    public String getPropertie(String propertie) {
        return prop.getProperty(propertie);
    }

    /**
     * Closes all open resources of the helper
     */
    public void close() {
        driver.close();
    }


    /**
     * found the text between two given text in String
     * @param msg
     * @param before
     * @param after
     * @return
     */
    protected String foundTextBetween(String msg, String before, String after) {

        msg = msg.substring(msg.indexOf(before) + before.length(), msg.indexOf(after));
 
        return msg;
       
    }

    

    /**
     * Reads the error message from the page notification.
     * 
     * @return the error message
     * @throws NoSuchElementException
     *             if error message is not present
     */
    public String readErrorMessage() {
        return "";

    }

    /**
     * Reads the info message from the page notification.
     * 
     * @return the info message
     * @throws NoSuchElementException
     *             if info message is not present
     */
    public String readInfoMessage() {
        return "";
    }

    public boolean getPortalExecutionResult() {
        return false;
    }
    /**
     * Verifies if found the required element
     * 
     * @param id
     *            the element id
     * @param value
     *            the value to compare with
     * @return true if equal
     * @throws NoSuchElementException
     *             if element is not present
     */
    public boolean verifyFoundElement(String id) {
        
        try {
        if(driver.findElement(By.id(id))!=null)
            return true;
        }catch(NoSuchElementException e) {
            return false;
        }
        return false;
    }

    /**
     * Verifies if the content of the element with the given id is equal to the
     * given value.
     * 
     * @param id
     *            the element id
     * @param value
     *            the value to compare with
     * @return true if equal
     * @throws NoSuchElementException
     *             if element is not present
     */
    public boolean verifyEqualElement(String id, String value) {
        WebElement element = driver.findElement(By.id(id));
        if(element == null) return false;
        
        String attribute = element.getAttribute(ATTRIUBTE_VALUE);

        if (attribute != null && attribute.equals(value)) {
            System.out.println("Element with id " + id + " and value " + value
                    + " is valid");
            return true;
        } else {
            System.out.println("Element with id " + id + " is invalid (" + value
                    + " != " + attribute + ")");
            return false;
        }
    }

    /**
     * Clicks the element with the given id.
     * 
     * @param id
     *            the element id
     * @throws NoSuchElementException
     *             if element is not present
     */
    public void clickElement(String id) {
        driver.findElement(By.id(id)).click();

        System.out.println("Clicked the element with id " + id);
    }

    /**
     * Reads the value of the element with the given id. This is used for fields
     * that use the value attribute, e.g. input fields.
     *
     * @return the value of the element
     * @throws NoSuchElementException
     *             if element is not present
     */
    public String readValue(String id) {
        WebElement element = driver.findElement(By.id(id));
        return element.getAttribute(ATTRIUBTE_VALUE);
    }

    /**
     * Reads the text of the element with the given id. This is used for text
     * within an element, e.g. &lt;p id="id"&gt;text&lt;/p&gt;
     *
     * @return the text of the element
     * @throws NoSuchElementException
     *             if element is not present
     */
    public String readText(String id) {
        WebElement element = driver.findElement(By.id(id));
        return element.getText();
    }

    /**
     * Takes the given value as input for the element with the given id.
     * 
     * @param id
     *            the element id
     * @param value
     *            the input value
     * @throws NoSuchElementException
     *             if element is not present
     */
    public void writeValue(String id, String value) {
        WebElement element = driver.findElement(By.id(id));
        element.sendKeys(value);
    }

    /**
     * Selects in the dropdown (select) element with the given id the option
     * with the given value.
     * 
     * @param id
     *            the element id
     * @param value
     *            the option value
     * @throws NoSuchElementException
     *             if element is not present
     */
    public void selectDropdown(String id, String value) {
        Select select = new Select(driver.findElement(By.id(id)));
        select.selectByValue(value);
    }

    /**
     * Submits the form with the given id.
     * 
     * @param id
     *            the element id
     * @throws NoSuchElementException
     *             if element is not present
     */
    public void submitForm(String id) {
        driver.findElement(By.id(id)).submit();

        System.out.println("Submitted form with id " + id);
    }

    /**
     * Waits for the element with the given id to be present or until the given
     * amount of seconds has passed.
     * 
     * @param id
     *            the element id
     * @param seconds
     *            the seconds until timeout
     * @throws TimeoutException
     *             if the timeout is reached
     */
    public void waitForElement(String id, int seconds) {
        (new WebDriverWait(driver, seconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    /**
     * Waits for the element with the given id to be present or until the given
     * amount of seconds has passed.
     * 
     * @param id
     *            the element id
     * @param seconds
     *            the seconds until timeout
     * @throws TimeoutException
     *             if the timeout is reached
     */
    public void wait(int seconds) {
        (new WebDriverWait(driver, seconds)).withTimeout(seconds, TimeUnit.SECONDS);
    }
    
 
    /**
     * Returns the current URL that the webdriver is visiting.
     * 
     * @return the current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void log (String msg) {
        logger.info(msg);
    }
}
