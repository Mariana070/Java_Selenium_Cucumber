package com.test.saucedemo.pages;

import io.cucumber.java.bs.A;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.text.DecimalFormat;

public class CheckOutPage {
    public CheckOutPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "#first-name")
    public WebElement firstName;
    @FindBy(css = "#last-name")
    public WebElement lastName;
    @FindBy(css = "#postal-code")
    public  WebElement zipCode;
    @FindBy(css = "#continue")
    public WebElement continueButton;
    @FindBy(css = ".inventory_item_name")
    private WebElement productName;
    @FindBy(css = ".summary_subtotal_label")
    private WebElement itemTotal;
    @FindBy(css = ".summary_tax_label")
    private WebElement tax;
    @FindBy(css = ".summary_total_label")
    private WebElement totalPrice;
    @FindBy(css = "#finish")
    private WebElement finishButton;
    @FindBy(xpath = "//h2")
    private WebElement successMessage;
    public void sendUserInfo(String firstName,String lastName, String zipCode){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.zipCode.sendKeys(zipCode);
        continueButton.click();
    }

public void validateItemOrderInformation(String expectedProductName, String expectedItemTotal, String expectedTax,
                                         String expectedTotalPrice, String taxRate){

    double itemTotalConversion = Double.parseDouble(BrowserUtils.getText(itemTotal).
                                 substring(BrowserUtils.getText(itemTotal).indexOf("$")+1));
    double taxConversion=Double.parseDouble(BrowserUtils.getText(tax).
                                 substring(BrowserUtils.getText(tax).indexOf("$")+1));
    double totalPriceConversion=Double.parseDouble(BrowserUtils.getText(totalPrice).
                                substring(BrowserUtils.getText(totalPrice).indexOf("$")+1));
    Assert.assertEquals(expectedProductName,BrowserUtils.getText(productName));
    Assert.assertEquals(expectedItemTotal,String.valueOf(itemTotalConversion));
    double taxCalculation=(itemTotalConversion*(Double.parseDouble(taxRate)*0.01));
    System.out.println(taxCalculation);
    DecimalFormat df = new DecimalFormat("#.00");
    String formattedSumPriceTax = df.format(taxCalculation);
    Assert.assertEquals(expectedTax,formattedSumPriceTax);
    Assert.assertEquals(taxCalculation,taxConversion,0.1);
    Assert.assertEquals(expectedTotalPrice,String.valueOf(totalPriceConversion));
}
    public void clickFinishButton(){
        finishButton.click();
}
    public String successMessage(){
        return BrowserUtils.getText(successMessage);
}

}
