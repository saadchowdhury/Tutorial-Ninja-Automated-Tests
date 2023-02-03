package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;
    private By yourStoreText = By.xpath("//a[contains(text(),'Your Store')]");
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnYourStore() {
        driver.findElement(yourStoreText).click();
        Logger.logstep("Your Store button is clicked");
        //System.out.println("Your Store is clicked");
    }
}
