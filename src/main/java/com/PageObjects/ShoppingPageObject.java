package com.PageObjects;

import com.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingPageObject extends AbstractComponents {

    WebDriver driver;
    public ShoppingPageObject(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
    //List<WebElement> itemsonwebsite = driver.findElements(By.cssSelector(".col-lg-4"));
    @FindBy(css=".col-lg-4")
    List<WebElement> itemsonwebsite;

    By productsBy=By.cssSelector(".col-lg-4");

    //findElement(By.cssSelector(".card-body button[class='btn w-10 rounded']")).click();
    By addTocart = By.cssSelector(".card-body button[class='btn w-10 rounded']");

    By  ToastMessage= By.cssSelector("#toast-container");
    //driver.findElement(By.cssSelector(".ng-animating"))

    @FindBy(css=".ng-animating")
    WebElement loading;
    //driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

    public List<WebElement> getItemsOnWebsite()
    {
        waitUnitlElementIsLocated(productsBy);
        return itemsonwebsite;
            /*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));*/

    }

    public WebElement searchItemByName(String productName)
    {
        /* WebElement itemneeded = itemsonwebsite.stream().
                    filter(product->product.findElement(By.tagName("b")).getText()
                            .equalsIgnoreCase(productName)).findAny().orElse(null);*/
        WebElement itemneeded =  getItemsOnWebsite().stream().filter(product->product.findElement(By.tagName("b")).getText()
                .equalsIgnoreCase(productName)).findAny().orElse(null);
        return itemneeded;

    }

    public CheckoutPageObject addToCart(String productName) throws InterruptedException {
        //findElement(By.cssSelector(".card-body button[class='btn w-10 rounded']")).click();
       WebElement itemneeded = searchItemByName(productName);
       itemneeded.findElement(addTocart).click();
       waitUnitlElementIsLocated(ToastMessage);
       waitUnitlElementIsInVisible(loading);
        CheckoutPageObject checkout = new CheckoutPageObject(driver);
        return checkout;
    }




}
