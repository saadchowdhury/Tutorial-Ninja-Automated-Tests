package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    private WebDriver driver;
    private By logoutMsg = By.xpath("//h1[contains(text(),'Account Logout')]");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logoutMsgCheck() {
        String expectedMsg = "Account Logout";
        String actualMsg = driver.findElement(logoutMsg).getText();
        if (expectedMsg.equalsIgnoreCase(actualMsg)) {
            Logger.logstep("Logout successful");
            //System.out.println("Logout successful");
        }
        else {
            Logger.logstep("Unsuccessful logout");
            //System.out.println("unsuccessful logout");
        }
    }
}
