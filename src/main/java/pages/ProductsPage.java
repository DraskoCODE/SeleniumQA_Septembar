package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import resources.ProductPagePath;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    public final static String inventoryList = "//div[@class='inventory_list']";
    public static String inventoryItem = ".//div[@class='inventory_item']";

    public final static String url = "https://www.saucedemo.com/inventory.html";

    public ChromeDriver driver;

    public ProductsPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(ProductsPage.url);
    }

    public void addProductToCart(String productName) {
        //nalazimo inventory_list
        WebElement inventoryList = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        //WebElement inventoryItems = inventoryList.findElement(By.xpath("//div[@class='inventory_item']"));
        List<WebElement> inventoryItems = inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < inventoryItems.size(); i++) {
            WebElement item = inventoryItems.get(i);
            WebElement itemName = item.findElement(By.xpath(".//div[@class='inventory_item_name ']"));
            if(itemName.getText().equals(productName)) {
                //WebElement button = item.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
                WebElement button = item.findElement(By.xpath(".//button"));
                button.click();
                System.out.println("Item added");
                break;
            }
        }
    }

    public List<WebElement> getCartNumber() {
        return driver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
    }

    public String getCartNumberText() {
       List<WebElement> spanList = getCartNumber();
       if(spanList.size() == 0) {
           return "0";
       }
       else {
           return spanList.get(0).getText();
       }
    }

    public void selectSortBy(String sortValue) {
        WebElement select = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        select.click();

        List<WebElement> options = select.findElements(By.xpath(".//option"));

        for(int i = 0; i < options.size(); i++) {
            if(options.get(i).getText().equals(sortValue)) {
                options.get(i).click();
                break;
            }
        }
    }

    public List<Double> returnPrice() {
        List<Double> returnPriceItems = new ArrayList<>();
        //nalazimo inventory_list
        WebElement inventoryList = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        //WebElement inventoryItems = inventoryList.findElement(By.xpath("//div[@class='inventory_item']"));
        List<WebElement> inventoryItems = inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < inventoryItems.size(); i++) {
            WebElement price = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String priceValue = price.getText();
            Double priceValueDouble = Double.parseDouble(priceValue.substring(1));
            returnPriceItems.add(priceValueDouble);
        }

        return returnPriceItems;
    }

    public List<String> returnNames() {
        List<String> returnNameItems = new ArrayList<>();
        //nalazimo inventory_list
        WebElement inventoryList = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        //WebElement inventoryItems = inventoryList.findElement(By.xpath("//div[@class='inventory_item']"));
        List<WebElement> inventoryItems = inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < inventoryItems.size(); i++) {
            WebElement name = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name ']"));
            String nameValue = name.getText();

            returnNameItems.add(nameValue);
        }

        return returnNameItems;
    }

    public List<Product> returnProducts() {
        List<Product> returnProducts = new ArrayList<>();

        WebElement inventoryList = driver.findElement(By.xpath(ProductsPage.inventoryList));
        List<WebElement> inventoryItems = inventoryList.findElements(By.xpath(ProductsPage.inventoryItem));

        for(int i = 0; i < inventoryItems.size(); i++) {
            WebElement name = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name ']"));
            String nameValue = name.getText();

            WebElement price = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String priceValue = price.getText();
            Double priceValueDouble = Double.parseDouble(priceValue.substring(1));

            WebElement desc = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_desc']"));
            String descValue = desc.getText();

            Product product = new Product(nameValue,descValue, priceValueDouble);
            returnProducts.add(product);
        }


        return returnProducts;
    }

    public List<Double> getPrices(List<Product> products) {
        List<Double> prices = new ArrayList<>();
        for(int i = 0; i < products.size(); i++) {
            prices.add(products.get(i).getPrice());
        }
        return prices;
    }


    public void closePage() {
        driver.quit();
    }


}
