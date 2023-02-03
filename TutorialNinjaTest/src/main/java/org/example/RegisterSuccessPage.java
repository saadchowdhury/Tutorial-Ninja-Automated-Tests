package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterSuccessPage {
    private WebDriver driver;
    private By successMessage = By.xpath("//*[@id='content']/h1");
    private By cont = By.xpath("//a[contains(text(),'Continue')]");

    private By myAccount = By.xpath("//a[@title='My Account']");
    private By logout = By.cssSelector("#top-links > ul > li.dropdown.open > ul > li:nth-child(5) > a");

    public RegisterSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public void successMessagecheck() {
        String currentURL = driver.getCurrentUrl();
        if(!currentURL.equalsIgnoreCase("http://tutorialsninja.com/demo/index.php?route=account/register"))
        {
            String expectedText = "Your Account Has Been Created!";
            String actualText = driver.findElement(successMessage).getText();
            //Assert.assertEquals(actualText,expectedText,"Registration error!");
            Assert.assertEquals(actualText,expectedText,"Registration error!");
//            if (expectedText.equalsIgnoreCase(actualText)) {
//                Logger.logstep("Account Registered");
//                //System.out.println("Account is created");
//            } else {
//                Logger.logstep("Registration Error");
//                //System.out.println("There is an Error");
//            }
        }
        else{
            Logger.logstep("Error found in Registration");
            //System.out.println("Error found");
            driver.quit();
        }

    }

    public void clickOnContinue() {
        driver.findElement(cont).click();
        Logger.logstep("Continue button is clicked");
        //System.out.println("Continue button is clicked");
    }

    public void clickMyAccount() {
        driver.findElement(myAccount).click();
        Logger.logstep("My account is clicked");
        //System.out.println("My account is clicked");
    }
    public void clickLogout() {
        driver.findElement(logout).click();
        Logger.logstep("Logout has been clicked");
        //System.out.println("Logout has been clicked");
    }
}
