package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Main {
    static WebDriver driver;
    static HomePage objHomePage;
    static RegisterPage objRegisterPage;
    static RegisterSuccessPage objRegisterSuccessPage;
    static MyAccountPage objMyAccountPage;
    static YourStorePage objYourStorePage;
    static ProductPage objProductPage;
    static CartPage objCartPage;
    static LogoutPage objLogoutPage;

    public static void click_register_from_home_page(){
        objHomePage = new HomePage(driver);
        objHomePage.clickOnRegister();
    }
    public static void register_an_account(String fName,String lName,String email,String phone,String pass,String confPass, String newsLetter, String policy){

        objRegisterPage = new RegisterPage(driver);
        objRegisterPage.inputFirstName(fName);
        objRegisterPage.inputLastname(lName);
        objRegisterPage.inputEmail(email);
        objRegisterPage.inputTelephone(phone);
        objRegisterPage.inputPassword(pass);
        objRegisterPage.inputPassConfirm(confPass);
        objRegisterPage.clickOnNewsletterSubscribe(newsLetter);
        objRegisterPage.clickOnPrivacyPolicy(policy);
        objRegisterPage.clickOnContinue();

    }

    public static void registration_success_message(){
        objRegisterSuccessPage = new RegisterSuccessPage(driver);
        objRegisterSuccessPage.successMessagecheck();
        objRegisterSuccessPage.clickOnContinue();
    }

    public static void click_on_your_store() {
        objMyAccountPage = new MyAccountPage(driver);
        objMyAccountPage.clickOnYourStore();
    }

    public static void click_on_first_item(){
        objYourStorePage = new YourStorePage(driver);
        objYourStorePage.hoverOnDesktops();
        objYourStorePage.clickShowAllDesktops();
        objYourStorePage.sort("Price (Low > High)");
        objYourStorePage.sort("Price (High > Low)");
        objYourStorePage.clickOnFirstItem();
    }

    public static void edit_quantity_add_to_cart(){
        objProductPage = new ProductPage(driver);
        objProductPage.enterQuantity();
        objProductPage.clickAddToCart();
        objProductPage.clickCartButton();
        objProductPage.clickonViewCart();
    }
//

    public static void check_quantity_and_subtotal(){
        objCartPage = new CartPage(driver);
        objCartPage.verifyQuantity("2");
        objCartPage.verifyTotal(2,602.00);
    }
    public static void click_logout() {
        objCartPage = new CartPage(driver);
        objCartPage.verifyQuantity("2");
        objCartPage.clickMyAccount();
       // try{
            objCartPage.clickLogout();
          //  }catch(InterruptedException e){
        System.out.println("5 sec");
    }



//
    public static void logout_msg_check(){
        objLogoutPage = new LogoutPage(driver);
        objLogoutPage.logoutMsgCheck();
    }
    public static void show_error_message(Map data[], int index){
        objRegisterPage = new RegisterPage(driver);
        objRegisterPage.errorMsgShow(data,index);

    }
//    public static boolean error_message_check(String fError, String lError, String eError, String pError, String pcError){
//        objRegisterPage = new RegisterPage(driver);
//        boolean valid_error = objRegisterPage.error_message_validation(fError);
//        return  valid_error;
//    }
    public static void setBrowser(String browser) {

        //this condition checks for valid browsers

        if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("edge")) {

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();

            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }

            //driver = new ChromeDriver();
            String URL = "http://tutorialsninja.com/demo/index.php?route=common/home";
            driver.get(URL);
        } else {
            System.out.println("The browser is not in scope");
        }
    }
    public static void main(String[] args) throws IOException {
        ReadFile objReadFile = new ReadFile();
        RegisterPage objRegister = new RegisterPage(driver);
        String filePath = System.getProperty("user.dir") + "\\src";
        String browser = "chrome";


        Map data[] = objReadFile.readExcel("myFile.xls", "Sheet1");
        //System.out.println(data[1]);
        System.out.println(data.length);
        String errorData[] = new String[6];

        for(int i=1 ; i< data.length; i++) {
            setBrowser(browser);
            driver.manage().window().maximize();
            click_register_from_home_page();
            register_an_account((String)data[i].get("First Name"),(String)data[i].get("Last name"),(String)data[i].get("Email"),(String)data[i].get("phone"),(String)data[i].get("password"),(String)data[i].get("confirm password"), (String) data[i].get("subscribe"), (String)data[i].get("policy"));
            String currentURL= driver.getCurrentUrl();
            String url = "http://tutorialsninja.com/demo/index.php?route=account/success";
            System.out.println(currentURL);
            errorData[i] = (String)data[i].get("first name error");
            if(currentURL.equals(url)){
                System.out.println("this is working");
                System.out.println(currentURL);
                registration_success_message();
                click_on_your_store();
                click_on_first_item();
                edit_quantity_add_to_cart();
                check_quantity_and_subtotal();
                click_logout();
                logout_msg_check();
                driver.quit();
            }else {
                show_error_message(data,i);
               // objRegister.error_message_validation(data, errorText,i);
                System.out.println(currentURL);
                System.out.println("Registration Unsuccessful");
                driver.quit();
            }
        }

    }
}
