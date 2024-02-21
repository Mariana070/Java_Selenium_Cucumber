package com.test.saucedemo.stepdefinitions;

import com.test.saucedemo.pages.CartPage;
import com.test.saucedemo.pages.CheckOutPage;
import com.test.saucedemo.pages.LoginPage;
import com.test.saucedemo.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverHelper;

public class OrderStepDef {
WebDriver driver = DriverHelper.getDriver();
LoginPage loginPage = new LoginPage(driver);
MainPage mainPage = new MainPage(driver);
CartPage cartPage= new CartPage(driver);
CheckOutPage checkOutPage= new CheckOutPage(driver);
    @Given("User provide username and password to login successfully")
    public void userProvideUsernameAndPasswordToLoginSuccessfully() {
        loginPage.login(ConfigReader.readProperty("QA_username"),ConfigReader.readProperty("QA_password"));
    }
    @When("User choose the {string}, click  add to cart button and validate it is added")
    public void userChooseTheClickAddToCartButtonAndValidateItIsAdded(String productName) {
       mainPage.chooseProduct(productName);
       mainPage.addingProductToCart();
    }
    @When("User click cart icon and checkout button")
    public void userClickCartIconAndCheckoutButton() {
       mainPage.clickCartIconButton(driver);
       cartPage.clickCheckoutButton();
    }
    @When("User provides {string},{string},{string} to checkout page and click continue button")
    public void userProvidesToCheckoutPageAndClickContinueButton(String firstName, String lastName, String zipCode) {
        checkOutPage.sendUserInfo(firstName,lastName,zipCode);

    }
    @Then("User validate the {string},{string},{string},{string} with {string}% tax rate")
    public void userValidateTheWithTaxRate(String productName, String itemTotal, String tax, String totalPrice, String taxRate) {
checkOutPage.validateItemOrderInformation(productName,itemTotal,tax,totalPrice,taxRate);
    }
    @Then("User click Finish Button and validate {string} for purchase")
    public void userClickFinishButtonAndValidateForPurchase(String expectedMessage) {
checkOutPage.clickFinishButton();
        Assert.assertEquals(expectedMessage,checkOutPage.successMessage());
    }

}
