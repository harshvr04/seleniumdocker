package com.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Prices {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindAll({@FindBy(xpath = "//p[normalize-space(@class) = 'pricing-package-name tac']/strong")})
    private List<WebElement> plans;

    public Prices(WebDriver driver){
        this.webDriver= driver;
        this.wait = new WebDriverWait(driver,30);
    }

    public List<String> getAllPlans(){
        List<String> webElementString = new ArrayList<>();
        List<WebElement> plans = webDriver.findElements(By.xpath("//p[normalize-space(@class) = 'pricing-package-name tac']/strong"));
        for(WebElement we : plans){ ExpectedConditions.visibilityOf(we);
           webElementString.add(we.getText());
        }
        return webElementString;
    }
}
