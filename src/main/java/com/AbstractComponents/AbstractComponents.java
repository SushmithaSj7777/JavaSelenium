package com.AbstractComponents;

import com.PageObjects.OrdersPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartbtn;


@FindBy(css="[routerlink*='/myorders']")
WebElement ordersbtn;




    public void waitUnitlElementIsLocated(By findby)
            {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
                wait.until(ExpectedConditions.visibilityOfElementLocated((findby)));
            }

    public void waitUnitlElementIsInVisible(WebElement ele) throws InterruptedException {
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.invisibilityOf(ele));*/
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    }

    public void clickoncartbtn() {
        cartbtn.click();
    }
    public OrdersPageObject clickonOrdersbtn() {
        ordersbtn.click();
        OrdersPageObject orderspage = new OrdersPageObject(driver);
        return orderspage;
    }



}
