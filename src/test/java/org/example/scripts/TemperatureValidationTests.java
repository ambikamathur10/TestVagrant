package org.example.scripts;

import org.example.pages.HomePage;
import org.example.pages.WeatherForecastPage;
import org.example.service.WeatherForecastImpl;
import org.example.utils.DataComparator;
import org.example.utils.DriverUtils;
import org.example.utils.PropertiesLoad;
import org.example.utils.VarianceBreachException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;


public class TemperatureValidationTests extends WeatherForecastImpl {

    HomePage homePage;
    DataComparator dataComparator;
    WeatherForecastPage weatherForecastPage;
    DriverUtils driverUtils;
    String dataFile;


    @BeforeClass
    public void setup() {
        driverUtils = new DriverUtils();
        homePage = new HomePage(DriverUtils.driver);
        dataComparator = new DataComparator();
        weatherForecastPage = new WeatherForecastPage(DriverUtils.driver);
         dataFile = "integrationTest.properties";
    }

    @Test
    public void temperatureComparisonTest() throws IOException {
        homePage.navigateToWeatherForecastPage(PropertiesLoad.getPropertyValue("location.city",getClass().getClassLoader()
                        .getResourceAsStream(dataFile)),
                PropertiesLoad.getPropertyValue("location.state", getClass().getClassLoader()
                        .getResourceAsStream(dataFile)), PropertiesLoad.getPropertyValue(
                        "location.country", getClass().getClassLoader()
                                .getResourceAsStream(dataFile)));
        dataComparator.compareTemperature(weatherForecastPage.getCurrentTemperature(),
                getWeatherForecastDetailsByLocation(
                        PropertiesLoad.getPropertyValue("location.city", getClass().getClassLoader()
                                .getResourceAsStream(dataFile)),
                        PropertiesLoad.getPropertyValue("location.state.code", getClass().getClassLoader()
                                .getResourceAsStream(dataFile)), PropertiesLoad.getPropertyValue("location.country.code", getClass().getClassLoader()
                                .getResourceAsStream(dataFile))));
    }

    @Test
    public void temperatureVarianceTest() throws IOException, VarianceBreachException {
        homePage.navigateToWeatherForecastPage(PropertiesLoad.getPropertyValue("location.city", getClass().getClassLoader()
                .getResourceAsStream(dataFile)),
                PropertiesLoad.getPropertyValue("location.state", getClass().getClassLoader()
                        .getResourceAsStream(dataFile)), PropertiesLoad.getPropertyValue(
                        "location.country", getClass().getClassLoader()
                                .getResourceAsStream(dataFile)));
        Assert.assertTrue(dataComparator.variance(weatherForecastPage.getCurrentTemperature(),
                getWeatherForecastDetailsByLocation(
                        PropertiesLoad.getPropertyValue("location.city", getClass().getClassLoader()
                                .getResourceAsStream(dataFile)), PropertiesLoad.getPropertyValue(
                                "location.state.code", getClass().getClassLoader()
                                        .getResourceAsStream(dataFile)), PropertiesLoad.getPropertyValue(
                                "location.country.code", getClass().getClassLoader()
                                        .getResourceAsStream(dataFile)))), "If the temperature difference" +
                " is between <=2 ");
    }

    @AfterClass
    private void close() {
        DriverUtils.driver.quit();
    }

}
