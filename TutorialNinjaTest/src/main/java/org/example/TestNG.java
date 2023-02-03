package org.example;

import org.log.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.reporter.ExtentManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class TestNG {

    static WebDriver driver = null;
    static HomePage objHomePage;
    static CartPage objCartPage;
    static LogoutPage objLogoutPage;
    static MyAccountPage objMyAccountPage;
    static ProductPage objProductPage;
    static ReadFile objReadFile;
    static RegisterPage objRegisterPage;
    static RegisterSuccessPage objRegisterSuccessPage;
    static YourStorePage objYourStorePage;
    static LoginPage objLoginPage;
    //static ReadFile objReadFile;

    public static void click_register_from_home_page(){
        objHomePage = new HomePage(driver);
        objHomePage.clickOnRegister();
    }
    public static void click_login_from_home_page(){
        objHomePage = new HomePage(driver);
        objHomePage.clickOnLogin();
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
    public static void login_existing_account(String email, String pass) {
        objLoginPage = new LoginPage(driver);
        objLoginPage.inputEmail(email);
        objLoginPage.inputPass(pass);
        objLoginPage.clickLoginButton();

    }
    public static void loginFailedError(String errorMsg)throws IOException{
        objLoginPage = new LoginPage(driver);
        objLoginPage.verifyLoginFailed(errorMsg);
    }

    public static void registration_success_message(){
        objRegisterSuccessPage = new RegisterSuccessPage(driver);
        objRegisterSuccessPage.successMessagecheck();
        //objRegisterSuccessPage.clickOnContinue();
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
        objCartPage.verifyTotal(2,1000.00);
    }
    public static void click_logout() {
        objCartPage = new CartPage(driver);
        objCartPage.verifyQuantity("2");
        objCartPage.clickMyAccount();
        //try{
        objCartPage.clickLogout();
        //}catch(InterruptedException e){
           // System.out.println("5 sec");
    }

    public static void logout_after_registration(){
        objRegisterSuccessPage = new RegisterSuccessPage(driver);
        objRegisterSuccessPage.clickMyAccount();
        objRegisterSuccessPage.clickLogout();
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
    public static void setBrowser(String browser,String URL) {

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
            //String URL = "http://tutorialsninja.com/demo/index.php?route=common/home";
            driver.get(URL);
        } else {
            Logger.logstep("The Browser in not in scope");
            Logger.logstep("the current browser : "+browser);
            Logger.logstep("the current url : "+URL);
            //System.out.println("The browser is not in scope");
        }
    }
    public TestNG() throws IOException {

    }

    @BeforeTest(alwaysRun = true)
    @Parameters ({"url", "browserName"})
    public void openBrowser(String url, String browserName){
        ExtentManager.startReport();
        setBrowser(browserName,url);
        objReadFile = new ReadFile();


    }

    @Test(priority = 1)
    public void negativeRegistrationTest() throws IOException {
        ExtentManager.logTest("Negative Test Cases for Registration");

//        String filePath = System.getProperty("user.dir") + "\\src";

        Map negativeData[] = objReadFile.readExcel("myFile.xls", "Sheet2");
        System.out.println(negativeData.length);
        for (int i = 1; i < negativeData.length; i++) {
            driver.manage().window().maximize();
            click_register_from_home_page();
            register_an_account((String) negativeData[i].get("First Name"), (String) negativeData[i].get("Last name"), (String) negativeData[i].get("Email"), (String) negativeData[i].get("phone"), (String) negativeData[i].get("password"), (String) negativeData[i].get("confirm password"), (String) negativeData[i].get("subscribe"), (String) negativeData[i].get("policy"));
            String currentURL = driver.getCurrentUrl();
            String url = "http://tutorialsninja.com/demo/index.php?route=account/success";
            System.out.println(currentURL);
            show_error_message(negativeData, i);
            System.out.println(currentURL);
            Logger.logstep("Registration Unsuccessful");
            //System.out.println("Registration Unsuccessful");
        }
    }
    @Test(priority = 2, groups = "Successful Register")
    public void positiveRegistration() throws IOException{
        ExtentManager.logTest("Positive Registration");
        ReadFile objReadFile = new ReadFile();
        RegisterPage objRegister = new RegisterPage(driver);
        String filePath = System.getProperty("user.dir") + "\\src";


        Map positiveData[] = objReadFile.readExcel("myFile.xls", "Sheet1");
        //System.out.println(data[1]);
        System.out.println(positiveData.length);
        String errorData[] = new String[6];

        for(int i=1 ; i< positiveData.length-4; i++) {
            driver.manage().window().maximize();
            click_register_from_home_page();
            register_an_account((String)positiveData[i].get("First Name"),(String)positiveData[i].get("Last name"),(String)positiveData[i].get("Email"),(String)positiveData[i].get("phone"),(String)positiveData[i].get("password"),(String)positiveData[i].get("confirm password"), (String) positiveData[i].get("subscribe"), (String)positiveData[i].get("policy"));
            String currentURL= driver.getCurrentUrl();
            String url = "http://tutorialsninja.com/demo/index.php?route=account/success";
            System.out.println(currentURL);
            registration_success_message();
            //Logger.logstep("Registration Successful");
            //System.out.println("Registration successful");
            System.out.println(currentURL);
            logout_after_registration();

        }
    }

    @DataProvider (name = "negative login data")
    public Object[][] negLogin(){
        return new Object[][] {{"saad1@gmail.com", "1234"}};
    }
    @Test(enabled = true,priority = 3, dataProvider = "negative login data")
    public void negativeLoginCheck(String emailID, String pass) throws IOException{
        ExtentManager.logTest("Negative Login verify");
        driver.manage().window().maximize();
        click_login_from_home_page();
        Map positiveData[] = objReadFile.readExcel("myFile.xls", "Sheet3");
        String err = (String) positiveData[1].get("Login Failed msg");
        login_existing_account(emailID, pass);
        loginFailedError(err);
        String currentURL= driver.getCurrentUrl();
        String url = "http://tutorialsninja.com/demo/index.php?route=account/login";
        System.out.println(currentURL);
        System.out.println("this is working");
        System.out.println(currentURL);
        Assert.assertEquals(url,currentURL,"Error found in Login");

    }

    @DataProvider (name = "positiveLogin")
    public Object[][] dpMethod(){
        return new Object[][] {{"saad500@gmail.com", "123456"}};
    }
    @Test(priority = 4, groups = "Successful Login", dataProvider = "positiveLogin")
    public void positiveLogin(String emailID, String pass) throws IOException {
        ExtentManager.logTest("Verify Successful User Login");
//        ReadFile objReadFile = new ReadFile();
//        String filePath = System.getProperty("user.dir") + "\\src";

//        Map data[] = objReadFile.readExcel(filePath, "myFile.xls", "Sheet3");
//        System.out.println(data.length);
//        for(int i=1 ; i< data.length; i++) {
        driver.manage().window().maximize();
        click_login_from_home_page();
        login_existing_account(emailID, pass);
        String currentURL = driver.getCurrentUrl();
        String url = "http://tutorialsninja.com/demo/index.php?route=account/success";
        System.out.println(currentURL);
        System.out.println("Successful positive login");
        System.out.println(currentURL);

//    }
    }

    @Test(priority = 5, dependsOnGroups = "Successful Login")

    public void verifyAddToCartQuantityPrice() throws IOException{
        ExtentManager.logTest("Verify Product Add to Cart and Quantity and Price");
        click_on_your_store();
        click_on_first_item();
        edit_quantity_add_to_cart();
        check_quantity_and_subtotal();
        click_logout();
        logout_msg_check();
    }



    @AfterMethod(alwaysRun = true)
    public void endMethod(ITestResult result) throws Exception {
        ExtentManager.getResult(result, driver);
    }

    @AfterTest(alwaysRun = true)
    public void endSession() {
        ExtentManager.stopReport();
        driver.quit();
    }

}
