package org.example;
import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
public class LoginPage {
    private WebDriver driver;
    private By email = By.cssSelector("#input-email");

    private By pass = By.cssSelector("#input-password");
    private By submit = By.cssSelector("input.btn");
    private By loginFailed = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void inputEmail(String emailID){
        driver.findElement(email).sendKeys(emailID);
        Logger.logstep("Email is entered in Sign in page");
    }
    public void inputPass(String password){
        driver.findElement(pass).sendKeys(password);
        Logger.logstep("password is entered in Sign in page");
    }

    public void clickLoginButton(){
        driver.findElement(submit).click();
        Logger.logstep("submit button is clicked clicked");
    }

    public String verifyLoginFailed(String errorMsg) throws IOException {
        String expectedText = errorMsg;
        String actualText = driver.findElement(loginFailed).getText();
        Logger.logstep("Expected Message: " + expectedText);
        Logger.logstep("Actual Message: " + expectedText);
        Assert.assertEquals(actualText, expectedText, "Wrong Login Failed message");
        return actualText;
    }
}
