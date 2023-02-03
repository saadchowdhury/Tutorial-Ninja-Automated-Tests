package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.log.Logger;

public class HomePage {
    private WebDriver driver;

    private By myAccount = By.xpath("//a[@title='My Account']");
    private By register = By.xpath("//a[contains(text(),'Register')]");
    private By login = By.cssSelector(".dropdown-menu-right > li:nth-child(2) > a:nth-child(1)");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnRegister(){
        driver.findElement(myAccount).click();
        driver.findElement(register).click();
        Logger.logstep("register option is clicked");
        //System.out.println("register option is clicked");
    }

    public void clickOnLogin(){
        driver.findElement(myAccount).click();
        driver.findElement(login).click();
        Logger.logstep("Login option is clicked");
        //System.out.println("Login option is clicked");
    }
}
