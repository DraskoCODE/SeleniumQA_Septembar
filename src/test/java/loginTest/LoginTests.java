package loginTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import resources.StandardUser;

public class LoginTests {

    @DataProvider(name = "user-data")
    public Object[][] dataProviderUserData(){
        return new Object[][] {{StandardUser.userName, "secret_sauce", "https://www.saucedemo.com/inventory.html"},
                               {StandardUser.userName, "qa", "https://www.saucedemo.com/"},
                               {StandardUser.userName, "", "https://www.saucedemo.com/"}};
    }

    @Test(dataProvider = "user-data")
    public void verifyLoginDataProviderOnChrome(String userName, String password, String url) {

        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getCurrentURL(), url);

        loginPage.closePage();
    }

    @Test(dataProvider = "user-data")
    public void verifyLoginDataProviderOnFireFox(String userName, String password, String url) {
        System.setProperty("firefox.driver", "D:\\Driver\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getCurrentURL(), url);

        loginPage.closePage();
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getCurrentURL(), "https://www.saucedemo.com/inventory.html");

        loginPage.closePage();
    }

    @Test
    public void verifyLoginWithLockedOutUser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("locked_out_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getCurrentURL(), "https://www.saucedemo.com/");

        loginPage.closePage();
    }

    @Test
    public void verifyLoginWithoutUserName() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getCurrentURL(), "https://www.saucedemo.com/");

        loginPage.closePage();
    }



}
