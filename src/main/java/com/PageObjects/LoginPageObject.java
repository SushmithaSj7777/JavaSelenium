package com.PageObjects;

import com.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractComponents {
    WebDriver driver;
    public  LoginPageObject(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //driver.findElement(By.id("userEmail")).sendKeys("sushmithasj7777@gmail.com");
    @FindBy(id="userEmail")
    WebElement userEmail;
    //driver.findElement(By.id("userPassword")).sendKeys("9164078841Vj$");
    @FindBy(id="userPassword")
    WebElement userPassword;
    //driver.findElement(By.id("login")).click();
    @FindBy(id="login")
    WebElement loginbtn;

   By erroralert =By.xpath("//div[@role='alert']");
    //String errormessage=driver.findElement(By.xpath("//div[@role='alert']")).getText();
    @FindBy(xpath = "//div[@role='alert']")
    WebElement errormessageele;

    public ShoppingPageObject loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginbtn.click();
        ShoppingPageObject shop = new ShoppingPageObject(driver);
        return shop;
    }
    public void loginUrl()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String incorrectemailandpwdvalidation()
    {
        waitUnitlElementIsLocated(erroralert);
        String errormessage =errormessageele.getText();
        return errormessage;
    }
}
