package loginTest;

import org.example.Url;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyLoginWithValidCredentials {

    //pozitivan test, kucamo validne kredencijale
    public static void main(String[] args) {

        //final String urlLoginPage = "https://www.saucedemo.com/";
        final String urlProductsPage = "https://www.saucedemo.com/inventory.html";

        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(Url.urlLoginPage);

        //set username
        WebElement inputUserName = driver.findElement(By.id("user-name"));
        inputUserName.clear();
        inputUserName.sendKeys("standard_user");
        System.out.println(inputUserName.getAttribute("value"));

        //set password
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys("secret_sauce");

        //driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click on Login
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click();

        //validacija, potvrda da je test prosao/pao
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        //junit
        Assert.assertEquals("Current url is not as expected",urlProductsPage ,currentUrl);

        driver.quit();

    }

}
