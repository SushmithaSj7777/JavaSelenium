package org.Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class E2ETest {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver =  new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      //  driver.manage().window().maximize();
        driver.findElement(By.id("userEmail")).sendKeys("sushmithasj7777@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("9164078841Vj$");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
        List<WebElement> itemsonwebsite = driver.findElements(By.cssSelector(".col-lg-4"));
        String productname = "Zara COAT 3";
        WebElement itemneeded = itemsonwebsite.stream().
               filter(product->product.findElement(By.tagName("b")).getText()
                       .equalsIgnoreCase(productname)).findAny().orElse(null);

       itemneeded.findElement(By.cssSelector(".card-body button[class='btn w-10 rounded']")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        List<WebElement> itemsincart = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = itemsincart.stream().anyMatch(items->items.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(match);
        // Locate the element

        driver.findElement(By.cssSelector(".totalRow button"));
        // Wait for the element to be clickable

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
        // Click the element using JavaScript to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);




        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
        WebElement dropdown=driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        WebElement placeorder = driver.findElement(By.cssSelector(".icon-arrow-right-circle"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeorder);
        String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
        driver.close();




    }



}
