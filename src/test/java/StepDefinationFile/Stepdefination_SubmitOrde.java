package StepDefinationFile;

import com.PageObjects.CheckoutPageObject;
import com.PageObjects.LoginPageObject;
import com.PageObjects.PaymentPageObject;
import com.PageObjects.ShoppingPageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class Stepdefination_SubmitOrde extends BaseTest {


    public LoginPageObject login;
    public ShoppingPageObject shop;
    public  CheckoutPageObject checkout;
    public PaymentPageObject payment;

    @Given("the e comm sit is launched")
    public void the_e_comm_sit_is_launched() throws IOException
    {
        login = launchUrl();
        if (login == null)
        {
            throw new NullPointerException("LoginPageObject is not initialized");
        }
    }

    @Given("^login with user (.+) and (.+)$")
    public void login_with_user_and_pwd(String username, String password) throws InterruptedException {
       shop = login.loginApplication(username, password);
         }

    @When("^ add the (.+) to cart$")
    public void add_the_productname_to_cart(String productname) throws InterruptedException
    {
        List<WebElement> itemsonwebsite = shop.getItemsOnWebsite();
         checkout = shop.addToCart("productname");
    }
    @And("^Checkout the (.+) and submit order$")
    public  void Checkout_the_productname_and_submit_order(String productname)
    {
        checkout.clickoncartbtn();

        Boolean match = checkout.checktheshoppeditemisincheckout("productname");
        Assert.assertTrue(match);
        payment = checkout.clickonCheckout();
        payment.enterCountry("india");
        payment.selectcountryfromdropdown();
        payment.clickonPlaceOrder();
    }
    @Then(" Then Verify the confirmation message {string}")
    public void  Then_Verify_the_confirmation_message(String message)
    {
        String Actualmessage = payment.validateSuccessMessage();
        Assert.assertTrue(Actualmessage.equalsIgnoreCase(message));

    }



}
