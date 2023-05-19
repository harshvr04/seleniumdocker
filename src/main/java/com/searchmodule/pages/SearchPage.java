package com.searchmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    private  WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchText;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(linkText = "Videos")
    private WebElement videoLink;

    @FindAll({@FindBy(xpath = "//div/div[normalize-space(@class) = 'tile__media']")})
    private List<WebElement> allVideos;

    public SearchPage(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        this.webDriver.get("https://duckduckgo.com/");
    }

    public void doSearch(String keyword){
        this.wait.until(ExpectedConditions.visibilityOf(this.searchText));
        this.searchText.sendKeys(keyword);
        //this.wait.until(ExpectedConditions.elementToBeClickable(this.searchButton));
        this.searchText.submit();
    }

    public void goToVideos(){
        this.wait.until((ExpectedConditions.visibilityOf(this.videoLink)));
        this.videoLink.click();
    }

    public int getResult(){
        By by = By.xpath("//div/div[normalize-space(@class) = 'tile__media']");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        System.out.println(this.allVideos.size());
        return this.allVideos.size();
    }   
}
