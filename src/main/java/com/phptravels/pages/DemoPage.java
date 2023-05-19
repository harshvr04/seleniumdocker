package com.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DemoPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(name = "first_name")
    private WebElement firstNameText;

    @FindBy(name = "last_name")
    private WebElement lastNameText;

    @FindBy(name = "business_name")
    private WebElement businessNameText;

    @FindBy(name = "email")
    private WebElement emailText;

    @FindBy(id = "demo")
    private WebElement submitButtonText;

    @FindBy(id = "numb1")
    private WebElement numb1;

    @FindBy(id = "numb2")
    private WebElement numb2;

    @FindBy(id = "number")
    private WebElement result;

    @FindBy(xpath = "//h2/strong[contains(text(), ' Thank you!')]")
    private WebElement checkMark;

    @FindBy(xpath = "//li/a[contains(text(), 'Pricing')]")
    private WebElement pricing;

    public DemoPage(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        this.webDriver.get("https://phptravels.com/demo/");
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameText));
    }

    public void enterUserDetails(String firstName, String lastName, String businessName, String email){
        this.firstNameText.sendKeys(firstName);
        this.lastNameText.sendKeys(lastName);
        this.businessNameText.sendKeys(businessName);
        this.emailText.sendKeys(email);
    }

    public void submit(){
        String verification = String.valueOf(Integer.parseInt(this.numb1.getText()) + Integer.parseInt(this.numb2.getText()));
        this.result.sendKeys(verification);
        this.submitButtonText.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.checkMark));
    }

    public void goToPricingPage(){
        this.pricing.click();
    }

}
