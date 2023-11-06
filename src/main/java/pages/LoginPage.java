package pages;


import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public WebElement returnPassword() {
        return driver.findElement(By.id("password"));
    }



    //metode za akcije nad web elementima
    public void setUserName(String username) {
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.clear();
        userName.sendKeys(username);
        //driver.findElement(By.id("user-name")).sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordTxtField = driver.findElement(By.id("password"));
        //WebElement passwordTxtField = returnPassword();
        passwordTxtField.sendKeys(password);
    }

    public void clickOnLogin() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        //driver.findElement(By.id("login-button")).click();
    }

    public String getUserNameText() {
        WebElement userName = driver.findElement(By.id("user-name"));
        System.out.println(userName.getText());
        return userName.getText();
    }

    public void login(User user) {
        setUserName(user.getUserName());
        setPassword(user.getPassword());
        clickOnLogin();
    }

    public void login(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickOnLogin();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getErrorMessage() {
        return "";
    }

    public void closePage() {
        driver.quit();
    }

}
