package org.Tests;

import com.PageObjects.*;
import org.TestComponents.BaseTest;
import org.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {

    //String productname = "Zara COAT 3";
    @Test(dataProvider = "getData" , groups = "endtoendtest" , retryAnalyzer = Retry.class)
    public void testCase(HashMap<String,String> input) throws IOException, InterruptedException {
       // LoginPageObject login =launchUrl();
            /*
            driver.get("https://rahulshettyacademy.com/client/");
            driver.findElement(By.id("userEmail")).sendKeys("sushmithasj7777@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("9164078841Vj$");
            driver.findElement(By.id("login")).click();*/
       // LoginPageObject login = new LoginPageObject(driver);
        //login.loginUrl();

        //ShoppingPageObject shop = new ShoppingPageObject(driver);
        ShoppingPageObject shop = login.loginApplication(input.get("email"), input.get("password"));

              /*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
            List<WebElement> itemsonwebsite = driver.findElements(By.cssSelector(".col-lg-4"));*/

        List<WebElement> itemsonwebsite = shop.getItemsOnWebsite();

             /* WebElement itemneeded = itemsonwebsite.stream().
                    filter(product->product.findElement(By.tagName("b")).getText()
                            .equalsIgnoreCase(productname)).findAny().orElse(null);*/
            /*itemneeded.findElement(By.cssSelector(".card-body button[class='btn w-10 rounded']")).click();
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));*/
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        //driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();


        //CheckoutPageObject checkout = new CheckoutPageObject(driver);
        CheckoutPageObject checkout = shop.addToCart(input.get("productname"));
        checkout.clickoncartbtn();




            /*List<WebElement> itemsincart = driver.findElements(By.cssSelector(".cartSection h3"));
            Boolean match = itemsincart.stream().anyMatch(items->items.getText().equalsIgnoreCase(productname));
            Assert.assertTrue(match);*/
        // Locate the element

           /* WebElement element =driver.findElement(By.cssSelector(".totalRow button"));
            // Click the element using JavaScript to avoid interception
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);*/

        Boolean match = checkout.checktheshoppeditemisincheckout(input.get("productname"));
        Assert.assertTrue(match);
        //        PaymentPageObject payment = new PaymentPageObject(driver);
        PaymentPageObject payment = checkout.clickonCheckout();


                        /*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
            //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));

            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
            WebElement dropdown=driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            WebElement placeorder = driver.findElement(By.cssSelector(".icon-arrow-right-circle"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeorder);
                String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
            Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thank you for the order."));*/


        payment.enterCountry("india");
        payment.selectcountryfromdropdown();
        payment.clickonPlaceOrder();
        String message = payment.validateSuccessMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
        //driver.close();


    }

    @Test(dependsOnMethods = {"testCase"} ,dataProvider = "getData")
    public void OrderHistory(HashMap<String,String> input) throws IOException, InterruptedException
    {
        ShoppingPageObject shop = login.loginApplication(input.get("email"), input.get("password"));
        OrdersPageObject orderspage = shop.clickonOrdersbtn();
        Boolean match =orderspage.verifyorderinhistory(input.get("productname"));
        Assert.assertTrue(match);


    }

    @DataProvider()
    public Object[][] getData() throws IOException {
      /*  HashMap<String,String> map=new HashMap<String,String>();
        map.put("email","sushmithasj7777@gmail.com");
        map.put("password","9164078841Vj$");
        map.put("productname","Zara COAT 3");


        HashMap<String,String> map1=new HashMap<String,String>();
        map1.put("email","shetty@gmail.com");
        map1.put("password","Iamking@000");
        map1.put("productname","ADIDAS ORIGINAL");*/
        List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "/src/test/java/org/Data/TestData.json");

        return new Object[][] { {data.get(0)},{data.get(1)}   };







   /* @DataProvider
    public Object[][] getData()
    {
        return new Object[][] {{"sushmithasj7777@gmail.com", "9164078841Vj$", "Zara COAT 3"},
        {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
    }
*/




}}





