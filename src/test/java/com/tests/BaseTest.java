package com.tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver webDriver;
    @BeforeTest
    public void setWebDriver(ITestContext ctx) throws MalformedURLException {

        String host = "localhost";

        DesiredCapabilities desiredCapabilities;

        if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            desiredCapabilities = DesiredCapabilities.firefox();
        }else{
            desiredCapabilities = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }
        String testName = ctx.getCurrentXmlTest().getName();
        desiredCapabilities.setCapability("name", testName);

        String completeUrl = "http://" + host + ":4444/wd/hub";
        this.webDriver = new RemoteWebDriver(new URL(completeUrl), desiredCapabilities);
    }

    @AfterTest
    public void quitBrowse(){
        this.webDriver.quit();
    }

}
