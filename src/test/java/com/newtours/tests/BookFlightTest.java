package com.newtours.tests;

import com.phptravels.pages.DemoPage;
import com.phptravels.pages.Prices;
import com.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

public class BookFlightTest extends BaseTest {
    private String plans;
    //Arrays.asList("Startup", "Agency", "Business", "Enterprise");
    @BeforeTest
    @Parameters({"plans"})
    public void setupParameters(String plans){
        this.plans = plans;
    }

    @Test
    public void demoPageTest(){
        DemoPage demoPage = new DemoPage(webDriver);
        demoPage.goTo();
        demoPage.enterUserDetails("TestFirst","testlast","Buijnees", "test@sharklasers.com");
        demoPage.submit();
        demoPage.goToPricingPage();
    }

    @Test(dependsOnMethods = "demoPageTest")
    public void pricingPageTest(){
        Prices prices = new Prices(webDriver);
        List<String> allPlans = prices.getAllPlans();
        String[] pricingPlans = plans.split(",");
        //allPlans.equals(plans);
        Assert.assertEquals(allPlans.toArray(),pricingPlans);
    }

    @AfterTest
    public void quitBrowser(){
        this.webDriver.quit();
    }
}
