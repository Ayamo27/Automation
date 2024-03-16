


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class Test {
    String item="(//div[@class=\"cart_list\"]/div[@class=\"cart_item\"]/div[@class=\"cart_item_label\"]/a/div/text())";
    public String btnByName(String name){
        String locator = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item_label']/following-sibling::div[@class='pricebar']/button", name);
        return locator;
    }


    private WebDriver driver;
    String button="login-button";
    String name="standard_user";
    String nameId="user-name";
    String passId="password";
    String pass="secret_sauce";
    String cardName="Sauce Labs Bolt T-Shirt";
    String actualName="//div[@class=\"inventory_item_name\"]";
    String cardLocator="//a[@class=\"shopping_cart_link\"]";
    @org.testng.annotations.Test
    public void testDemo(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);


        driver.get("https://www.saucedemo.com/");
        By fildnameId=By.id(nameId);
        WebElement loginById= driver.findElement(fildnameId);
        loginById.sendKeys(name);
        By fildpassId=By.id(passId);
        WebElement loginBypass= driver.findElement(fildpassId);
        loginBypass.sendKeys(pass);

        By buttonID=By.id(button);
        WebElement pressBtn= driver.findElement(buttonID);
        pressBtn.sendKeys(Keys.ENTER);
        String cardBtn=btnByName(cardName);
        By btnXpath=By.xpath(cardBtn);
        WebElement okBtn= driver.findElement(btnXpath);
        okBtn.sendKeys(Keys.ENTER);
        By cardClass=By.xpath(cardLocator);
        WebElement openCard= driver.findElement(cardClass);
        openCard.click();
        By validateName=By.xpath(actualName);
        WebElement ValidateItem= driver.findElement(validateName);
        String ActualItem = ValidateItem.getText();
        Assert.assertTrue(ActualItem.equals(cardName), "Expected card name: " + cardName + ", Actual card name: " + ActualItem);

    }
}