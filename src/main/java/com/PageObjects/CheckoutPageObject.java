package com.PageObjects;

import com.AbstractComponents.AbstractComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPageObject extends AbstractComponents {
    WebDriver driver;

    public CheckoutPageObject(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);


    }

    //List<WebElement> itemsincart = driver.findElements(By.cssSelector(".cartSection h3"));
    @FindBy(css=".cartSection h3")
    List<WebElement> itemsincart;
    
    // WebElement element =driver.findElement(By.cssSelector(".totalRow button"));
    @FindBy(css=".totalRow button")
    WebElement element;



    public boolean checktheshoppeditemisincheckout(String productname)
    {

        boolean match = itemsincart.stream().anyMatch(items->items.getText().equalsIgnoreCase(productname));
        return match;

    }

    public PaymentPageObject clickonCheckout()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        PaymentPageObject payment = new PaymentPageObject(driver);
        return payment;
    }




}
