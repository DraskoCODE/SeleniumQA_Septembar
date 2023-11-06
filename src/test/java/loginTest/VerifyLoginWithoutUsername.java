package loginTest;

import org.example.Locators;
import org.example.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyLoginWithoutUsername {

    //negativan test, kucamo validan password
    public static void main(String[] args) {

        //final String urlLoginPage = "https://www.saucedemo.com/";
        final String urlProductsPage = "https://www.saucedemo.com/inventory.html";

        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(Url.urlLoginPage);

        //set username
        //WebElement inputUserName = driver.findElement(By.id("user-name"));
        //inputUserName.clear();

        //set password
        WebElement inputPassword = driver.findElement(By.id(Locators.inputPassword));
        inputPassword.clear();
        inputPassword.sendKeys("secret_sauce");

        //driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click on Login
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click();

        //validacija, potvrda da je test prosao/pao
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        if(currentUrl.equals(Url.urlLoginPage)) {
            System.out.println("PASSED");
        }
        else {
            System.out.println("FAILED");
        }

        driver.quit();

    }
}
