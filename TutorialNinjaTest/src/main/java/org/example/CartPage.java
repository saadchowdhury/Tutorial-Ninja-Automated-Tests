package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    private By myAccount = By.xpath("//a[@title='My Account']");
    private By logout = By.xpath("//a[contains(text(),'Logout')]");
    private By qty = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input");
    private By total = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]");
    private By unitPrice = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[5]");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyQuantity(String expQty) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(qty));
        //String expectedQty = "2";
        //int expQty = Integer.parseInt(expectedQty);
        String actualQty = driver.findElement(qty).getAttribute("value");
        //int actQty = Integer.parseInt(expectedQty);
        if (expQty.equalsIgnoreCase(actualQty)) {
            Logger.logstep("Quantity is correct");
            //System.out.println("Quantity is correct");
        }
        else {
            Logger.logstep("Quantity is wrong");
            //System.out.println("Quantity is wrong");
        }
    }

    public void verifyTotal(int expQ, double expPrice) {

        //String expectedQty = "$1,806.00";
        String actualTotal = driver.findElement(total).getText().trim().replace("$","").replace(",","");
        //actualTotal=actualTotal.trim().replace(",","");
        Logger.logstep("actual total is :"+actualTotal);
        //System.out.println("actual total is :"+ actualTotal);
        double actSubtotal = Double.parseDouble(actualTotal);
        //int untPrice =Integer.parseInt(driver.findElement(unitPrice).getText()) ;
        double expSubTotal = (double)expQ * expPrice;
        if (actSubtotal==expSubTotal) {
            Logger.logstep("Total amount is correct");
            //System.out.println("Total amount is correct");
        }
        else {
            Logger.logstep("Total amount is wrong");
            //System.out.println("Total amount is wrong");
        }
    }

    public void clickMyAccount() {
        driver.findElement(myAccount).click();
        Logger.logstep("My account button is clicked");
        //System.out.println("My account is clicked");
    }
    public void clickLogout()  {
        driver.findElement(logout).click();

            //Thread.sleep(5000);


        Logger.logstep("Logout has been clicked");
        //System.out.println("Logout has been clicked");
    }
}
