package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WeatherForecastPage {
    WebDriver driver;
    public WeatherForecastPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    public String getCurrentTemperature()
    {
        return driver.findElement(By.xpath("//h2[contains(text(),'Current Weather')]/..//div[@class='temp']")).getText();
    }
}
