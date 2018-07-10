/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2018                                           
 *                                                                                                                                 
 *  Creation Date: 10 7, 2018                                                      
 *                                                                              
 *******************************************************************************/
package org.oscm.portal;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.oscm.webtest.PortalHtmlElements;
import org.oscm.webtest.PortalPathSegments;
import org.oscm.webtest.PortalTester;
import org.oscm.webtest.WebTester;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MarketplaceSubscriptionWT {
    private static PortalTester tester;
    private static final String referenceNumber = ""+System.currentTimeMillis();
    @BeforeClass
    public static void setup() throws Exception {
        tester = new PortalTester();
        String userid=PlaygroundSuiteTest.supplierOrgAdminId;
        String userpassword = PlaygroundSuiteTest.supplierOrgAdminPwd;
        tester.loginMarketplace(userid, userpassword, PlaygroundSuiteTest.supplierOrgId);
    }

    @AfterClass
    public static void cleanUp() {
        tester.logoutMarketplace();
        tester.close();

    }

    @Test
    public void test01gotoMarketPlace() throws Exception{

        tester.visitPortal(PortalPathSegments.GOTO_MARKETPLACE);
        tester.waitForElement(By.id(PortalHtmlElements.GOTO_MARKETPLACE_DROPDOWN_MARKETPLACE), WebTester.IMPLICIT_WAIT);
        tester.selectDropdown(PortalHtmlElements.GOTO_MARKETPLACE_DROPDOWN_MARKETPLACE, PlaygroundSuiteTest.supplierOrgId);
        tester.waitForElementVisible(By.id(PortalHtmlElements.GOTO_MARKETPLACE_BUTTONLINK_GOTO), WebTester.IMPLICIT_WAIT);
        tester.clickElement(PortalHtmlElements.GOTO_MARKETPLACE_BUTTONLINK_GOTO);
        assertTrue(tester.verifyFoundElement(By.id(PortalHtmlElements.MARKETPLACE_SPAN_WELCOME)));

    }
    
    
    @Test
    public void test02createSubscription() throws Exception {
        for(int i = 0; i<=200; i++) {
            String subscriptionName = "sub_" + System.currentTimeMillis();
            tester.visitMarketplace(PortalPathSegments.INDEX_MARKETPLACE);
            String xpathServiceLink = String.format(PortalHtmlElements.MARKETPLACE_LINK_SERVICE_NAME, PlaygroundSuiteTest.marketServiceName);
            tester.waitForElement(By.xpath(xpathServiceLink), WebTester.IMPLICIT_WAIT);
            String linkToService = tester.getDriver().findElement(By.xpath(xpathServiceLink)).getAttribute("href");
            tester.getDriver().navigate().to(linkToService);
            tester.waitForElement(By.id(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_BUTTON_GETITNOW), WebTester.IMPLICIT_WAIT);

            tester.clickElement(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_BUTTON_GETITNOW);
            tester.waitForElement(By.id(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_BUTTONLINK_NEXT), WebTester.IMPLICIT_WAIT);
            tester.getDriver().findElement(By.id(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_INPUT_SUBNAME)).clear();
            tester.writeValue(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_INPUT_SUBNAME, subscriptionName);
            tester.writeValue(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_INPUT_REFNUMBER, referenceNumber);
            tester.clickElement(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_BUTTONLINK_NEXT);
            tester.waitForElementVisible(By.id(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_CHECKBOX_LICENSEAGREE), WebTester.IMPLICIT_WAIT);
            tester.clickElement(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_CHECKBOX_LICENSEAGREE);
            tester.clickElement(PortalHtmlElements.MARKETPLACE_SUBSCRIPTION_BUTTONLINK_CONFIRM);
            assertTrue(tester.getExecutionResult());
        }
    }
}
