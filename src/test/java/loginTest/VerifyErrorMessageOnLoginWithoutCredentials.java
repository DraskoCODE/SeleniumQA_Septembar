package loginTest;

import org.example.Locators;
import org.example.Message;
import org.example.Url;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyErrorMessageOnLoginWithoutCredentials {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(Url.urlLoginPage);

        //WebElement btnLogin = driver.findElement(By.id("login-button"));
        WebElement btnLogin = driver.findElement(By.xpath(Locators.loginButton));
        btnLogin.click();

        //locairanje web elementa preko xpath-a
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        //Assert.assertEquals("Message is not as expected", "Epic sadface: Username is required", errorMessage);
        WebElement closeErrorMessage = driver.findElement(By.xpath("//button[@class='error-button']"));
        closeErrorMessage.click();

        if(errorMessage.equals(Message.errorWithoutCredentials)) {
            System.out.println("PASSED");
        }
        else {
            System.out.println("FAILED");
        }

        driver.quit();

    }

}
