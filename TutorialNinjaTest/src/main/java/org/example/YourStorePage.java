package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class YourStorePage {
    private WebDriver driver;
    private By mac = By.xpath("//a[contains(text(),'MacBook')]");
    private By Desktops = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a");
    private By Sorting = By.xpath("//select[@id='input-sort']");
    private By SortItem = By.xpath("(//div[@class='image'])");
    private By AllDesktops = By.xpath("//a[normalize-space()='Show All Desktops']");
    public YourStorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOnDesktops(){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(Desktops);
        action.moveToElement(element);
        action.perform();
        Logger.logstep("Hovering on Desktops");
        //System.out.println("Hovering on Desktops");
    }
//    public void clickOnFirstItem() {
//        driver.findElement(mac).click();
//        System.out.println("First Item has been clicked");
//    }

    public void clickShowAllDesktops(){
        driver.findElement(AllDesktops).click();
        Logger.logstep("Show all desktops ic clicked");
        //System.out.println("Show All Desktops clicked");
    }
    public void sort(String str){
        Select select = new Select(driver.findElement(Sorting));
        select.selectByVisibleText(str);
        Logger.logstep("Items sorted according to " + str);
        //System.out.println("Items sorted according to" + str);
    }

    public void clickOnFirstItem(){
        List<WebElement> options = driver.findElements(SortItem);
        options.get(0).click();
        Logger.logstep("First item is clicked");
        //System.out.println("First Item clicked");
    }

}
