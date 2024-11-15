package com.PageObjects;

import com.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPageObject extends AbstractComponents {

    WebDriver driver;
    public PaymentPageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
@FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    // WebElement dropdown=driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]"));
    @FindBy(xpath = "(//span[@class='ng-star-inserted'])[2]")
            WebElement dropdown;
    By waitLocator = By.cssSelector(".ta-item");

   // WebElement placeorder = driver.findElement(By.cssSelector(".icon-arrow-right-circle"));
    @FindBy(css=".icon-arrow-right-circle")
    WebElement placeOrder;

    // String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
    @FindBy(css=".hero-primary")
    WebElement confirmmsg;


    public void enterCountry(String india)
    {
        country.sendKeys(india);
    }

    public void selectcountryfromdropdown()
    {
        waitUnitlElementIsLocated(waitLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
    }
    public void clickonPlaceOrder(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);

    }

    public String validateSuccessMessage()
    {
        String message =confirmmsg.getText();
       return message;
    }


}
