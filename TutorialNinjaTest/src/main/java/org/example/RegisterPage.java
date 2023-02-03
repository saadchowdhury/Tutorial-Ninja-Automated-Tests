package org.example;

import org.log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RegisterPage {
    private WebDriver driver;
    private By firstName = By.xpath("//input[@id='input-firstname']");
    private By lastName = By.xpath("//input[@id='input-lastname']");
    private By email = By.xpath("//input[@id='input-email']");
    private By telephone = By.xpath("//input[@id='input-telephone']");
    private By password = By.xpath("//input[@id='input-password']");
    private By confirmPassword = By.xpath("//input[@id='input-confirm']");
    private By subscribe = By.xpath("//input[@name='newsletter' and @value='1']");
    private By privacyPolicy = By.xpath("//input[@name='agree' and @value='1']");
    private By cont = By.xpath("//input[@value='Continue']");

    private By warningHead = By.xpath("//*[@id=\"account-register\"]/div[1]/text()");
    private By warningMsg = By.xpath("//div[@class='text-danger']");
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void scrollTo(String locator){
        if(locator.equalsIgnoreCase("privacy")) {
            WebElement element = driver.findElement(By.xpath("//input[@name='agree' and @value='1']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView()", element);
        }else{
            Logger.logstep("locator is out of scope");
            //System.out.println("locator is out of scope");
        }
    }

    public void inputFirstName(String fName){
        if(fName!="") {
            driver.findElement(firstName).sendKeys(fName);
            Logger.logstep("First name is entered");
            //System.out.println("First name is entered");
        }else{
            Logger.logstep("first name is not entered");
            //System.out.println("first name is not entered");
        }
    }

    public void inputLastname(String lName){
        if(lName!="") {
            driver.findElement(lastName).sendKeys(lName);
            Logger.logstep("Last name is entered");
            //System.out.println("Last name is entered");
        }
        else{
            Logger.logstep("Last name is not entered");
            //System.out.println("Last name is not found");
        }
    }

    public void inputEmail(String emailID ){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        if(emailID!=""){
        driver.findElement(email).sendKeys(emailID+saltStr+"@gmail.com");
        System.out.println("Email is entered!");
        }else{
            Logger.logstep("Email is not entered");
            //System.out.println("email id is not found");
        }

    }

    public void inputTelephone(String phoneNumber){
        if(phoneNumber!="") {
            driver.findElement(telephone).sendKeys(phoneNumber);
            Logger.logstep("Telephone is not entered");
            //System.out.println("the telephone number is entered");
        }else{
            Logger.logstep("Phone number is not entered");
            //System.out.println("phone number is not found");
        }
    }

    public void inputPassword(String pass) {
        if(pass!="") {
            driver.findElement(password).sendKeys(pass);
            Logger.logstep("Password is entered");
            //System.out.println("Password is entered");
        }else{
            Logger.logstep("password is not found");
            //System.out.println("password is not found");
        }
    }

    public void inputPassConfirm(String conPass) {
        if (conPass!="") {
            driver.findElement(confirmPassword).sendKeys(conPass);
            Logger.logstep("password confirm is entered");
            //System.out.println("Password confirm is entered");
        }else{
            Logger.logstep("confirm password is not entered");
            //System.out.println("Confirm password is not entered");
        }
    }



    public void clickOnNewsletterSubscribe(String newsLetter) {
//        WebElement radioElement = driver.findElement(By.xpath("//input[@value='"+ gender + "']"));
        //boolean isSelected = driver.findElement(subscribe).isSelected();

        if (newsLetter.equalsIgnoreCase("FALSE")) {
            driver.findElement(subscribe).click();
            Logger.logstep("Newsletter subscribe radio button is clicked");
            //System.out.println("Newsletter subscribe radio button is clicked");
        }else{
            Logger.logstep("subscribe button is not clicked");
            //System.out.println("subscribe button is not clicked");
        }
    }

    public void clickOnPrivacyPolicy(String policy) {
        scrollTo("privacy");
        //WebElement wHead = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));

        // performing click operation if element is not selected
        if (policy.equalsIgnoreCase("TRUE")) {
            driver.findElement(privacyPolicy).click();
            Logger.logstep("Privacy policy checkbox has been clicked");
            //System.out.println("Privacy policy checkbox has been clicked");
        }else if(policy.equalsIgnoreCase("False")){
            Logger.logstep("policy is not clicked");
        }
    }

    public void clickOnContinue() {
        driver.findElement(cont).click();
        Logger.logstep("Continue button has been clicked");
        //System.out.println("Continue button has been clicked");
    }



    public void errorMsgShow(Map data[],int index){

        List<WebElement> options = driver.findElements(By.xpath("//div[@class='text-danger']"));
        for(int i=0 ;i< options.size();i++){
            System.out.println(i);
            System.out.println(options.get(i).getText());
            if(data[index].get("first name error").equals(options.get(i).getText()) || data[index].get("last name error").equals(options.get(i).getText()) || data[index].get("email error").equals(options.get(i).getText()) || data[index].get("phone error").equals(options.get(i).getText()) || data[index].get("password error").equals(options.get(i).getText()) || data[index].get("confirm error").equals(options.get(i).getText())){
                Logger.logstep("Error is valid");
                //System.out.println("Error is valid");
            }else{
                Logger.logstep("Invalid Error");
                //System.out.println("Invalid Error");
            }
        }


    }



}
