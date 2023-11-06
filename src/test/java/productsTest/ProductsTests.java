package productsTest;

import helper.ListManager;
import models.Product;
import models.User;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.List;

public class ProductsTests {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderProductsName(){
        return new Object[][] {{"Sauce Labs Bike Light"},
                               {"Sauce Labs Fleece Jacket"},
                               {"Sauce Labs Onesie"}};
    }

    @Test(dataProvider = "data-provider")
    public void verifyAddItemToCart(String productName) {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        //User standardUser = new User("standard_user", "secret_sauce");
        User standardUser = new User();
        standardUser.userName = "standard_user";
        standardUser.setPassword("secret_sauce");
        //loginPage.login(standardUser);

        //loginPage.setUserName("standard_user");
        //loginPage.setPassword("secret_sauce");
        //loginPage.setUserName(standardUser.getUserName());
        //loginPage.setPassword(standardUser.getPassword());
        //loginPage.clickOnLogin();

        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        String cartNumberBeforeAdd = productsPage.getCartNumberText();
        int cartNumberBeforeAddInt = Integer.parseInt(cartNumberBeforeAdd);

        productsPage.addProductToCart(productName);

        System.out.println(productsPage.getCartNumberText());
        String actualCartNumber = productsPage.getCartNumberText();

        Assert.assertEquals(Integer.parseInt(actualCartNumber), cartNumberBeforeAddInt + 1, "Cart number is not as expected");

        productsPage.closePage();
    }

    @Test
    public void verifyAddItemToCartFor() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        User standardUser = new User("standard_user", "secret_sauce");
        loginPage.login(standardUser);

        List<String> productName = new ArrayList<>();
        productName.add("Sauce Labs Bike Light");
        productName.add("Sauce Labs Fleece Jacket");
        productName.add("Sauce Labs Onesie");

        ProductsPage productsPage = new ProductsPage(driver);

        for(int i = 0; i < productName.size(); i++) {
            String cartNumberBeforeAdd = productsPage.getCartNumberText();
            int cartNumberBeforeAddInt = Integer.parseInt(cartNumberBeforeAdd);

            productsPage.addProductToCart(productName.get(i));

            System.out.println(productsPage.getCartNumberText());
            String actualCartNumber = productsPage.getCartNumberText();

            Assert.assertEquals(Integer.parseInt(actualCartNumber), cartNumberBeforeAddInt + 1, "Cart number is not as expected");
        }

        productsPage.closePage();
    }


    @Test
    public void verifyAddItemSauceLabsBikeLightToCart() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();

        ProductsPage productsPage = new ProductsPage(driver);

        String cartNumberBeforeAdd = productsPage.getCartNumberText();
        int cartNumberBeforeAddInt = Integer.parseInt(cartNumberBeforeAdd);

        productsPage.addProductToCart("Sauce Labs Bike Light");
        //productsPage.addProductToCart("Sauce Labs Onesie");

        System.out.println(productsPage.getCartNumberText());
        String actualCartNumber = productsPage.getCartNumberText();

        Assert.assertEquals(Integer.parseInt(actualCartNumber), cartNumberBeforeAddInt + 1, "Cart number is not as expected");

        //Assert.assertEquals(actualCartNumber, "1", "Cart number is not as expected");

        productsPage.closePage();

    }

    @Test
    public void verifySortByPriceLowToHigh() {
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        User standardUser = new User("standard_user", "secret_sauce");
        loginPage.login(standardUser);
        //loginPage.setUserName("standard_user");
        //loginPage.setPassword("secret_sauce");
        //loginPage.clickOnLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectSortBy("Price (low to high)");

        //Assertion, validacija PASS/FAIL
        //List<Double> prices = productsPage.returnPrice();
        List<Product> products = productsPage.returnProducts();

        Assert.assertTrue(ListManager.isListSortedAsc(productsPage.getPrices(products)), "Prices are not sort as expected");
        System.out.println("TEST");
        productsPage.closePage();
    }

}
