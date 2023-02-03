package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private By qty = By.xpath("//input[@id='input-quantity']");
    private By addBtn = By.xpath("//button[@id='button-cart']");
    private By cartBtn = By.xpath("//div[@id='cart']");
    private By viewCart = By.xpath("//strong[contains(text(),'View Cart')]");



    //*[@id="product-product"]/div[1]
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuantity() {
        WebElement qt=  driver.findElement(qty);
        qt.clear();
        qt.sendKeys("2");
        Logger.logstep("Quantity is edited");
        //System.out.println("Quantity is edited");
    }

    public void clickAddToCart(){
        driver.findElement(addBtn).click();
        Logger.logstep("Add to cart button is clicked");
        //System.out.println("Add to cart button is clicked");
    }

    public void clickCartButton() {
        driver.findElement(cartBtn).click();
        Logger.logstep("Cart button is clicked");
        //System.out.println("Cart button is clicked");

    }

    public void clickonViewCart() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCart));
        driver.findElement(viewCart).click();
        Logger.logstep("View cart is clicked");
        //System.out.println("View cart is clicked");

    }
}
