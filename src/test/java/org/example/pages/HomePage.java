package org.example.pages;

import org.example.utils.PropertiesLoad;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage {
    @FindBy(xpath = "//input[@name='query']")
    WebElement location;
    WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void navigateToWeatherForecastPage(String city, String state, String country) throws IOException {
        driver.get(PropertiesLoad.getPropertyValue("url.ui", getClass().getClassLoader().getResourceAsStream("integrationTest.properties")));
        location.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//div[contains(text(),'" + city + ", " + state + ", " + country + "')]")).click();

    }
}
